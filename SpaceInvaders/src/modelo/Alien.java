package modelo;

import java.util.LinkedList;
import java.util.Observable;
@SuppressWarnings("deprecation")
public class Alien extends Observable{
	private int posX;
	private int posY;
	
	public boolean tick(int x, int y)  throws JuegoPerdidoException {
		return false;
	}
	
	private boolean estaEnLimite() {
		return false;
	}
	
	public void disparar() {
		
	}
}
