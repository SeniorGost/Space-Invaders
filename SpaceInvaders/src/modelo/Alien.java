package modelo;

public abstract class Alien {
    protected int posX;
    protected int posY;
    protected boolean justMoved;

    public Alien(int x, int y) {
        this.posX = x;
        this.posY = y;
        
        this.justMoved = false;
    }

    /**
     * @param deltaX - El cambio horizontal que se realizaran en la posicion del alien.
     * @param deltaY - El cambio vertical que se realizaran en la posicion del alien.
     * @return {@code true} si el alien ha alcanzado uno de los limites horizontales del grid, {@code false} en caso contrario.
     * @throws JuegoPerdidoException Si el alien alcanza el final
     */
    public boolean tick(int deltaX, int deltaY) throws JuegoPerdidoException {
        this.posX += deltaX;
        this.posY += deltaY;
        
        justMoved = deltaX != 0;
        
        return (posX - 1 < 0 || posX  + 1 > Modelo.getModelo().getWidth() - 1);
    }
    
    /**
     * @return {@code true} Si su posicion coincide con la recibida, {@code false} en caso contrario.
     */
    public abstract boolean playerCollided(int[] pPosX, int[] pPosY, int offsetX, int offsetY, int hurtboxX, int hurtboxY);
    
    public abstract boolean hit(int balaX, int balaY);

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