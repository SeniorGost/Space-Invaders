package modelo;

import modelo.excepciones.JuegoCambiadoException;


public final class Jugador {
    private static Jugador miJugador;
    
    private Nave nave;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;

	private boolean willShoot;
    
	private static final int SHOOT_COOLDOWN_PERIOD = 6;
	private int shootCooldown;
    
	private Jugador() {
    }

    /**
     * Se debe de llamar al comienzo de cada partida. Genera una nave dependiendo del tipo especificado 
     * en el parametro.
     * @param tipo - [0]: Nave Green | [1]: Nave Blue | [2]: Nave Red
     */
    public void inicializar(int tipo) 
    {
    	moveLeft = false;
    	moveRight = false;
    	moveUp = false;
    	moveDown = false;
    	
    	willShoot = false;
    	shootCooldown = 0;
    	
    	nave = GeneradorNaves.getGeneradorNaves().generarNave(tipo);

        ArtilleriaJugador.getArtilleria().iniciar(tipo);
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
    	boolean willMoveX = moveRight || moveLeft;
    	boolean willMoveY = moveDown || moveUp;    	
    	
    	int deltaX;
    	int deltaY;
    	
    	if (moveRight && !moveLeft)
    		deltaX = 1;
    	else
    		deltaX = -1;
    	
    	if (moveDown && !moveUp)
    		deltaY = 1;
    	else
    		deltaY = -1;
    	
        // logica de Movimiento
        if (willMoveX) {
            if (!nave.canMoveH(deltaX))
            	deltaX = 0;
        } else {
        	deltaX = 0;
        }
        if(willMoveY) {
            if (!nave.canMoveV(deltaY))
            	deltaY = 0;
        } else {
        	deltaY = 0;
        }
        
    	nave.move(deltaX, deltaY);
        
        nave.tick();
        
        if (shootCooldown > 0) {        		
        	shootCooldown--;
        } else {
        	if (willShoot) {
        		ArtilleriaJugador.getArtilleria().shoot(nave.getOffsetX(), nave.getOffsetY() - 5);
        		willShoot = false;
        		shootCooldown = SHOOT_COOLDOWN_PERIOD;
        	}
        }
        ArtilleriaJugador.getArtilleria().tick();
    }

    public boolean hit(int offsetX, int offsetY, int hurtboxX, int hurtboxY, int[] pX, int[] pY) {
    	if (nave.canCollide(offsetX, offsetY, hurtboxX, hurtboxY))
    		return nave.isHit(pX, pY);
    	return false;
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
    	moveLeft = doMove;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia la derecha.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveRight()}.
     * {@code false} detiene el movimiento.
     */
    public void moveRight(boolean doMove) {
    	moveRight = doMove;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia arriba.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveUp()}.
     * {@code false} detiene el movimiento.
     */
    public void moveUp(boolean doMove) {
    	moveUp = doMove;
    }
    
    /**
     * Especifica si la nave va a comenzar o va a detener su movimiento hacia abajo.
     * Continuara este movimiento hasta que especifique lo contrario.
     * @param doMove - {@code true} inicia el movimiento (funcionalmente igual a llamar a {@code moveDown()}.
     * {@code false} detiene el movimiento.
     */
    public void moveDown(boolean doMove) {
    	moveDown = doMove;
    }
    
    /**
     * Indica que debe disparar un bala (singular).
     */
    public void shoot() {
    	if (shootCooldown < 4) {    		
    		willShoot = true;
    	}
    }
}
