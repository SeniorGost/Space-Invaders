package modelo;

import modelo.excepciones.JuegoCambiadoException;

public class CHAlien implements CollisionHandler {

	@Override
	public boolean collide(int offsetX, int offsetY, int hurtboxX, int hurtboxY, int[] pX, int[] pY) {
		boolean rdo = false;
		
		try {			
			rdo = Jugador.getJugador().hit(offsetX, offsetY, hurtboxX, hurtboxY, pX, pY);
		} catch (JuegoCambiadoException e) {
			Modelo.getModelo().acabarPartida(e.getTipo());
		}
		return rdo;
	}	
}
