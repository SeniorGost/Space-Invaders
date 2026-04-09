package modelo;

import java.util.ArrayList;

public class NaveGreen extends Nave {

	private boolean willShoot;
	private ArrayList<Nave> pixeles;
	
	public NaveGreen() {
		super();
		
		willShoot = false;
		
		pixeles = new ArrayList<Nave>();
		
		pixeles.add(new NaveGreenPixel(0 	+ posX, 0	+ posY));
		
		pixeles.add(new NaveGreenPixel(-1 	+ posX, 0	+ posY));
		pixeles.add(new NaveGreenPixel(1 	+ posX, 0	+ posY));
		
		pixeles.add(new NaveGreenPixel(0 	+ posX, -1	+ posY));
		pixeles.add(new NaveGreenPixel(0 	+ posX, 1	+ posY));
	}
	
	@Override
	public void tick() throws JuegoCambiadoException {
		
		moveLeft(movLeft);
		moveRight(movRight);
		moveUp(movUp);
		moveDown(movDown);
		
		for (Nave n : pixeles) {
			
			n.moveLeft(movLeft);
			n.moveRight(movRight);
			n.moveUp(movUp);
			n.moveDown(movDown);
	    	
			n.tick();
			
	        setChanged();
	        notifyObservers(new int[] {n.getPosX(), n.getPosY()});
		}
		
		int[] pixX = new int[pixeles.size()];
		int[] pixY = new int[pixeles.size()];
		
		for (int i = 0; i < pixeles.size(); i++) {
			Nave pixel = pixeles.get(i);
			
			pixX[i] = pixel.getPosX();
			pixY[i] = pixel.getPosY();
		}
		
        //logica de Disparo
        if (willShoot) {
        	ArtilleriaJugador.getArtilleria().tick(pixX, pixY, this.willShoot);
            willShoot = false;
        } else {
        	ArtilleriaJugador.getArtilleria().tick(pixX, pixY);
        }
        
        super.tick();
	}

	@Override
	protected boolean canMoveLeft() {
		for (Nave n : pixeles) {
			if (!n.canMoveLeft())
				return false;
		}
		return true;
	}

	@Override
	protected boolean canMoveRight() {
		for (Nave n : pixeles) {
			if (!n.canMoveRight())
				return false;
		}
		return true;
	}

	@Override
	protected boolean canMoveUp() {
		for (Nave n : pixeles) {
			if (!n.canMoveUp())
				return false;
		}
		return true;
	}

	@Override
	protected boolean canMoveDown() {
		for (Nave n : pixeles) {
			if (!n.canMoveDown())
				return false;
		}
		return true;
	}
    
	@Override
	public void shoot() {
		willShoot = true;
	}

}
