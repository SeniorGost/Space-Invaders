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
	
	/**
	 * Debes de llamar a este metodo al pricipio de cada partida, pero solo UNA VEZ
	 */
	public void iniciar() {
		listaBalas = new LinkedList<BalaJugador>();
		
		Flota.getFlota().inicializar();
	}
	
	/**
	 * Genera una bala del tipo indicado en la posicion indicada.
	 * 
	 * @param posX - Componente x de la posicion en la que se desea que aparezca la nueva bala.
	 * @param posY - Componente y de la posicion en la que se desea que aparezca la nueva bala.
	 * @param type - Existen diferentes tipos de balas, este paramtro especifica cual es el deseado.
	 */
	public void shoot(int posX, int posY) {		
		if (posY > 0) {
			BalaJugador nuevaBala = new BalaRombo(posX, posY);
			// Cuando se implementen las entidades multipixel, cambiar el segundo parametro de la constructora.
			listaBalas.add(nuevaBala);
		}
	}
	
	/**
	 * Inicia la lógica de las balas disparadas por el jugador. Puede generar una nueva bala del jugador.
	 * Entre otros, se encarga de mover las balas y envia señales de su posición al vista.
	 * 
	 * @param pixNaveX - Componente x de las posiciones de los pixeles de la nave con los que el alien puede impactar.
	 * @param pixNaveY - Componente y de las posiciones de los pixeles de la nave con los que el alien puede impactar.
	 * @param naveX - Componente x de la posicion central de la nave.
	 * @param naveY - Componente y de la posicion central de la nave.
	 * @throws JuegoCambiadoException Propaga excepciones.
	*/
	public void tick(int[] pixNaveX, int[] pixNaveY, int naveX, int naveY) throws JuegoCambiadoException {		
		// Se envia el tick a cada una de la balas del jugador
		if (!listaBalas.isEmpty()) {
			Iterator<BalaJugador> it = listaBalas.iterator();
			
			while (it.hasNext()) {
				BalaJugador curBala = it.next();
				
				LinkedList<Integer> pX = curBala.getDisplayX();
				LinkedList<Integer> pY = curBala.getDisplayY();
				
				Iterator<Integer> iteratorPixelesX = pX.iterator();
				Iterator<Integer> iteratorPixelesY = pY.iterator();
				
				while (iteratorPixelesX.hasNext() && iteratorPixelesY.hasNext()) {
					notifyView(iteratorPixelesX.next(), iteratorPixelesY.next());
				}
				// Una bala es eliminada si devuelve 'true' en su metodo tick
				if(curBala.tick()) it.remove();
			}
		}
		
		// Propaga el tick
		Flota.getFlota().tick(pixNaveX, pixNaveY, naveX, naveY);
	}
	
	/**
	 * Mediante el 'Patron Observer' notifica al vista de las posiciones dadas.
	 * 
	 * @param posX - Posicion horizontal de la bala.
	 * @param posY - Posicion vertical de la bala.
	 */
	private void notifyView(int posX, int posY) {
		if (posX < 0 || posX > Modelo.getModelo().getWidth() - 1)
			return;
		if (posY < 0 || posY > Modelo.getModelo().getHeight() - 1)
			return;
		
		setChanged();
		notifyObservers(new int[] {posX, posY});
	}
}
