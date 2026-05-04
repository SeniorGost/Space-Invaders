package modelo;

import java.util.Random;

public class AlienDefault extends Alien {
	Random rng;
	private static final int SHOOT_CHANCE = 10;
	
	public AlienDefault(int posX, int posY) {
		super(1);
		rng = new Random();
		
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
		setEntityManager(posX, posY, pX, pY, pColor);
	}
	
	@Override
	public void move(int deltaX, int deltaY) {
		if (rng.nextInt(SHOOT_CHANCE) == 0)
			ArtilleriaEnemigo.getArtilleria().crearBala(getOffsetX(), getOffsetY() + 4);
		
		super.move(deltaX, deltaY);
	}
}
