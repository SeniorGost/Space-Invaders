package modelo;

public class NaveBlue extends NaveMultipixel {

	public NaveBlue() {
		super(-3, new int[] {0, 1});
		
		int posX = getPosX();
		int posY = getPosY();
		
		pixeles.add(new NavePixel(-1 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, -3	+ posY));
		
		pixeles.add(new NavePixel(-1 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, -2	+ posY));
		
		pixeles.add(new NavePixel(0 	+ posX, -1	+ posY));
		
		pixeles.add(new NavePixel(-3 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(-2 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(3 	+ posX, 0	+ posY));
		
		pixeles.add(new NavePixel(0 	+ posX, 1	+ posY));
		
		pixeles.add(new NavePixel(-1 	+ posX, 2	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 2	+ posY));
		
		pixeles.add(new NavePixel(-2 	+ posX, 3	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, 3	+ posY));
		
		
	}

}
