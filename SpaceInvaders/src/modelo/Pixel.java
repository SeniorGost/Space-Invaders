package modelo;

import java.util.Observable;

public class Pixel extends Observable implements EntityManager {

	public static final int COLOR_ID_WHITE = 0;
	public static final int COLOR_ID_GREEN = 1;
	public static final int COLOR_ID_BLUE = 2;
	public static final int COLOR_ID_RED = 3;
	
	public static final int COLOR_ID_GREEN_CUERPO = 4;
	public static final int COLOR_ID_GREEN_CEJA = 5;
	public static final int COLOR_ID_GREEN_NARIZ = 6;
	public static final int COLOR_ID_GREEN_DETALLES = 7;
	
	public static final int COLOR_ID_BLUE_FRENTE = 8;
	public static final int COLOR_ID_BLUE_FONDO = 9;
	public static final int COLOR_ID_BLUE_BRAZOS = 10;
	public static final int COLOR_ID_BLUE_DETALLES = 11;
	
	public static final int COLOR_ID_RED_FRENTE = 12;
	public static final int COLOR_ID_RED_FONDO = 13;
	public static final int COLOR_ID_RED_DETALLES = 14;
	
	
	
	
	private int posX; 
	private int posY; 
	private int colorId; 
	
	public Pixel(int pPosX, int pPosY, int pColor) {
		posX = pPosX;
		posY = pPosY;
		colorId = pColor;
		
		addObserver(Modelo.getModelo());
	}
	@Override
	public boolean tick() {
		setChanged();
		notifyObservers(new int[] {posX, posY, colorId});
		return false;
	}

	@Override
	public boolean canMoveH(int deltaX) {
		int newX = posX + deltaX;
		if (newX < 0 || newX > Modelo.getModelo().getWidth() - 1)
			return false;
		return true;
	}
	
	@Override
	public boolean canMoveV(int deltaY) {
		int newY = posY + deltaY;
		if (newY < 0 || newY > Modelo.getModelo().getHeight() - 1)
			return false;
		return true;
	}
	
	@Override
	public void move(int deltaX, int deltaY) {
		posX += deltaX;
		posY += deltaY;
	}

	@Override
	public boolean canCollide(int offsetX, int offsetY, int hurtBoxX, int hurtBoxY) {
		boolean rdo = false;
		
		int difY = posY - offsetY;
		if (difY < 0) difY = -difY;		
		int marginY = 0 + hurtBoxY;
		
		if (difY <= marginY) {
			int difX = posX - offsetX;
			if (difX < 0) difX = -difX;
			int marginX = 0 + hurtBoxX;
			
			rdo = difX <= marginX;
		}
		
		return rdo;
	}

	@Override
	public boolean isHit(int[] pX, int[] pY) {
		for (int i = 0; i < pX.length; i++) {
			if (pX[i] == posX && pY[i] == posY)
				return true;
		}
		
		return false;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}

}
