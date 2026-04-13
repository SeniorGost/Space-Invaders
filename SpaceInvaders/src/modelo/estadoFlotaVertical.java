package modelo;

public class estadoFlotaVertical extends estadoFlota {

	private boolean direction;
	
	public estadoFlotaVertical(boolean pDirection) {
		direction = pDirection;
	}
	
	public void tick(int[] pixNaveX, int[] pixNaveY, int naveX, int naveY) throws JuegoPerdidoException {
		
		Flota.getFlota().move(0, 1);
		Flota.getFlota().setState(new estadoFlotaEsperar(!direction, false));
	}
}
