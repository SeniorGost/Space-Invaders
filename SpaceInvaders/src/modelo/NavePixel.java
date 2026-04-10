package modelo;

public class NavePixel extends Nave {

	public NavePixel(int pPosX, int pPosY) {
		super(pPosX, pPosY);
	}

	/**
	 * Comprueba que haya mas espacio a su izquierda.
	 * @return {@code true} si hay mas espacio a su izquierda, {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveLeft() {
		return LIMITE_IZQ < getPosX();
	}

	/**
	 * Comprueba que haya mas espacio a su derecha.
	 * @return {@code true} si hay mas espacio a su derecha, {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveRight() {
		return LIMITE_DER > getPosX();
	}

	/**
	 * Comprueba que haya mas espacio encima.
	 * @return {@code true} si hay mas espacio encima, {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveUp() {
		return LIMITE_ARRIBA < getPosY();
	}

	/**
	 * Comprueba que haya mas espacio debajo.
	 * @return {@code true} si hay mas espacio debajo, {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveDown() {
		return LIMITE_ABAJO > getPosY();
	}
	
	@Override
	public void shoot() {
		return;	
	}
}
