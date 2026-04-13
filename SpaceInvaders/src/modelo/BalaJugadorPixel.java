package modelo;

import java.util.LinkedList;

public class BalaJugadorPixel extends BalaJugador {

	public BalaJugadorPixel(int pPosX, int pPosY) {
		super(pPosX, pPosY);
	}

	/**
	 * Solo llamar a este metodo si estamos trabajando con una instancia de una bala singular.
	 * NO SE DEBE DE LLAMAR cuando se esta trabajando con una instancia de in pexel de una bala mas grande.
	 * <p> Quedas avisado...
	 * <p> Comprueba si la bala se encuentra en una posicion en la que hay un pixel de un alien, si no, se mueve hacia arriba.
	 * @return {@code true} si la bala a colisionado con un alien, {@code false} en caso contrario.
	 */
	
	@Override
	public boolean tick() throws JuegoGanadoException {
		
		int[] pX = new int[1]; pX[0] = getPosX();
		int[] pY = new int[1]; pY[0] = getPosY();
		
		if (Flota.getFlota().hit(pX, pY, getPosX(), getPosY(), 1, 1))
			return true;
		move();
		
		return false;
	}
	
	@Override
	public LinkedList<Integer> getDisplayX() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		rdo.add(getPosX());
		return rdo;
	}
	@Override
	public LinkedList<Integer> getDisplayY() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		rdo.add(getPosY());
		return rdo;
	}
}
