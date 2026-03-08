package modelo;

import java.util.Observable;
@SuppressWarnings("deprecation")
public class Alien extends Observable {
    private int posX;
    private int posY;

    public Alien(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     * @param deltaX - El cambio horizontal que se realizaran en la posicion del alien.
     * @param deltaY - El cambio vertical que se realizaran en la posicion del alien.
     * @param jugadorX - La posicion horizontal del jugador.
     * @param jugadorY - La posicion vertical del jugador.
     * @return {@code true} si el alien ha alcanzado uno de los limites horizontales del grid, {@code false} en caso contrario.
     * @throws JuegoPerdidoException Si el alien alcanza el final o si colisiona con el jugador
     */
    public boolean tick(int deltaX, int deltaY, int jugadorX, int jugadorY) throws JuegoPerdidoException {
    	
    	// Verifica si su posicion es la misma que la del jugador
    	if (posX == jugadorX && posY == jugadorY) {
    		throw new JuegoPerdidoException();
    	}
    	
    	// Verificación de límite inferior (si el alien llega abajo, se pierde el juego)
    	if (posY == Modelo.getModelo().getHeight()) {    		
    		throw new JuegoPerdidoException();
    	}
    	
        this.posX += deltaX;
        this.posY += deltaY;
        
        setChanged();
        notifyObservers(new int[] {posX, posY});
        
        return (posX == 0 || posX == Modelo.getModelo().getWidth());
    }
    

    /**
     * @return {@code true} Si su posicion coincide con la recibida, {@code false} en caso contrario.
     */
    
    public boolean hit(int balaX, int balaY) {
    	if (this.posX == balaX && this.posY == balaY) {return true;}
    	return false;
    }

    public void disparar() {
        // De momento vacío 
    }

}