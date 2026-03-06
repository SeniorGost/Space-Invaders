package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public final class Jugador extends Observable {
    private int posX;
    private int posY;
    
    //  movDir false='izquierda', true='derecha'
    private boolean movDir;
    private boolean willMove;
    private boolean willShoot;
    
    private static Jugador miJugador;
    
    // Constantes de juego
    private static final int VELOCIDAD = 10;
    private static final int LIMITE_IZQ = 0;
    private static final int LIMITE_DER = 8000;

    private Jugador() {
        // Posición inicial 
        this.posX = 400;
        this.posY = 550;
        this.willMove = false;
        this.willShoot = false;
    }

    public static void inicializar() {
        miJugador = new Jugador();
    }

    public static Jugador getJugador() {
        if (miJugador == null) inicializar();
        return miJugador;
    }

    public void tick() throws JuegoCambiadoException {
        
        // logica dde Movimiento
        if (willMove) {
            if (movDir) { // Derecha
                if (posX + VELOCIDAD <= LIMITE_DER) posX += VELOCIDAD;
            } else { // Izquierda
                if (posX - VELOCIDAD >= LIMITE_IZQ) posX -= VELOCIDAD;
            }
            willMove = false;
        }
        //logica de Disparo
        if (willShoot) {
            ArtilleriaJugador.getArtilleria().tick(this.posX, this.posY, this.willShoot);
            willShoot = false;
        } else {
        	ArtilleriaJugador.getArtilleria().tick(this.posX, this.posY, this.willShoot);
        }
        setChanged();
        notifyObservers();
    }

  
    // @param pMovDir false para izquierda, true para derecha.
    public void move(boolean pMovDir) {
        this.movDir = pMovDir;
        this.willMove = true;
    }

    
    public void shoot() {
        this.willShoot = true;
    }

}