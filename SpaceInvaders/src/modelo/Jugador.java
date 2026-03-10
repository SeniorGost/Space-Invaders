package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public final class Jugador extends Observable {
    private int posX;
    private int posY;
    
    //  movDir false='izquierda', true='derecha'
    private boolean movDirX;
    private boolean movDirY;
    private boolean willShoot;
	private boolean willMoveX;
	private boolean willMoveY;
    
    private static Jugador miJugador;
    
    // Constantes de juego
    private static final int VELOCIDAD = 1;
    private static final int LIMITE_IZQ = 0;
    private static final int LIMITE_DER = Modelo.getModelo().getWidth() - 1;
    private static final int LIMITE_ABAJO = Modelo.getModelo().getHeight() - 1;
    private static final int LIMITE_ARRIBA = 0;

    private Jugador() {
        this.willMoveX = false;
        this.willMoveY = false;
        this.willShoot = false;
    }

    public void inicializar() {
    	// Posición inicial 
    	posX = 50;
        posY = 55;
        
        ArtilleriaJugador.getArtilleria().iniciar();
    }

    public static Jugador getJugador() {
        if (miJugador == null) miJugador = new Jugador();
        return miJugador;
    }

    public void tick() throws JuegoCambiadoException {
        
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

    // @param pMovDir false para izquierda, true para derecha.
    public void moveX(boolean movX) {
        this.movDirX = movX;
        this.willMoveX = true;
    }
    
    public void moveY(boolean movY) {
        this.movDirY = movY;
        this.willMoveY = true;
    }

    public void shoot() {
        this.willShoot = true;
    }

}