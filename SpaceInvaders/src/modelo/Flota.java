package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Flota {
    private ArrayList<Alien> listaAliens;
    private boolean movDir; // false = izquierda, true = derecha
    private int tickCount;
    private static Flota miFlota;

    private Flota() {
        listaAliens = new ArrayList<>();
        tickCount = 0;
        movDir = true; // Empiezan moviéndose a la derecha
        inicializarAliens();
    }

    public static Flota getFlota() {
        if (miFlota == null) {
            miFlota = new Flota();
        }
        return miFlota;
    }

   //Matriz de aliens ej 1x10
    private void inicializarAliens() {
        for (int fila = 0; fila < 1; fila++) {
            for (int col = 0; col < 10; col++) {
                // Espaciado de 40px entre ellos
                listaAliens.add(new Alien(50 + (col * 40), 50 + (fila * 40)));
            }
        }
    }

    public static void inicializar() {
        miFlota = new Flota();
    }

   //movimiento cada 4 ticks
    public void tick(int x, int y) throws JuegoGanadoException, JuegoPerdidoException {
        tickCount++;
        hit(x, y);
        if (tickCount >= 4) {
            moverFlota();
            tickCount = 0;
        }
        if (listaAliens.isEmpty()) {//comprobaos si no quedan aliens
        	  throw new JuegoGanadoException();
        }
    }

    private void moverFlota() {
        boolean cambiarDireccion = false;

        //Comprobacion Limites laterales
        for (Alien a : listaAliens) {
            if (a.estaEnLimite(8000, 0)) { // Límites de la pantalla 
                cambiarDireccion = true;
                break;
            }
        }

        if (cambiarDireccion) {
            movDir = !movDir; 
            for (Alien a : listaAliens) {
                a.mover(0, 20); //bajar unaa fila
            }
        } else {
            int avance = movDir ? 10 : -10;
            for (Alien a : listaAliens) {
                a.mover(avance, 0);
            }
        }
    }

   //si se alcanza un alien se elimina de la flota
    public boolean hit(int x, int y) throws JuegoPerdidoException {
        Iterator<Alien> it = listaAliens.iterator();
        while (it.hasNext()) {
            Alien a = it.next();
            // El tick del alien devuelve true si hay colisión
                if (a.tick(x, y)) {
                    it.remove(); 
                    return true; 
                }
         }
        return false;
    }

  
    
}
