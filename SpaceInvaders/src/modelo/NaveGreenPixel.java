package modelo;

public class NaveGreenPixel extends Nave {

	public NaveGreenPixel(int pPosX, int pPosY) {
		super();
		
		posX = pPosX;
		posY = pPosY;
	}

	@Override
	protected boolean canMoveLeft() {
		System.out.println("check");
		return LIMITE_IZQ < posX;
	}

	@Override
	protected boolean canMoveRight() {
		return LIMITE_DER > posX;
	}

	@Override
	protected boolean canMoveUp() {
		return LIMITE_ARRIBA < posY;
	}

	@Override
	protected boolean canMoveDown() {
		return LIMITE_ABAJO > posY;
	}
	
	@Override
	public void shoot() {
		return;	
	}
}
