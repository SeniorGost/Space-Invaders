package modelo;

public class AlienDefault extends Alien {
	public AlienDefault(int posX, int posY) {
		super();
		
		int[] pX = {
				-2, 2, 
				-1, 0, 1, 
				-2, -1, 0, 1, 2, 
				-2, -1, 0, 1, 2, 
				-2, 2
		};
		int[] pY = {
				-2, -2, 
				-1, -1, -1,
				0, 0, 0, 0, 0,
				1, 1, 1, 1, 1, 
				2, 2
		};
		int[] pColor = new int[pY.length];
		
		for (int i = 0; i < pX.length; i++) {
			if (i == 5 || i == 6 || i == 8 || i == 9)
				pColor[i] = Pixel.COLOR_ID_WHITE;
			else
				pColor[i] = Pixel.COLOR_ID_GREEN;			
		}
		
		PixelComposite eM = new PixelComposite(posX, posY, pX, pY, pColor);
		eM.setCollisionManager(getCollisionHandler());
		setEntityManager(eM);
	}
}
