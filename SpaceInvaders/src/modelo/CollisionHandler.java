package modelo;

public interface CollisionHandler {
	public boolean collide(int offsetX, int offsetY, int hurtboxX, int hurtboxY, int[] pX, int[] pY);
}
