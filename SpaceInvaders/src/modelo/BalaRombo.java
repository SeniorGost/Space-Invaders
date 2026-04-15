package modelo;

public class BalaRombo extends BalaJugador {
	public BalaRombo(int posX, int posY) {
		super();
		
		int[] pX = {0, -1, 0, 1, -2, -1, 0, 1, 2, -1, 0, 1, 0};
		int[] pY = {-2, -1, -1, -1, 0, 0, 0, 0, 0, 1, 1, 1, 2};
		int[] pColor = new int[pY.length];
		
		for (int i = 0; i < pX.length; i++) {
			pColor[i] = Pixel.COLOR_ID_WHITE;
		}
		
		PixelComposite eM = new PixelComposite(posX, posY, pX, pY, pColor);
		eM.setCollisionManager(getCollisionHandler());
		setEntityManager(eM);
	}
}
