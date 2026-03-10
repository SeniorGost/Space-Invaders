package modelo;

public class BalaJugador {
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
		boolean rdo = false;
		
		rdo = Flota.getFlota().hit(posX, ++posY);
		
		return rdo;
	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
}
