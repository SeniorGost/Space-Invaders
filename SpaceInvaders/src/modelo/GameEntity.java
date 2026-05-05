package modelo;

public abstract class GameEntity {
	
	private PixelComposite entityManager;
	private CollisionHandler curCH;
	
	private int offsetX;
	private int offsetY;
	private int hitboxX;
	private int hitboxY;
	
	private int health;
	
	public GameEntity(int pHealth) {
		health = pHealth;
	}
	
	public void setEntityManager(int posX, int posY, int[] dispX, int[] dispY, int[] colors) {
		entityManager = new PixelComposite();
		
		offsetX = posX;
		offsetY = posY;
		
		for (int i = 0; i < dispX.length; i++) {			
			int absX = Math.abs(dispX[i]);
			int absY = Math.abs(dispY[i]);
			
			if (hitboxX < absX)
				hitboxX = absX;
			
			if (hitboxY < absY)
				hitboxY = absY;
			
			entityManager.addPixel(dispX[i] + posX, dispY[i] + posY, colors[i]);
		}
	}
	
	/**
	 * Actualiza el display en pantalla
	 */
	public void draw() {
		entityManager.draw();
	}
	/**
	 * @return {@code true} si esta en collision con otro {@code GameEntity},
	 * {@code false} en caso contrario.
	 */
	public boolean collide() {
		return curCH.collide(offsetX, offsetY, hitboxX, hitboxY, entityManager.getDisplayX(), entityManager.getDisplayY());
	}
	
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
	
	public boolean canMoveH(int deltaX) {
		return entityManager.canMoveH(deltaX);
	}
	public boolean canMoveV(int deltaY) {
		return entityManager.canMoveV(deltaY);		
	}
	public void move(int deltaX, int deltaY) {
		entityManager.move(deltaX, deltaY);
		offsetX += deltaX;
		offsetY += deltaY;
	}
	public boolean isHit(int[] pX, int[] pY) {
		return entityManager.isHit(pX, pY);
	}
	public void hit() {
		health--;
		entityManager.enterHitDisplay();
	}
	public boolean isDead() {
		return health == 0;
	}
	protected void setCollisionHandler(CollisionHandler pCH) {
		curCH = pCH;
	}
	public int getOffsetX() {
		return offsetX;
	}	
	public int getOffsetY() {
		return offsetY;
	}
	
	protected CollisionHandler getCollisionHandler() {
		return curCH;
	}
}
