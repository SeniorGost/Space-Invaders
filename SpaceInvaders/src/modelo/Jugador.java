package modelo;

public final class Jugador {
    private static Jugador miJugador;
    
    private Nave nave;

    private Jugador() {
    }

    /**
     * Se debe de llamar al comienzo de cada partida. Genera una nave dependiendo del tipo especificado 
     * en el parametro.
     * @param tipo - [0]: Nave Green | [1]: Nave Blue | [2]: Nave Red
     */
    public void inicializar(int tipo) {
    	
    	if (tipo == 0) {
    		nave = new NaveGreen();
    	}
        
        ArtilleriaJugador.getArtilleria().iniciar();
    }

    public static Jugador getJugador() {
        if (miJugador == null) miJugador = new Jugador();
        return miJugador;
    }

    /**
     * Tick es un brawler que puede disparar minas y hacer explotar su cabeza y tal...
     * @throws JuegoCambiadoException Propaga excepción
     */
    public void tick() throws JuegoCambiadoException {
        nave.tick();
    }

    /**
     * Indica que la nave del jugador debe empezar a moverse a la izquierda.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveLeft() {
        nave.moveLeft(true);
    }
    
    /**
     * Indica que la nave del jugador debe empezar a moverse a la derecha.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveRight() {
    	nave.moveRight(true);
    }
    
    /**
     * Indica que la nave del jugador debe empezar a moverse hacia arriba.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveUp() {
    	nave.moveUp(true);
    }
    
    /**
     * Indica que la nave del jugador debe empezar a moverse hacia abajo.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveDown() {
    	nave.moveDown(true);;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia la izquierda.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveLeft()}.
     * {@code false} detiene el movimiento.
     */
    public void moveLeft(boolean doMove) {
    	nave.moveLeft(doMove);
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia la derecha.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveRight()}.
     * {@code false} detiene el movimiento.
     */
    public void moveRight(boolean doMove) {
    	nave.moveRight(doMove);
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia arriba.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveUp()}.
     * {@code false} detiene el movimiento.
     */
    public void moveUp(boolean doMove) {
    	nave.moveUp(doMove);;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia abajo.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveDown()}.
     * {@code false} detiene el movimiento.
     */
    public void moveDown(boolean doMove) {
    	nave.moveDown(doMove);;
    }
    
    /**
     * Indica que debe disparar un bala (singular).
     */
    public void shoot() {
        nave.shoot();
    }

    public Nave getNave() {
    	return nave;
    }
}