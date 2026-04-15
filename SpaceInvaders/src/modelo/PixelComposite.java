package modelo;

import java.util.ArrayList;

public class PixelComposite implements EntityManager {
	
	private ArrayList<Pixel> pixeles;
	
	private int offsetX;
	private int offsetY;
	private int hitboxX;
	private int hitboxY;
	
	private CollisionHandler collisionHandler;
	
	public PixelComposite(int pOffsetX, int pOffsetY, int[] pX, int[] pY, int[] pColors) {
		pixeles = new ArrayList<Pixel>();
		
		hitboxX = -1;
		hitboxY = -1;
		
		offsetX = pOffsetX;
		offsetY = pOffsetY;
		
		for (int i = 0; i < pX.length; i++) {
			int absX = Math.abs(pX[i]);
			int absY = Math.abs(pY[i]);
			
			if (hitboxX < absX)
				hitboxX = absX;
			
			if (hitboxY < absY)
				hitboxY = absY;
			
			int posX = pX[i] + pOffsetX;
			int posY = pY[i] + pOffsetY;
			
			int color = pColors[i];
			
			pixeles.add(new Pixel(posX, posY, color));
		}
	}
	
	@Override
	public boolean tick() {
		boolean rdo = false;
		
		int[] pX = new int[pixeles.size()];
		int[] pY = new int[pixeles.size()];
		
		for (int i = 0; i < pixeles.size(); i++) {
			Pixel p = pixeles.get(i);
			pX[i] = p.getPosX();
			pY[i] = p.getPosY();
		}
		
		rdo = collisionHandler.collide(offsetX, offsetY, hitboxX, hitboxY, pX, pY);
		
		if (!rdo) {
			for (EntityManager p : pixeles) {			
				p.tick();
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
		offsetX += deltaX;
		offsetY += deltaY;
	}

	@Override
	public boolean canCollide(int pOffsetX, int pOffsetY, int hurtBoxX, int hurtBoxY) {
		boolean rdo = false;
		
		int difY = offsetY - pOffsetY;
		if (difY < 0) difY = -difY;		
		int marginY = hitboxY + hurtBoxY;
		
		if (difY <= marginY) {
			int difX = offsetX - pOffsetX;
			if (difX < 0) difX = -difX;
			int marginX = hitboxX + hurtBoxX;
			
			rdo = difX <= marginX;
		}
		
		return rdo;
	}
	
	@Override
	public boolean isHit(int[] pX, int[] pY) {
		for (EntityManager p : pixeles) {
			if (p.isHit(pX, pY))
				return true;
		}

		return false;
	}
	
	public int getOffsetX() {
		return offsetX;
	}	
	public int getOffsetY() {
		return offsetY;
	}
	
	public void setCollisionManager(CollisionHandler pCollisionManager) {
		collisionHandler = pCollisionManager;
	}
}
