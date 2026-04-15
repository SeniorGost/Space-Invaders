package modelo;

public class BalaFlecha extends BalaJugador {
	public BalaFlecha(int posX, int posY) {
		super();
		
		int[] pX = {0, -1, 1};
		int[] pY = {-1, 0, 0};
		int[] pColor = new int[pY.length];
		
		for (int i = 0; i < pX.length; i++) {
			pColor[i] = Pixel.COLOR_ID_WHITE;
		}
		
		PixelComposite eM = new PixelComposite(posX, posY, pX, pY, pColor);
		eM.setCollisionManager(getCollisionHandler());
		setEntityManager(eM);
	}
}
