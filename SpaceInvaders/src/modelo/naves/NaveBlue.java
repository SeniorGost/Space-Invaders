package modelo.naves;

public class NaveBlue extends NaveMultipixel {

	public NaveBlue() {
		super(-4);
		
		int posX = getPosX();
		int posY = getPosY();
		
		//Para intentar que el la vista, cada vez que se recargue la página sea más natural se me ha ocurrido pintarlo de iquierda a derecha en lugar de arriba a abajo
		pixeles.add(new NavePixel(-3 	+ posX, 0	+ posY));
		
		pixeles.add(new NavePixel(-2 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(-2 	+ posX, 3	+ posY));
		
		pixeles.add(new NavePixel(-1 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(-1 	+ posX, 2	+ posY));
		
		pixeles.add(new NavePixel(0 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, -1	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(0 	+ posX, 1	+ posY));
		
		pixeles.add(new NavePixel(1 	+ posX, -3	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, -2	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(1 	+ posX, 2	+ posY));
		
		pixeles.add(new NavePixel(2 	+ posX, 0	+ posY));
		pixeles.add(new NavePixel(2 	+ posX, 3	+ posY));
		
		pixeles.add(new NavePixel(3 	+ posX, 0	+ posY));		
	}

}
