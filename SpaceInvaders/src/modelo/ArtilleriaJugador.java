package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import modelo.excepciones.JuegoCambiadoException;


@SuppressWarnings("deprecation")
public class ArtilleriaJugador extends Observable {
	private LinkedList<BalaJugador> listaBalas;
	private static ArtilleriaJugador miArtilleriaJugador;
	
	public static ArtilleriaJugador getArtilleria() {
		if (miArtilleriaJugador == null) miArtilleriaJugador = new ArtilleriaJugador();
		return miArtilleriaJugador;
	}
	
	private ArtilleriaJugador() {
		listaBalas = new LinkedList<BalaJugador>();
	}
	
	/**
	 * Debes de llamar a este metodo al pricipio de cada partida, pero solo UNA VEZ
	 */
	public void iniciar(int tipo) {
		listaBalas = new LinkedList<BalaJugador>();
		
		//esto le pasa al generador el id correspondiente al tipo de nave con el que se quiere jugar para que generador sette la estrategia segun eso
		GeneradorBalas.getGeneradorBalas().setEstrategia(tipo);
		
		Flota.getFlota().inicializar();
	}
	
	/**
	 * Genera una bala del tipo indicado en la posicion indicada.
	 * 
	 * @param posX - Componente x de la posicion en la que se desea que aparezca la nueva bala.
	 * @param posY - Componente y de la posicion en la que se desea que aparezca la nueva bala.
	 */
	public void shoot(int posX, int posY) {		
		BalaJugador nBala = GeneradorBalas.getGeneradorBalas().generarBalaJugador(posX, posY);
		if (!nBala.canMoveH(0) && !nBala.canMoveV(0))
			return;
		
		listaBalas.add(nBala);
	}
	
	/**
	 * Inicia la lógica de las balas disparadas por el jugador. Puede generar una nueva bala del jugador.
	 * Entre otros, se encarga de mover las balas y envia señales de su posición al vista.
	*/
	public void tick() throws JuegoCambiadoException {

		Iterator<BalaJugador> it = listaBalas.iterator();
		
		while(it.hasNext()) {
			BalaJugador curBala = it.next();
			
			if (curBala.canMoveV(-1)) {				
				curBala.move(0, -1);
				if(curBala.tick())
					it.remove();
			} else {
				it.remove();
			}
		}
		
		// Propaga el tick
		Flota.getFlota().tick();
	}
}
