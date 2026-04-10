package modelo;

/**
 * Entre sus primos cromaticos, el verde es el más diplomatico.
 */
public class NaveGreen extends NaveMultipixel {
	/**
	 * nave
	 */
	public NaveGreen() {
		super();
		
		// Es obligatorio inicializar estos atributos
		bulletOffset = -2;
		bulletType = 0;
		
		int posX = getPosX();
		int posY = getPosY();
		
		// El 'pixel art' de la nave se hace asi. Esto tambien define su 'hitbox'.
		pixeles.add(new NavePixel(0 	+ posX, -2	+ posY));
		
		pixeles.add(new NavePixel(-1 	+ posX, -1	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -1	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, -1	+ posY));
		
		pixeles.add(new NavePixel(-2 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, 0	+ posY));
		
		pixeles.add(new NavePixel(-1 	+ posX, 1	+ posY));		
		pixeles.add(new NavePixel(1 	+ posX, 1	+ posY));		
	}

}
