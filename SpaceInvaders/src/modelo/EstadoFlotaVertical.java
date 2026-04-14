package modelo;

import modelo.excepciones.JuegoPerdidoException;

public class EstadoFlotaVertical extends EstadoFlota {

	private boolean direction;
	
	public EstadoFlotaVertical(boolean pDirection) {
		direction = pDirection;
	}
	
	public void tick(int[] pixNaveX, int[] pixNaveY, int naveX, int naveY) throws JuegoPerdidoException {
		
		Flota.getFlota().move(0, 1);
		Flota.getFlota().setState(new EstadoFlotaEsperar(!direction, false));
	}
}
