package modelo;

import java.awt.Color;

public class NaveGreen extends Nave {
	public NaveGreen() {
		super();
		
		int[] pX = {
				-3, -1, 0, 1, 3,
				-2, -1, 0, 1, 2,
				-2, -1, 0, 1, 2,
				-3, -2, -1, 0, 1, 2, 3,
				-3, -2, -1, 0, 1, 2, 3,				
				-3, -2, -1, 0, 1, 2, 3,
				-2, -1, 0, 1, 2
		};
		int[] pY = {
				-3, -3, -3, -3, -3,
				-2, -2, -2, -2, -2,
				-1, -1, -1, -1, -1,
				0, 0, 0, 0, 0, 0, 0,
				1, 1, 1, 1, 1, 1, 1,
				2, 2, 2, 2, 2, 2, 2,
				3, 3, 3, 3, 3
		};
		int[] pColor = {
				Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, 
				Pixel.COLOR_ID_GREEN_CEJA, Pixel.COLOR_ID_GREEN_CEJA, Pixel.COLOR_ID_GREEN_NARIZ, Pixel.COLOR_ID_GREEN_CEJA, Pixel.COLOR_ID_GREEN_CEJA, 
				Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_DETALLES, Pixel.COLOR_ID_GREEN_NARIZ, Pixel.COLOR_ID_GREEN_DETALLES, Pixel.COLOR_ID_GREEN_CUERPO, 
				Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_NARIZ, Pixel.COLOR_ID_GREEN_NARIZ, Pixel.COLOR_ID_GREEN_NARIZ, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO,
				Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_DETALLES, Pixel.COLOR_ID_GREEN_DETALLES, Pixel.COLOR_ID_GREEN_DETALLES, Pixel.COLOR_ID_GREEN_DETALLES, Pixel.COLOR_ID_GREEN_DETALLES, Pixel.COLOR_ID_GREEN_CUERPO,
				Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO,
				Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO, Pixel.COLOR_ID_GREEN_CUERPO
		};
		
		PixelComposite eM = new PixelComposite(50, 55, pX, pY, pColor);
		eM.setCollisionManager(getCollisionHandler());
		setEntityManager(eM);
		
	}
}
