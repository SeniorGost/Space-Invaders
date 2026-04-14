package modelo.balaJugador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import modelo.Flota;
import modelo.excepciones.JuegoGanadoException;

public abstract class BalaJugadorMultipixel extends BalaJugador {

	ArrayList<BalaJugador> pixeles;
	
	int hurtboxX;
	int hurtboxY;
	
	/**
	 * @param pPosX - La coordenada x de la posicion central de la bala
	 * @param pPosY - La coordenada y de la posicion central de la bala
	 * @param hX - El ancho de la hitbox de la bala (dividido entre 2)
	 * @param hY - La altura de la hitbox de la bala (dividido entre 2)
	 */
	
	public BalaJugadorMultipixel(int pPosX, int pPosY, int hX, int hY) {
		super(pPosX, pPosY);
		
		hurtboxX = hX;
		hurtboxY = hY;
		
		pixeles = new ArrayList<BalaJugador>();
	}
	
	/**
	 * Comprueba si alguno de sus pixeles comparte posicion con algun pixel de algun alien, en caso contrario,
	 * disminuye su posicion vertical (sube una casilla).
	 * 
	 * @return {@code true} si ha collisionado con un alien o si ha alcanzado el limite superior del grid,
	 * {@code false} en caso contrario.
	 */
	@Override
	public boolean tick() throws JuegoGanadoException {
		int[] pX = new int[pixeles.size()];
		int[] pY = new int[pixeles.size()];
		
		for (int i = 0; i < pixeles.size(); i++) {
			pX[i] = pixeles.get(i).getPosX();
			pY[i] = pixeles.get(i).getPosY();
		}
		
		if (Flota.getFlota().hit(pX, pY, getPosX(), getPosY(), hurtboxX, hurtboxY))
			return true;
		
		if (super.tick())
			return true;
		
		return false;
	}
	
	/**
	 * Mueve todos los pixeles asociados a el y mueve su posicion central.
	 * 
	 * @return {@code true} si al menos uno de los pixeles asociados ha alcanzado el limite superior
	 * del grid ('y' a lo sumo 0). {@code false} en caso contrario.
	 */
	@Override
	protected boolean move() {
		boolean rdo = false;
		Iterator<BalaJugador> it = pixeles.iterator();
		
		while (it.hasNext()) {
			BalaJugador p = it.next();
			rdo = p.move() || rdo;
		}
		super.move();
		
		return rdo;
	}
	
	@Override
	public LinkedList<Integer> getDisplayX() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		
		for (BalaJugador b: pixeles) {
			rdo.add(b.getPosX());
		}
		return rdo;
	}
	@Override
	public LinkedList<Integer> getDisplayY() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		
		for (BalaJugador b: pixeles) {
			rdo.add(b.getPosY());
		}
		return rdo;
	}
	
	
	protected void addPixel(BalaJugador p) {
		pixeles.add(p);
	}
}
