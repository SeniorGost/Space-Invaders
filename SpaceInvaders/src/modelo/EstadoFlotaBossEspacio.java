package modelo;

import modelo.excepciones.JuegoGanadoException;
import modelo.excepciones.JuegoPerdidoException;

public class EstadoFlotaBossEspacio extends EstadoFlota {

	private int count = 0;
	
	@Override
	public void tick() throws JuegoPerdidoException {
		if (Flota.getFlota().isEmpty()) {
			Modelo.getModelo().acabarPartida(JuegoGanadoException.TIPO);
			return;
		}	
		
		count++;
		
		if (count > 4) {
			count  = 0;
			Flota.getFlota().move(0, 1);
		}
		
		Flota.getFlota().draw();
	}

}
