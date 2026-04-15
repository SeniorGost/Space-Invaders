package modelo;

public class NaveBlue extends Nave {
	public NaveBlue() {
		super();
		
		int[] pX = {
				-1, 0, 1, 
				-2, -1, 0, 1, 2,
				-2, -1, 0, 1, 2,
				-3, 0, 3,
				-3, -1, 0, 1, 3,
				-1, 1,
				-1, 1
		};
		int[] pY = {
				-3, -3, -3,
				-2, -2, -2, -2, -2, 
				-1, -1, -1, -1, -1, 
				0, 0, 0, 
				1, 1, 1, 1, 1,
				2, 2, 
				3, 3
				
		};
		int[] pColor = {
				Pixel.COLOR_ID_BLUE_FONDO, Pixel.COLOR_ID_BLUE_FONDO, Pixel.COLOR_ID_BLUE_FONDO, 
				Pixel.COLOR_ID_BLUE_BRAZOS, Pixel.COLOR_ID_BLUE_FRENTE, Pixel.COLOR_ID_BLUE_DETALLES, Pixel.COLOR_ID_BLUE_FRENTE, Pixel.COLOR_ID_BLUE_BRAZOS, 
				Pixel.COLOR_ID_BLUE_BRAZOS, Pixel.COLOR_ID_BLUE_FRENTE, Pixel.COLOR_ID_BLUE_FRENTE, Pixel.COLOR_ID_BLUE_FRENTE, Pixel.COLOR_ID_BLUE_BRAZOS, 
				Pixel.COLOR_ID_BLUE_BRAZOS, Pixel.COLOR_ID_BLUE_FRENTE, Pixel.COLOR_ID_BLUE_BRAZOS, 
				Pixel.COLOR_ID_BLUE_BRAZOS, Pixel.COLOR_ID_BLUE_FONDO, Pixel.COLOR_ID_BLUE_FRENTE, Pixel.COLOR_ID_BLUE_FONDO, Pixel.COLOR_ID_BLUE_BRAZOS, 
				Pixel.COLOR_ID_BLUE_FONDO, Pixel.COLOR_ID_BLUE_FONDO, 
				Pixel.COLOR_ID_BLUE_FONDO, Pixel.COLOR_ID_BLUE_FONDO
		};
		
		PixelComposite eM = new PixelComposite(50, 55, pX, pY, pColor);
		eM.setCollisionManager(getCollisionHandler());
		setEntityManager(eM);
		
	}
}
