package modelo;

import java.util.Observable;
@SuppressWarnings("deprecation")
public class BalaJugador extends Observable{
	private int posX;
	private int posY;
	
	public BalaJugador(int pPosX, int pPosY) {
		posX = pPosX;
		posY = pPosY;
	}
	
	public boolean tick() {
		boolean rdo = Flota.getFlota().hit(posX, ++posY);
		
		if (!rdo) {
			setChanged();
			int[] pos = {posX, posY};
			notifyObservers(pos);
		}
		
		return rdo;
	}
}
