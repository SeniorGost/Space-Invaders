package modelo;

public interface EntityManager {

	/**
	 * @return {@code true} si ha collisionado con algo, {@code false} en caso contrario.
	 */
	public boolean tick();
	public boolean canMoveH(int deltaX);
	public boolean canMoveV(int deltaY);
	public void move(int deltaX, int deltaY);
	public boolean canCollide(int offsetX, int offsetY, int hurtBoxX, int hurtBoxY);
	public boolean isHit(int[] pX, int[] pY);
	
}
