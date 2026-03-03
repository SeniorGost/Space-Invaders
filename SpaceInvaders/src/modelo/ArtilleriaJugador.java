package modelo;

import java.util.LinkedList;
import java.util.Observable;
@SuppressWarnings("deprecation")
public class ArtilleriaJugador {
	private LinkedList<BalaJugador> listaBalas;
	private static ArtilleriaJugador miArtilleriaJugador;
	
	public static ArtilleriaJugador getArtilleria() {
		if (miArtilleriaJugador == null) iniciar();
		return miArtilleriaJugador;
	}
	
	public static void iniciar() {
		miArtilleriaJugador = new ArtilleriaJugador();
	}
	
	public void tick() throws JuegoCambiadoException{
		Flota.getFlota().tick(null);
	}
	
	public void tick(int posX, int posY) {
		
	}
	
	private void moverBalas() {
		
	}
	
	private void crearBala(int posX, int posY) {
		
	}
}
