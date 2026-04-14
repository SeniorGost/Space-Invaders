package modelo.alien;

import java.util.ArrayList;
import java.util.LinkedList;

import modelo.excepciones.JuegoPerdidoException;

public class AlienMultipixel extends Alien {
	
	ArrayList<Alien> listaPixeles;
	int hitboxX;
	int hitboxY;
	
	public AlienMultipixel(int x, int y) {
		super(x, y);
		
		listaPixeles = new ArrayList<Alien>();
		
		hitboxX = 2;
		hitboxY = 2;
		
		// le foo fighter:
		
		listaPixeles.add(new AlienPixel(-2 	+ x, -2	+ y));
		listaPixeles.add(new AlienPixel(2 	+ x, -2	+ y));
		
		listaPixeles.add(new AlienPixel(-1 	+ x, -1	+ y));
		listaPixeles.add(new AlienPixel( 	  x, -1	+ y));
		listaPixeles.add(new AlienPixel(1 	+ x, -1	+ y));
		
		listaPixeles.add(new AlienPixel(-2 	+ x,	  y));
		listaPixeles.add(new AlienPixel( 	  x,	  y));
		listaPixeles.add(new AlienPixel(2 	+ x,	  y));
		
		listaPixeles.add(new AlienPixel(-2 	+ x, 1	+ y));
		listaPixeles.add(new AlienPixel(-1 	+ x, 1	+ y));
		listaPixeles.add(new AlienPixel( 	  x, 1	+ y));
		listaPixeles.add(new AlienPixel(1 	+ x, 1	+ y));
		listaPixeles.add(new AlienPixel(2 	+ x, 1	+ y));
		
		listaPixeles.add(new AlienPixel(-2 	+ x, 2	+ y));
		listaPixeles.add(new AlienPixel(2 	+ x, 2	+ y));
	}
	
	@Override
	public boolean tick(int deltaX, int deltaY) throws JuegoPerdidoException {
		boolean reachedWall = false;
		
		for (Alien a : listaPixeles) {
			reachedWall = a.tick(deltaX, deltaY) || reachedWall;
		}
		
		super.tick(deltaX, deltaY);
		
		return reachedWall;
	}
	
	@Override
	public boolean hit(int[] pixelesX, int[] pixelesY, int pPosX, int pPosY, int hurtboxX, int hurtboxY) {
		if (hitboxCollides(pPosX, pPosY, hurtboxX, hurtboxY)) {			
			for (Alien a : listaPixeles) {
				if(a.hit(pixelesX, pixelesY, pPosX, pPosY, hurtboxX, hurtboxY)) 
					return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean playerCollided(int[] pPosX, int[] pPosY, int offsetX, int offsetY, int hurtboxX, int hurtboxY) {
		
		if (hitboxCollides(offsetX, offsetY, hurtboxX, hurtboxY)) {
			for(Alien a : listaPixeles) {
				boolean isCollision = a.playerCollided(pPosX, pPosY, offsetX, offsetY, hurtboxX, hurtboxY);
				
				if (isCollision)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Dados los margenes de la 'hurtbox' de un jugador y la posicion central de decho jugador, determina si existe
     * una intersección entre la hurtbox del jugador y la hitbox del alien.
     * 
	 * @param pX - Componente x de la posición central de la nave del jugador.
	 * @param pY - Componente y de la posición central de la nave del jugador.
	 * @param hurtboxX - Margen horizontal de la hurtbox de la nave del jugador.
	 * @param hurtboxY - Margen vertical de la hurtbox de la nave del jugador.
	 * @return {@code true} si existe una intersección, {@code false} en caso contrario.
	 */
	private boolean hitboxCollides(int pX, int pY, int hurtboxX, int hurtboxY) {
		// La verdad, no estoy seguro de que calculo geometrico se realiza exactamente aqui debido a que elabore
		// este codigo antes incluso de habernos puesto a programar el programa principal. Unicamente por la posibilidad
		// de que fuesemoms a utilizar hitboxes.
		boolean rdo = false;
		
		int difY = getPosY() - pY;
		if (difY < 0) difY = -difY;		
		int marginY = hitboxY + hurtboxY;
		
		if (difY <= marginY) {
			int difX = getPosX() - pX;
			if (difX < 0) difX = -difX;
			int marginX = hitboxX + hurtboxX;
			
			rdo = difX <= marginX;
		}
		
		return rdo;
	}
	
	@Override
	public LinkedList<Integer> getDisplayX() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		
		for (Alien a: listaPixeles) {
			rdo.add(a.getPosX());
		}
		return rdo;
	}
	@Override
	public LinkedList<Integer> getDisplayY() {
		LinkedList<Integer> rdo = new LinkedList<Integer>();
		
		for (Alien a: listaPixeles) {
			rdo.add(a.getPosY());
		}
		return rdo;
	}
	
	public void disparar() {
		// De momento vacío 
	}
	
}
