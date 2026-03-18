package modelo;

public class Alien {
    private int posX;
    private int posY;
    private boolean justMoved;

    public Alien(int x, int y) {
        this.posX = x;
        this.posY = y;
        
        this.justMoved = false;
    }

    /**
     * @param deltaX - El cambio horizontal que se realizaran en la posicion del alien.
     * @param deltaY - El cambio vertical que se realizaran en la posicion del alien.
     * @param jugadorX - La posicion horizontal del jugador.
     * @param jugadorY - La posicion vertical del jugador.
     * @return {@code true} si el alien ha alcanzado uno de los limites horizontales del grid, {@code false} en caso contrario.
     * @throws JuegoPerdidoException Si el alien alcanza el final o si colisiona con el jugador
     */
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
        
        return (posX - 1 < 0 || posX  + 1 > Modelo.getModelo().getWidth() - 1);
    }
    

    /**
     * @return {@code true} Si su posicion coincide con la recibida, {@code false} en caso contrario.
     */
    
    public boolean hit(int balaX, int balaY) {
    	// Se comprueba tambien las posiciones de alrededor de la bala para facilitar eliminar a los aliens
    	if (this.posX == balaX && (this.posY == balaY || this.posY == balaY - 1)) 
    		return true;
    	if (justMoved)
	    	if (this.posX == balaX && this.posY == balaY + 1) 
	    		return true;
    	return false;
    }

    public void disparar() {
        // De momento vacío 
    }

	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}

}