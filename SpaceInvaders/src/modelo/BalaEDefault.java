package modelo;

public class BalaEDefault extends BalaEnemigo {
	
	public BalaEDefault(int posX, int posY) {
		super(1);
		
		int[] pX = {0, 0, -1, 1, 0};
		int[] pY = {-2, -1, 0, 0, 1};
		int[] pColor = new int[pY.length];
		
		for (int i = 0; i < pX.length; i++) {
			pColor[i] = Pixel.COLOR_ID_WHITE;
		}
		setEntityManager(posX, posY, pX, pY, pColor);
	}
	
	
}
