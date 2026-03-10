package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
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
	
	public void iniciar() {
		listaBalas = new LinkedList<BalaJugador>();
		
		Flota.getFlota().inicializar();
	}
	
	/**
	 * Inicia la lógica de las balas disparadas por el jugador.
	 * Entre otros, se encarga de mover las balas y envia señales de su posición al vista.
	 * 
	 * Puede ser llamado con parametros. En cuyo caso, se generará una nueva bala del jugador.
	*/
	public void tick(int posX, int posY) throws JuegoCambiadoException {
		tick(posX, posY, false);
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
		
		// Se envia el tick a cada una de la balas del jugador
		if (!listaBalas.isEmpty()) {
			Iterator<BalaJugador> it = listaBalas.iterator();
			
			while (it.hasNext()) {
				BalaJugador curBala = it.next();
				
				// Una bala es eliminada si devuelve 'true' en su metodo tick
				if(curBala.tick()) it.remove();
			}
		}
		
		notifyView();
		
		// Propaga el tick
		Flota.getFlota().tick(posX, posY);
	}
	
	/**
	 * Mediante el 'Patron Observer' notifica al vista de las posiciones de las balas
	 */
	private void notifyView() {
		for (BalaJugador b : listaBalas) {
			setChanged();
			notifyObservers(new int[] {b.getPosX(), b.getPosY()});
		}
	}
}
