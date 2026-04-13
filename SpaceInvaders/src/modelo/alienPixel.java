package modelo;

import java.util.LinkedList;

public class alienPixel extends Alien {

	public alienPixel(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean tick(int deltaX, int deltaY) throws JuegoPerdidoException {    	
    	// Verificación de límite inferior (si el alien llega abajo, se pierde el juego)
    	if (posY == (Modelo.getModelo().getHeight()-1))
    		throw new JuegoPerdidoException();
        
    	boolean rdo = super.tick(deltaX, deltaY);
        
        return rdo;
	}

	@Override
	public boolean playerCollided(int[] pPosX, int[] pPosY, int offsetX, int offsetY, int hurtboxX, int hurtboxY) {
		for (int i = 0; i < pPosX.length; i++) {
			if (posX == pPosX[i] && posY == pPosY[i]) 
				return true;
		}
		return false;
	}
	
	@Override
	public boolean hit(int balaX, int balaY) {
    	// Se comprueba tambien las posiciones de alrededor de la bala para facilitar eliminar a los aliens
    	if (this.posX == balaX && (this.posY == balaY || this.posY == balaY - 1)) 
    		return true;
    	if (justMoved)
	    	if (this.posX == balaX && this.posY == balaY + 1) 
	    		return true;
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
