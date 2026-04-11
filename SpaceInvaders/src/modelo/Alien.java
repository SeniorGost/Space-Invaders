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
     * Si existe una intersección entre la hitbox del alien y la hurtbox del jugador, se comprueba si al menos uno de
     * los pixeles del alien cohencide en posicion con uno de los pixeles de la nave.
     * 
     * @param pPosX - Los componentes x de las posiciones de los pixeles de la nave del jugador.
     * @param pPosY - Los componentes y de las posiciones de los pixeles de la nave del jugador.
     * @param offsetX - Componente x de la posición central de la nave del jugador.
     * @param offsetY - Componente y de la posición central de la nave del jugador.
     * @param hurtboxX - Margen horizontal de la hurtbox de la nave del jugador.
     * @param hurtboxY - Margen vertical de la hurtbox de la nave del jugador.
     * @return {@code true} el alien y la nave del jugador comparten posición, {@code false} en caso contrario.
	*/
    public abstract boolean playerCollided(int[] pPosX, int[] pPosY, int offsetX, int offsetY, int hurtboxX, int hurtboxY);
    
	/**
	 * Dada la posicion de un a bala del jugador, devuelve si la bala ha collisionado con el alien.
	 * 
	 * @param balaX - posicion horizontal (componente x) de la bala el jugador.
	 * @param balaY - posicion vertical (componente y) de la bala el jugador.
	 * 
	 * @return {@code true} si la posicion de la bala coincide con al menos una posicion de un pixel del alien.
	 * {@code false} en caso contrario.
	 */
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