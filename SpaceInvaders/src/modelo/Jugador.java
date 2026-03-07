package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public final class Jugador extends Observable {
    private int posX;
    private int posY;
    
    //  movDir false='izquierda', true='derecha'
    private boolean movDirX;
    private boolean movDirY;
    private boolean willMove;
    private boolean willShoot;
    
    private static Jugador miJugador;
    
    // Constantes de juego
    private int VELOCIDAD;
    private int LIMITE_IZQ;
    private int LIMITE_DER;
    private int LIMITE_ABAJO;
    private int LIMITE_ARRIBA;

    private Jugador() {
        this.willMove = false;
        this.willShoot = false;
        
        VELOCIDAD = 1;
        LIMITE_IZQ = 0;
        LIMITE_DER = Modelo.getModelo().getWidth();
        LIMITE_ABAJO = Modelo.getModelo().getHeight();
        LIMITE_ARRIBA = 0;
        
        
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
        if (willMove) {
            if (movDirX) {	// Derecha
                if (posX + VELOCIDAD <= LIMITE_DER) posX += VELOCIDAD;
            } else { 		// Izquierda
                if (posX - VELOCIDAD >= LIMITE_IZQ) posX -= VELOCIDAD;
            }
            
            if (movDirY) {	// Abajo
                if (posY + VELOCIDAD <= LIMITE_ABAJO) posY += VELOCIDAD;
            } else { 		// Arriba
                if (posY - VELOCIDAD >= LIMITE_ARRIBA) posY -= VELOCIDAD;
            }            
            willMove = false;
        }
        //logica de Disparo
        if (willShoot) {
        	ArtilleriaJugador.getArtilleria().tick(this.posX, this.posY, this.willShoot);
            willShoot = false;
        } else {
        	ArtilleriaJugador.getArtilleria().tick();
        }
        
        setChanged();
        notifyObservers(new int[] {posX, posY});
    }

  
    // @param pMovDir false para izquierda, true para derecha.
    public void moveX(boolean movX) {
        this.movDirX = movX;
        this.willMove = true;
    }
    
    public void moveY(boolean movY) {
        this.movDirY = movY;
        this.willMove = true;
    }

    public void shoot() {
        this.willShoot = true;
    }

}