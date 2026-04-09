package modelo;

import java.util.ArrayList;

public class alienMultipixel extends Alien {
	
	ArrayList<Alien> listaPixeles;
	int hitboxX;
	int hitboxY;
	
	public alienMultipixel(int x, int y) {
		super(x, y);
		
		listaPixeles = new ArrayList<Alien>();
		
		hitboxX = 2;
		hitboxY = 2;
		
		for (int i = -2; i <= 2; i++) 
			for (int j = -2; j <= 2; j++)
				if (i == j || i == -j)
					listaPixeles.add(new alienPixel(x + i, y + j));
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean tick(int deltaX, int deltaY) throws JuegoPerdidoException {
		boolean reachedWall = false;
		
		for (Alien a : listaPixeles) {
			reachedWall = a.tick(deltaX, deltaY) || reachedWall;
		}
		
		return reachedWall;
	}
	
	@Override
	public boolean hit(int balaX, int balaY) {
		for (Alien a : listaPixeles) {
			if(a.hit(balaX, balaY)) 
				return true;
		}
		return false;
	}
	
	@Override
	public boolean playerCollided(int[] pPosX, int[] pPosY, int offsetX, int offsetY, int hurtboxX, int hurtboxY) {
		
		if (hitboxCollides(offsetX, offsetY, hurtboxX, hurtboxY)) {
			for( Alien a : listaPixeles) {
				boolean isCollision = a.playerCollided(pPosX, pPosY, offsetX, offsetY, hurtboxX, hurtboxY);
				
				if (isCollision)
					return true;
			}
		}
		return false;
	}
	
	private boolean hitboxCollides(int pX, int pY, int hurtboxX, int hurtboxY) {
		boolean rdo = false;
		
		int difY = posY - pY;
		if (difY < 0) difY = -difY;		
		int marginY = hitboxY + hurtboxY;
		
		if (difY <= marginY) {
			int difX = posX - pX;
			if (difX < 0) difX = -difX;
			int marginX = hitboxX + hurtboxX;
			
			rdo = difX <= marginX;
		}
		
		return rdo;
	}
	
	public void disparar() {
		// De momento vacío 
	}
	
}
