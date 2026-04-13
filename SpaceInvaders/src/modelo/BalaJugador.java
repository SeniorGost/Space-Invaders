package modelo;

import java.util.LinkedList;

public abstract class BalaJugador {
	private int posX;
	private int posY;
	
	public BalaJugador(int pPosX, int pPosY) {
		posX = pPosX;
		posY = pPosY;
	}
	
	/**
	 * Gestiona la logica de la bala
	 * 
	 * @return {@code true} si la bala ha impactado con algun alien, {@code false} en caso contrario
	 * @throws JuegoGanadoException - Si la bala ha causado que se elimine el ultimo alien de la flota
	 */
	
	public boolean tick() throws JuegoGanadoException {
		return move();
	}
	
	/**
	 * Disminuye su posicion vertical una unidad (sube una casilla).
	 * @return {@code true} si ha alcanzado el limite superior del grid ('y' a lo sumo 0).
	 * {@code false} en caso contrario.
	 */
	protected boolean move() {
		if (--posY > 0)
			return false;
		return true;
		
	}
	
	public abstract LinkedList<Integer> getDisplayX();
	public abstract LinkedList<Integer> getDisplayY();
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
}
