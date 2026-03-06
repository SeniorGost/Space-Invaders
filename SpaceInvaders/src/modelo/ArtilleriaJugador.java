package modelo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
@SuppressWarnings("deprecation")
public class ArtilleriaJugador {
	private LinkedList<BalaJugador> listaBalas;
	private static ArtilleriaJugador miArtilleriaJugador;
	
	public static ArtilleriaJugador getArtilleria() {
		if (miArtilleriaJugador == null) iniciar();
		return miArtilleriaJugador;
	}
	
	/**
	 * Crea una nueva instancia de {@code ArtilleriaJugador}
	 */
	public static void iniciar() {
		miArtilleriaJugador = new ArtilleriaJugador();
	}
	
	/**
	 * Inicia la lógica de las balas disparadas por el jugador.
	 * Entre otros, se encarga de mover las balas y envia señales de su posición al vista.
	 * 
	 * Puede ser llamado con parametros. En cuyo caso, se generará una nueva bala del jugador.
	*/
	public void tick() throws JuegoCambiadoException {
		tick(0, 0, false);
	}
	
	/**
	 * Inicia la lógica de las balas disparadas por el jugador. Puede generar una nueva bala del jugador.
	 * Entre otros, se encarga de mover las balas y envia señales de su posición al vista.
	 * 
	 * @param posX - La posición horizontal de la nave en el momento del disparo.
	 * @param willShoot - El método generará una nueva bala cuando este parametro es {@code true}, no generará una bala cuando sea {@code false}.
	*/
	public void tick(int posX, int posY, boolean willShoot) throws JuegoCambiadoException {
		if (willShoot && posY > 0) {
			BalaJugador nuevaBala = new BalaJugador(posX, posY - 1);
			// Cuando se implementen las entidades multipixel, cambiar el segundo parametro de la constructora.
			listaBalas.add(nuevaBala);
		}
		
		if (!listaBalas.isEmpty()) {
			Iterator<BalaJugador> it = listaBalas.iterator();
			
			while (it.hasNext()) {
				BalaJugador curBala = it.next();
				
				if(curBala.tick()) {it.remove();}
			}
		}
		
		Flota.getFlota().tick(posX, posY);
		
	}
}
