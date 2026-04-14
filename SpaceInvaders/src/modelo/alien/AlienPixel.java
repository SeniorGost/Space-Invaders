package modelo.alien;

import java.util.LinkedList;

import modelo.Modelo;
import modelo.excepciones.JuegoPerdidoException;

public class AlienPixel extends Alien {

	public AlienPixel(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean tick(int deltaX, int deltaY) throws JuegoPerdidoException {    	
    	// Verificación de límite inferior (si el alien llega abajo, se pierde el juego)
    	if (getPosY() == (Modelo.getModelo().getHeight()-1))
    		throw new JuegoPerdidoException();
        
    	boolean rdo = super.tick(deltaX, deltaY);
        
        return rdo;
	}

	@Override
	public boolean playerCollided(int[] pPosX, int[] pPosY, int offsetX, int offsetY, int hurtboxX, int hurtboxY) {
		for (int i = 0; i < pPosX.length; i++) {
			if (getPosX() == pPosX[i] && getPosY() == pPosY[i]) 
				return true;
		}
		return false;
	}
	
	@Override
	public boolean hit(int[] pixelesX, int[] pixelesY, int pPosX, int pPosY, int hurtboxX, int hurtboxY) {
    	// Se comprueba tambien las posiciones de alrededor de la bala para facilitar eliminar a los aliens
		
		for (int i = 0; i < pixelesX.length; i++) {			

			int balaX = pixelesX[i];
			int balaY = pixelesY[i];
			
			if (this.getPosX() == balaX && (this.getPosY() == balaY || this.getPosY() == balaY - 1)) 
				return true;
			if (justMoved)
				if (this.getPosX() == balaX && this.getPosY() == balaY + 1) 
					return true;
		}
		return false;
	}
	
	@Override
	public LinkedList<Integer> getDisplayX() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		
		rdo.add(getPosX());
		
		return rdo;
	}
	
	@Override
	public LinkedList<Integer> getDisplayY() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		
		rdo.add(getPosY());
		
		return rdo;
	}
}
