package modelo;

import modelo.excepciones.JuegoPerdidoException;

public class EstadoFlotaPostFase1 extends EstadoFlota {
	
	private static final int TRANS_TIME = 60;
	private int counter = 0;
	
	@Override
	public void tick() throws JuegoPerdidoException {
		counter++;
		
		if (counter >= TRANS_TIME) {
			Flota.getFlota().setBoss();
			Flota.getFlota().setState(new EstadoFlotaBossEspacio());
		}
	}
}
