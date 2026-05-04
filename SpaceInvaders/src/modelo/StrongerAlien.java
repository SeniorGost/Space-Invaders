package modelo;

public class StrongerAlien extends Alien {
	public StrongerAlien(int posX, int posY) {
		super(10);
		
		int[] pX = {
				0,
				-2, 0, 2,
				-2, -1, 0, 1, 2,
				-2, -1, 0, 1, 2,
				-2, -1, 1, 2
		};
		int[] pY = {
				-2,
				-1, -1, -1,
				0, 0, 0, 0, 0,
				1, 1, 1, 1, 1, 
				2, 2, 2, 2
		};
		int[] pColor = new int[pY.length];
		
		for (int i = 0; i < pX.length; i++) {
			if (i == 9 || i == 10 || i == 12 || i == 13)
				pColor[i] = Pixel.COLOR_ID_WHITE;
			else
				pColor[i] = Pixel.COLOR_ID_STRONGER_GREEN;			
		}
		
		setEntityManager(posX, posY, pX, pY, pColor);
	}
}
