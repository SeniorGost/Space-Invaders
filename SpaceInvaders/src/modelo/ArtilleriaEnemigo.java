package modelo;

import java.util.LinkedList;

import modelo.balaJugador.BalaEnemigo;

public class ArtilleriaEnemigo {
	
	private static ArtilleriaEnemigo miArtilleriaEnemiga;
	private LinkedList<BalaEnemigo> listaBalas;
	public static ArtilleriaEnemigo getArtilleria() {
		if (miArtilleriaEnemiga == null) miArtilleriaEnemiga = new ArtilleriaEnemigo();
		return miArtilleriaEnemiga;
	}
	
	public void tick() {
		
	}
	
	public void tick(int posX, int posY) {
		
	}
	
	private void moverBalas() {
		
	}
	
	private void crearBala() {
		
	}
}
