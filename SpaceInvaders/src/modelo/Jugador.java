package modelo;

import java.awt.Color;
import java.util.Observable;

@SuppressWarnings("deprecation")
public final class Jugador extends Observable {
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
	public static void inicializar() {
		miJugador = new Jugador();
	}
	
	public static Jugador getJugador() {
		if (miJugador == null) inicializar();
		
		return miJugador;
	}
	
	public void tick(long cosa) {
		System.out.println(cosa);
	}
	
	public void move(boolean pWillMove) {
		
	}
	
	public void shoot() {
		
	}
}
