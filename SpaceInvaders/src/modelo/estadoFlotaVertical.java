package modelo;

public class estadoFlotaVertical extends estadoFlota {

	private boolean direction;
	
	public estadoFlotaVertical(boolean pDirection) {
		direction = pDirection;
	}
	
	public void tick(int jugadorX, int jugadorY) throws JuegoPerdidoException {
		
		Flota.getFlota().move(0, 1, jugadorX, jugadorY);
		Flota.getFlota().setState(new estadoFlotaEsperar(!direction, false));
	}
}
