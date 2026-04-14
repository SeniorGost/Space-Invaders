package modelo.balaJugador;

public class BalaFlecha extends BalaJugadorMultipixel {

    /**
     * Crea una bala en forma de flecha (3 píxeles).
     * @param posX Posición X central
     * @param pPosY Posición Y 
     */
    public BalaFlecha(int posX, int pPosY) {
     
        super(posX, pPosY - 1, 1, 1);
        
        int posY = getPosY();

        // Añadimos los 3 píxeles que forman la flecha:
      
        addPixel(new BalaJugadorPixel(posX, posY - 1));
        addPixel(new BalaJugadorPixel(posX - 1, posY));
        addPixel(new BalaJugadorPixel(posX + 1, posY));
    }
}