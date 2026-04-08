package modelo;

public class estadoFlotaHorizontal extends estadoFlota {

	private boolean direction;
	
	public estadoFlotaHorizontal(boolean pDirection) {
		direction = pDirection;
	}
	
	public void tick(int jugadorX, int jugadorY) throws JuegoPerdidoException{
		int dir = -1;
		if (direction)
			dir = 1;
		
		boolean limit = Flota.getFlota().move(dir, 0, jugadorX, jugadorY);
		
		Flota.getFlota().setState(new estadoFlotaEsperar(direction, limit));
	}
}
