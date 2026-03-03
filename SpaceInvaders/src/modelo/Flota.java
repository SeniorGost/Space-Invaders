package modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Flota {
	private ArrayList<Alien> listaAliens;
	private boolean movDir;
	private int tickCount;
	private static Flota miFlota;
	
	public static Flota getFlota() {
		if(miFlota == null) inicializar();
		
		return miFlota;
	}
	
	/*
	 * TODO iniciar la lista de Aliens en formación
	 */
	public static void inicializar() {
		miFlota = new Flota();
	}
	
	public void tick(LinkedList<int[]> listaBalas) throws JuegoCambiadoException {
		
	}
	
	private void moverAliens() {
		
	}
	
	private void decidirDireccion() {
		
	}
	
	private void decidirAlien() {
		
	}
}
