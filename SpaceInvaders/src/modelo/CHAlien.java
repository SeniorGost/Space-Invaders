package modelo;

public class CHAlien implements CollisionHandler {

	@Override
	public boolean collide(int offsetX, int offsetY, int hurtboxX, int hurtboxY, int[] pX, int[] pY) {
		return Jugador.getJugador().hit(offsetX, offsetY, hurtboxX, hurtboxY, pX, pY);
	}

}
