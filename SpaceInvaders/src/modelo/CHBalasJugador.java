package modelo;

public class CHBalasJugador implements CollisionHandler {

	@Override
	public boolean collide(int offsetX, int offsetY, int hurtboxX, int hurtboxY, int[] pX, int[] pY) {
		return Flota.getFlota().hit(offsetX, offsetY, hurtboxX, hurtboxY, pX, pY);
	}

}
