package modelo;

public interface EntityManager {

	/**
	 * @return {@code true} si ha collisionado con algo, {@code false} en caso contrario.
	 */
	public void draw();
	public boolean canMoveH(int deltaX);
	public boolean canMoveV(int deltaY);
	public void move(int deltaX, int deltaY);
	public boolean isHit(int[] pX, int[] pY);
	public void enterHitDisplay();
	
}
