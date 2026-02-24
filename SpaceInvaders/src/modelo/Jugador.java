package modelo;

import java.awt.Color;

public final class Jugador {
	private int posX;
	private int posY;
	private Color color;
	// posible método de movimiento: int movDir = [-1,0,1]
	// útil para definir x += movDir*velocidad(CONSTANTE)
	private boolean movDir;
	private boolean willMove;
	private boolean willShoot;
	private static Jugador miJugador;
	
	private Jugador() {
		
	}
	
	public static Jugador getJugador() {
		if (miJugador == null) miJugador = new Jugador();
		
		return miJugador;
	}
	
	public void tick() {
		
	}
	
	public void move(boolean pWillMove) {
		
	}
	
	public void shoot() {
		
	}
}
