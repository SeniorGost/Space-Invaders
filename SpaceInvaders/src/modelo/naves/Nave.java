package modelo.naves;

import java.util.Observable;

import modelo.Modelo;
import modelo.excepciones.JuegoCambiadoException;

@SuppressWarnings("deprecation")
public abstract class Nave extends Observable {
	
	// Donde esta la nave
    private int posX;
    private int posY;
    
    // Atributos relevantes para el movimiento
    private boolean movLeft;		// Se mueve hacia la izquierda en su siguiente tick cuando es true
    private boolean movRight;		// Se mueve hacia la derecha en su siguiente tick cuando es true
    private boolean movUp;			// Se mueve hacia la arriba en su siguiente tick cuando es true
    private boolean movDown;		// Se mueve hacia la abajo en su siguiente tick cuando es true
    
    // Constantes de juego
    protected static final int VELOCIDAD = 1;
    protected static final int LIMITE_IZQ = 0;
    protected static final int LIMITE_DER = Modelo.getModelo().getWidth() - 1;
    protected static final int LIMITE_ABAJO = Modelo.getModelo().getHeight() - 1;
    protected static final int LIMITE_ARRIBA = 0;

	//Ander no te asustes, estas constantes estan PUBLICAS aun si son ATRIBUTOS porque se usan mucho a lo largo y ancho de modelo
	public static final int NAVE_GREEN = 0;
	public static final int NAVE_BLUE = 1;
	public static final int NAVE_RED = 2;
    
    
    /**
     * Las naves aparecen quietas en un punto determinado del espacio
     * 
     * @param pPosX - La posicion horizontal (coordenada x) en la que aparecerá la nave
     * @param pPosY - La posicion vertical (coordenada y) en la que aparecerá la nave
     */
    public Nave(int pPosX, int pPosY) {
        movLeft = false;
        movRight = false;
        movUp = false;
        movDown = false;
        
    	// Posición inicial 
    	posX = pPosX;
        posY = pPosY;
    }
    
    /**
     * Mueve la nave. No comprueba antes si su movimiento la dejara fuera del espacio.
     * 
     * @throws JuegoCambiadoException Propaga las excepciones.
     */
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

    /**
     * @return La posicion horizontal (coordenada x) de la nave.
     */
    public int getPosX() {
    	return posX;
    }
    
    /**
     * @return La posicion vertical (coordenada y) de la nave.
     */
    public int getPosY() {
    	return posY;
    }

	protected boolean isMovLeft() {
		return movLeft;
	}

	protected boolean isMovRight() {
		return movRight;
	}

	protected boolean isMovUp() {
		return movUp;
	}

	protected boolean isMovDown() {
		return movDown;
	}
}
