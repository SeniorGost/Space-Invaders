package modelo;

public class alienPixel extends Alien {

	public alienPixel(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean tick(int deltaX, int deltaY, int jugadorX, int jugadorY) throws JuegoPerdidoException {
    	// Verifica si su posicion es la misma que la del jugador
    	if (posX == jugadorX && posY == jugadorY) 
    		throw new JuegoPerdidoException();
    	
    	// Verificación de límite inferior (si el alien llega abajo, se pierde el juego)
    	if (posY == (Modelo.getModelo().getHeight()-1)) {    		
    		throw new JuegoPerdidoException();
    	}
    	
        this.posX += deltaX;
        this.posY += deltaY;
        
        justMoved = deltaX != 0;
        
        // Verifica de nuevo si su posicion es la misma que la del jugador
        if (posX == jugadorX && posY == jugadorY) 
        	throw new JuegoPerdidoException();
        
        Flota.getFlota().notifyView(posX, posY);
        
        return (posX - 1 < 0 || posX  + 1 > Modelo.getModelo().getWidth() - 1);
	}

	@Override
	public boolean hit(int balaX, int balaY) {
    	// Se comprueba tambien las posiciones de alrededor de la bala para facilitar eliminar a los aliens
    	if (this.posX == balaX && (this.posY == balaY || this.posY == balaY - 1)) 
    		return true;
    	if (justMoved)
	    	if (this.posX == balaX && this.posY == balaY + 1) 
	    		return true;
    	return false;
	}

}
