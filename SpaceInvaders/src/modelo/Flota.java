package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Flota {
    private ArrayList<Alien> listaAliens;
    private boolean movDir; // false = izquierda, true = derecha
    private boolean willFall;
    private int tickCount;
    private static Flota miFlota;

    private Flota() {
        listaAliens = new ArrayList<>();
        movDir = true;
        willFall = false;
    }

    public static Flota getFlota() {
        if (miFlota == null) {
            miFlota = new Flota();
        }
        return miFlota;
    }

   //Matriz de aliens ej 1x10
    private void inicializarAliens() {
    	listaAliens = new ArrayList<Alien>();
    	
        for (int fila = 0; fila < 1; fila++) {
            for (int col = 0; col < 10; col++) {
                // Espaciado de 40px entre ellos
                listaAliens.add(new Alien(50 + (col * 40), 50 + (fila * 40)));
            }
        }
    }

    public void inicializar() {
        tickCount = 0;
        movDir = true; // Empiezan moviéndose a la derecha
        inicializarAliens();
    }
    //movimiento cada 4 ticks
    public void tick(int x, int y) throws JuegoGanadoException, JuegoPerdidoException {
    	tickCount++;
    	
    	int deltaX = 0;
    	int deltaY = 0;
    	
    	if (tickCount == 4) {
    		tickCount = 0;
    		
    		deltaX = 1;
    		
    		if (!movDir) {
    			deltaX = -deltaX;
    		}
    		if (willFall) {
    			deltaY = -1;
    			willFall = false;
    		}
    		
    		for (Alien a : listaAliens) {    			
    			if (a.tick(deltaX, deltaY, x, y)) {
    				willFall = true;
    				movDir = !movDir;
    			}
    		}
    		
    	}
    }
    
    
    
   //si se alcanza un alien se elimina de la flota
    public boolean hit(int x, int y) throws JuegoGanadoException {
    	boolean alienEncontrado = false;
    	
        Iterator<Alien> it = listaAliens.iterator();
        while (it.hasNext() && !alienEncontrado) {
            Alien a = it.next();
            // El tick del alien devuelve true si hay colisión
                if (a.hit(x, y)) {
                    it.remove(); 
                    alienEncontrado = true;
                }
         }
        
        if (listaAliens.isEmpty()) {//comprobaos si no quedan aliens
        	throw new JuegoGanadoException();
        }
        return alienEncontrado;
    }

  
    
}
