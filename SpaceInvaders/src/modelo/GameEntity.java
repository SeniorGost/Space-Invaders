package modelo;

public abstract class GameEntity {
	
	private PixelComposite entityManager;
	private CollisionHandler curCH;
	
	public GameEntity() {}
	
	public void setEntityManager(PixelComposite pEntityManager) {
		entityManager = pEntityManager;
	}
	
	/**
	 * Actualiza el display en pantalla
	 * 
	 * @return {@code true} si esta en collision con otro {@code GameEntity},
	 * {@code false} en caso contrario.
	 */
	public boolean tick() {
		return entityManager.tick();
	}
	
	public boolean canMoveH(int deltaX) {
		return entityManager.canMoveH(deltaX);
	}
	public boolean canMoveV(int deltaY) {
		return entityManager.canMoveV(deltaY);		
	}
	public void move(int deltaX, int deltaY) {
		entityManager.move(deltaX, deltaY);
	}
	public boolean canCollide(int offsetX, int offsetY, int hurtBoxX, int hurtBoxY) {
		return entityManager.canCollide(offsetX, offsetY, hurtBoxX, hurtBoxY);
	}
	public boolean isHit(int[] pX, int[] pY) {
		return entityManager.isHit(pX, pY);
	}
	protected void setCollisionHandler(CollisionHandler pCH) {
		curCH = pCH;
	}
	public int getOffsetX() {
		return entityManager.getOffsetX();
	}	
	public int getOffsetY() {
		return entityManager.getOffsetY();
	}
	
	protected CollisionHandler getCollisionHandler() {
		return curCH;
	}
}
