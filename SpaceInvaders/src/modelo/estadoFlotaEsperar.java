package modelo;

public class estadoFlotaEsperar extends estadoFlota {

	private int tickCount;
	private boolean direction; // false = izquierda, true = derecha
	private boolean fallNext;
	
	public estadoFlotaEsperar(boolean pDirection, boolean pFallNext) {
		tickCount = 0;
		direction = pDirection;
		fallNext = pFallNext;
	}
	
	public void tick(int jugadorX, int jugadorY) {
		tick();
	}
	
	public void tick() {
		tickCount++;
		
		if (tickCount == 4) {
			if (fallNext)
				Flota.getFlota().setState(new estadoFlotaVertical(direction));
			else
				Flota.getFlota().setState(new estadoFlotaHorizontal(direction));
		}
	}
	
}
