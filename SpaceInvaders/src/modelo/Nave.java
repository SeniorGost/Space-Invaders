package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Nave extends Observable {

    protected int posX;
    protected int posY;
    
    protected boolean movLeft;
    protected boolean movRight;
    protected boolean movUp;
    protected boolean movDown;
    
    // Constantes de juego
    protected static final int VELOCIDAD = 1;
    protected static final int LIMITE_IZQ = 0;
    protected static final int LIMITE_DER = Modelo.getModelo().getWidth() - 1;
    protected static final int LIMITE_ABAJO = Modelo.getModelo().getHeight() - 1;
    protected static final int LIMITE_ARRIBA = 0;
    
    public Nave() {
        movLeft = false;
        movRight = false;
        movUp = false;
        movDown = false;
        
    	// Posición inicial 
    	posX = 50;
        posY = 55;
    }
    
    public void tick() throws JuegoCambiadoException {
    	boolean willMoveX = movRight || movLeft;
    	boolean willMoveY = movDown || movUp;    	
    	
    	boolean movDirX = movRight && !movLeft;
    	boolean movDirY = movDown && !movUp;
        
       // logica de Movimiento
        if (willMoveX) {
            if (movDirX) {	// Derecha
                posX += VELOCIDAD;
            } else { 		// Izquierda
                posX -= VELOCIDAD;
            }
            willMoveX = false;
        }
        if(willMoveY) {
            if (movDirY) {	// Abajo
                posY += VELOCIDAD;
            } else { 		// Arriba
                posY -= VELOCIDAD;
            }            
            willMoveY = false;
        }
    }
    
    protected abstract boolean canMoveLeft();
    protected abstract boolean canMoveRight();
    protected abstract boolean canMoveUp();
    protected abstract boolean canMoveDown();

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
    	movLeft = doMove && canMoveLeft();
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia la derecha.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveRight()}.
     * {@code false} detiene el movimiento.
     */
    public void moveRight(boolean doMove) {
    	movRight = doMove && canMoveRight();
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia arriba.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveUp()}.
     * {@code false} detiene el movimiento.
     */
    public void moveUp(boolean doMove) {
    	movUp = doMove && canMoveUp();
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia abajo.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveDown()}.
     * {@code false} detiene el movimiento.
     */
    public void moveDown(boolean doMove) {
    	movDown = doMove && canMoveDown();
    }
    
    /**
     * Indica que debe disparar un bala (singular).
     */
    public abstract void shoot();

    public int getPosX() {
    	return posX;
    }
    public int getPosY() {
    	return posY;
    }
}
