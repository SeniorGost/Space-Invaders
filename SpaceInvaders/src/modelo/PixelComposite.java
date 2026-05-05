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
	public void draw() {
		for (EntityManager p : pixeles)		
			p.draw();
	}
	
	@Override
	public boolean canMoveH(int deltaX) {
		boolean rdo = pixeles.stream().allMatch(p -> p.canMoveH(deltaX));
		return rdo;
	}
	
	@Override
	public boolean canMoveV(int deltaY) {
		boolean rdo = pixeles.stream().allMatch(p -> p.canMoveV(deltaY));
		return rdo;	
	}
	
	@Override
	public void move(int deltaX, int deltaY) {
		for (EntityManager p : pixeles) {
			p.move(deltaX, deltaY);
		}
	}

	@Override
	public boolean isHit(int[] pX, int[] pY) {
		boolean rdo = pixeles.stream().anyMatch(p -> p.isHit(pX, pY));
		return rdo;
	}
	
	public int[] getDisplayX() {
		int[] rdo = pixeles.stream().mapToInt(p -> p.getPosX()).toArray();
		return rdo;
	}	
	public int[] getDisplayY() {
		int[] rdo = pixeles.stream().mapToInt(p -> p.getPosY()).toArray();
		return rdo;
	}
	@Override
	public void enterHitDisplay() {
		for (EntityManager p : pixeles) {
			p.enterHitDisplay();
		}
	}
}
