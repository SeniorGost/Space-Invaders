package modelo;

import java.util.Observable;
@SuppressWarnings("deprecation")
public class Alien extends Observable {
    private int posX;
    private int posY;
    
    // Constantes opcionales para dimensiones del alien 
    // private static final int ANCHO = 30;
    // private static final int ALTO = 20;

    public Alien(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     * @return true si el alien ha sido impactado (muere), false en caso contrario.
     * @throws JuegoPerdidoException Si el alien alcanza el final
     */
    public boolean tick(int balaX, int balaY) throws JuegoPerdidoException {
        if (this.posX == balaX && this.posY == balaY) {
            setChanged();
            notifyObservers(); 
            return true; 
        }
        
        // Verificación de límite inferior (si el alien llega abajo, se pierde el juego)
        if (estaEnLimite()) {
            throw new JuegoPerdidoException();
        }
        
        return false;
    }

    //verifica si ha llegado a una de las paredes
    public boolean estaEnLimite(int limiteDerecho, int limiteIzquierdo) {
        return (this.posX >= limiteDerecho || this.posX <= limiteIzquierdo);
    }
    
    private boolean estaEnLimite() {
        // Lógica para detectar si el alien llegó al borde inferior (suponiendo 500 como límite)
        return this.posY >= 500;
    }

    public void mover(int deltaX, int deltaY) {
        this.posX += deltaX;
        this.posY += deltaY;
        setChanged();
        notifyObservers();
    }

    public void disparar() {
        // De momento vacío 
    }

}