package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public final class Jugador extends Observable {
    private int posX;
    private int posY;
    
    //  movDir false='izquierda', true='derecha'
    private boolean movLeft;
    private boolean movRight;
    private boolean movUp;
    private boolean movDown;
    
    
    private boolean willShoot;
    
    private static Jugador miJugador;
    
    // Constantes de juego
    private static final int VELOCIDAD = 1;
    private static final int LIMITE_IZQ = 0;
    private static final int LIMITE_DER = Modelo.getModelo().getWidth() - 1;
    private static final int LIMITE_ABAJO = Modelo.getModelo().getHeight() - 1;
    private static final int LIMITE_ARRIBA = 0;

    private Jugador() {
        movLeft = false;
        movRight = false;
        movUp = false;
        movDown = false;
        willShoot = false;
    }

    public void inicializar() {
    	// Posición inicial 
    	posX = 50;
        posY = 55;
        
        movLeft = false;
        movRight = false;
        movUp = false;
        movDown = false;
        willShoot = false;
        
        ArtilleriaJugador.getArtilleria().iniciar();
    }

    public static Jugador getJugador() {
        if (miJugador == null) miJugador = new Jugador();
        return miJugador;
    }

    public void tick() throws JuegoCambiadoException {
    	
    	boolean willMoveX = movRight || movLeft;
    	boolean willMoveY = movDown || movUp;    	
    	
    	boolean movDirX = movRight && !movLeft;
    	boolean movDirY = movDown && !movUp;
        
       // logica de Movimiento
        if (willMoveX) {
            if (movDirX) {	// Derecha
                if (posX + VELOCIDAD <= LIMITE_DER) posX += VELOCIDAD;
            } else { 		// Izquierda
                if (posX - VELOCIDAD >= LIMITE_IZQ) posX -= VELOCIDAD;
            }
            willMoveX = false;
        }
        if(willMoveY) {
            if (movDirY) {	// Abajo
                if (posY + VELOCIDAD <= LIMITE_ABAJO) posY += VELOCIDAD;
            } else { 		// Arriba
                if (posY - VELOCIDAD >= LIMITE_ARRIBA) posY -= VELOCIDAD;
            }            
            willMoveY = false;
        }
        
        setChanged();
        notifyObservers(new int[] {posX, posY});
        
        //logica de Disparo
        if (willShoot) {
        	ArtilleriaJugador.getArtilleria().tick(this.posX, this.posY, this.willShoot);
            willShoot = false;
        } else {
        	ArtilleriaJugador.getArtilleria().tick(this.posX, this.posY);
        }
        
    }

    /**
     * Indica que la nave del jugador debe empezar a moverse a la izquierda.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveLeft() {
        moveLeft(true);
    }
    
    /**
     * Indica que la nave del jugador debe empezar a moverse a la derecha.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveRight() {
    	moveRight(true);
    }
    
    /**
     * Indica que la nave del jugador debe empezar a moverse hacia arriba.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveUp() {
    	moveUp(true);
    }
    
    /**
     * Indica que la nave del jugador debe empezar a moverse hacia abajo.
     * Continuara este movimiento hasta que especifique lo contrario.
     */
    public void moveDown() {
    	moveDown(true);;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia la izquierda.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveLeft()}.
     * {@code false} detiene el movimiento.
     */
    public void moveLeft(boolean doMove) {
    	movLeft = doMove;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia la derecha.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveRight()}.
     * {@code false} detiene el movimiento.
     */
    public void moveRight(boolean doMove) {
    	movRight = doMove;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia arriba.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveUp()}.
     * {@code false} detiene el movimiento.
     */
    public void moveUp(boolean doMove) {
    	movUp = doMove;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia abajo.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveDown()}.
     * {@code false} detiene el movimiento.
     */
    public void moveDown(boolean doMove) {
    	movDown = doMove;
    }
    
    /**
     * Indica que debe disparar un bala (singular).
     */
    public void shoot() {
        this.willShoot = true;
    }

}