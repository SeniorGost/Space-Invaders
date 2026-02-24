package modelo;

import java.util.LinkedList;

public class ArtilleriaJugador {
	private LinkedList<BalaJugador> listaBalas;
	private static ArtilleriaJugador miArtilleriaJugador;
	
	public static ArtilleriaJugador getArtilleria() {
		if (miArtilleriaJugador == null) miArtilleriaJugador = new ArtilleriaJugador();
		return miArtilleriaJugador;
	}
	
	public void tick() {
		
	}
	
	public void tick(int posX, int posY) {
		
	}
	
	private void moverBalas() {
		
	}
	
	private void crearBala(int posX, int posY) {
		
	}
}
