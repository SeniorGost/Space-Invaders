package modelo;

import modelo.excepciones.JuegoPerdidoException;

public abstract class EstadoFlota {

	protected EstadoFlota() {
		
	}
	
	public abstract void tick() throws JuegoPerdidoException;
}
