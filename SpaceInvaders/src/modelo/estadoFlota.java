package modelo;

import modelo.excepciones.JuegoPerdidoException;

public abstract class estadoFlota {

	protected estadoFlota() {
		
	}
	
	public abstract void tick(int[] pixNaveX, int[] pixNaveY, int naveX, int naveY) throws JuegoPerdidoException;
}
