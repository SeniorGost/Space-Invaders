package modelo;

public class EstadoFlotaEsperar extends EstadoFlota {

	private int tickCount;
	private boolean direction; // false = izquierda, true = derecha
	private boolean fallNext;
	
	public EstadoFlotaEsperar(boolean pDirection, boolean pFallNext) {
		tickCount = 0;
		direction = pDirection;
		fallNext = pFallNext;
	}
	
	public void tick() {
		tickCount++;
		
		if (tickCount == 4) {
			if (fallNext)
				Flota.getFlota().setState(new EstadoFlotaVertical(direction));
			else
				Flota.getFlota().setState(new EstadoFlotaHorizontal(direction));
		}
	}
	
}
