package modelo;

import modelo.excepciones.JuegoPerdidoException;

public class EstadoFlotaVertical extends EstadoFlota {

	private boolean direction;
	
	public EstadoFlotaVertical(boolean pDirection) {
		direction = pDirection;
	}
	
	@Override
	public void tick() throws JuegoPerdidoException {
		if (Flota.getFlota().isEmpty()) {
			Flota.getFlota().setState(new EstadoFlotaPostFase1());
			return;
		}	
		
		Flota.getFlota().move(0, 1);
		Flota.getFlota().setState(new EstadoFlotaEsperar(!direction, false));
		
		Flota.getFlota().draw();
	}
}
