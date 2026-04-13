package modelo.naves;

/**
 * Entre sus primos cromaticos, el verde es el más diplomatico.
 */
public class NaveGreen extends NaveMultipixel {
	/**
	 * nave
	 */
	public NaveGreen() {
		super(-3, new int[] {0, 1});
		
		int posX = getPosX();
		int posY = getPosY();
		
		// El 'pixel art' de la nave se hace asi. Esto tambien define su 'hitbox'.
		pixeles.add(new NavePixel(-3 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(3 	+ posX, -3	+ posY));
		
		pixeles.add(new NavePixel(-2 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, -2	+ posY));

		pixeles.add(new NavePixel(-2 	+ posX, -1	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -1	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, -1	+ posY));
		
		pixeles.add(new NavePixel(-3 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(-2 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(3 	+ posX, 0	+ posY));
			
		pixeles.add(new NavePixel(-3 	+ posX, 1	+ posY));
		pixeles.add(new NavePixel(3 	+ posX, 1	+ posY));
		
		pixeles.add(new NavePixel(-3 	+ posX, 2	+ posY));
		pixeles.add(new NavePixel(-2 	+ posX, 2	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, 2	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, 2	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 2	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, 2	+ posY));
		pixeles.add(new NavePixel(3 	+ posX, 2	+ posY));
		
		pixeles.add(new NavePixel(-2 	+ posX, 3	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, 3	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, 3	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 3	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, 3	+ posY));
		
	}

}
