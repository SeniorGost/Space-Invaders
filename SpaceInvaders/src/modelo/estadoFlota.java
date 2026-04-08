package modelo;

public abstract class estadoFlota {

	protected estadoFlota() {
		
	}
	
	public abstract void tick(int jugadorX, int jugadorY) throws JuegoPerdidoException;
}
