package modelo.naves;

import modelo.Pixel;

public class NaveRed extends Nave {
	
	public NaveRed() {
	super(1);
	
	int[] pX = {
			0, 
			-1, 0, 1, 
			-1, 0, 1, 
			-2, -1, 0, 1, 2, 
			-2, -1, 0, 1, 2, 
			-2, 0, 2, 
			-2, 2
	};
	int[] pY = {
			-3, 
			-2, -2, -2, 
			-1, -1, -1, 
			0, 0, 0, 0, 0,
			1, 1, 1, 1, 1, 
			2, 2, 2, 
			3, 3 
	};
	int[] pColor = new int[] {
		Pixel.COLOR_ID_RED_FONDO, 
		Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_FONDO, Pixel.COLOR_ID_RED_FRENTE, 
		Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_DETALLES, Pixel.COLOR_ID_RED_FRENTE, 
		Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_DETALLES, Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_FRENTE,
		Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_FONDO, Pixel.COLOR_ID_RED_DETALLES, Pixel.COLOR_ID_RED_FONDO, Pixel.COLOR_ID_RED_FRENTE,
		Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_DETALLES, Pixel.COLOR_ID_RED_FRENTE, 
		Pixel.COLOR_ID_RED_FRENTE, Pixel.COLOR_ID_RED_FRENTE, 
	};
	
	setEntityManager(50, 55, pX, pY, pColor);
	}
}
