package modelo;

import java.util.ArrayList;

public class PixelComposite implements EntityManager {
	
	private ArrayList<Pixel> pixeles;
	
	public PixelComposite() {
		pixeles = new ArrayList<Pixel>();
	}
	
	public void addPixel(int pX, int pY, int pColor) {
		pixeles.add(new Pixel(pX, pY, pColor));
	}
	
	@Override
	public boolean draw() {
		boolean rdo = false;
		
		if (!rdo) {
			for (EntityManager p : pixeles) {			
				p.draw();
			}
		}
		
		return rdo;
	}
	
	@Override
	public boolean canMoveH(int deltaX) {
		for (EntityManager p : pixeles) {
			if (!p.canMoveH(deltaX))
				return false;
		}
		return true;	
	}
	
	@Override
	public boolean canMoveV(int deltaY) {
		for (EntityManager p : pixeles) {
			if (!p.canMoveV(deltaY))
				return false;
		}
		return true;	
	}
	
	@Override
	public void move(int deltaX, int deltaY) {
		for (EntityManager p : pixeles) {
			p.move(deltaX, deltaY);
		}
	}

	
	@Override
	public boolean isHit(int[] pX, int[] pY) {
		for (EntityManager p : pixeles) {
			if (p.isHit(pX, pY))
				return true;
		}

		return false;
	}
	
	public int[] getDisplayX() {
		int[] pX = new int[pixeles.size()];
		
		for (int i = 0; i < pixeles.size(); i++) {
			Pixel p = pixeles.get(i);
			pX[i] = p.getPosX();
		}
		return pX;
	}	
	public int[] getDisplayY() {
		int[] pY = new int[pixeles.size()];
		for (int i = 0; i < pixeles.size(); i++) {
			Pixel p = pixeles.get(i);
			pY[i] = p.getPosY();
		}
		return pY;
	}
	@Override
	public void enterHitDisplay() {
		for (EntityManager p : pixeles) {
			p.enterHitDisplay();
		}
	}
}
