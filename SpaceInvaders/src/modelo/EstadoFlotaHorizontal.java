package modelo;

import modelo.excepciones.JuegoPerdidoException;

public class EstadoFlotaHorizontal extends EstadoFlota {

	private boolean direction;
	
	public EstadoFlotaHorizontal(boolean pDirection) {
		direction = pDirection;
	}

	@Override
	public void tick() throws JuegoPerdidoException {
		int dir = -1;
		if (direction)
			dir = 1;
		
		boolean limit = Flota.getFlota().move(dir, 0);
		
		Flota.getFlota().setState(new EstadoFlotaEsperar(direction, limit));
	}
}
