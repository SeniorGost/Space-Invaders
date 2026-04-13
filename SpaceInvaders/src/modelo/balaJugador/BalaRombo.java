package modelo.balaJugador;

public class BalaRombo extends BalaJugadorMultipixel {
	public BalaRombo(int posX, int pPosY) {
		super(posX, pPosY - 3, 2, 2);
		int posY = getPosY();
		
		addPixel(new BalaJugadorPixel(0		+ posX, -2	+ posY));
		
		addPixel(new BalaJugadorPixel(-1	+ posX, -1	+ posY));
		addPixel(new BalaJugadorPixel(0		+ posX, -1	+ posY));
		addPixel(new BalaJugadorPixel(1		+ posX, -1	+ posY));
		
		addPixel(new BalaJugadorPixel(-2	+ posX, 0	+ posY));
		addPixel(new BalaJugadorPixel(-1	+ posX, 0	+ posY));
		addPixel(new BalaJugadorPixel(0		+ posX, 0	+ posY));
		addPixel(new BalaJugadorPixel(1		+ posX, 0	+ posY));
		addPixel(new BalaJugadorPixel(2		+ posX, 0	+ posY));
		
		addPixel(new BalaJugadorPixel(-1	+ posX, 1	+ posY));
		addPixel(new BalaJugadorPixel(0		+ posX, 1	+ posY));
		addPixel(new BalaJugadorPixel(1		+ posX, 1	+ posY));
		
		addPixel(new BalaJugadorPixel(0		+ posX, 2	+ posY));		
	}
}
