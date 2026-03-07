package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Flota {
    private ArrayList<Alien> listaAliens;
    private boolean movDir; // false = izquierda, true = derecha
    private boolean willFall; // Los aliens descenderan una posicion cuando este atributo es 'true' 
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

    public void inicializar() {
        tickCount = 0;
        movDir = true; // Empiezan moviéndose a la derecha
        willFall = false;
        inicializarAliens();
    }
    
    //Matriz de aliens
    private void inicializarAliens() {
    	listaAliens = new ArrayList<Alien>();
    	
    	// Definimos un margen derecho de 6px en el que ningun alien puede aparecer
    	int maxHPos = Modelo.getModelo().getWidth() - 6;
    	
    	// Los aliens tienen que tener al menos in pixel de separacion entre ellos, por lo que vamos a 
    	// considerar solo las posiciones horizontalmente pares como posiciones validas para que aparezca un alien.
    	ArrayList<Integer> validHPos = new ArrayList<Integer>();
    	for (int i = 0; i <= maxHPos; i += 2) {
    		validHPos.add(i);
    	}
    	
    	// Se encoge una cantidad de aliens aleatoria entre 4 y 8 (ambos incluidos)
    	Random rand = new Random();
    	int alienAmount = rand.nextInt(4) + 4; 
    	
    	// De manera aleatoria se eligen posiciones de entre las posiciones validas. La cantidad será la misma que la cantidad
    	// de aliens que queremos crear
    	int i = 0;
    	ArrayList<Integer> spawnHPos = new ArrayList<Integer>();
    	while (i < alienAmount && validHPos.size() > 0) {
    		int nextSpawnPos = rand.nextInt(validHPos.size() - 1);
    		spawnHPos.add(validHPos.remove(nextSpawnPos)); 
    		
    		i++;
    	}
    	
    	// Se instancian los aliens en las posiciones aleatorias
    	for (int j : spawnHPos) {
    		Alien nAlien = new Alien(j, 0);
    		listaAliens.add(nAlien);
    	}
    }
    
    //movimiento cada 4 ticks
    public void tick(int x, int y) throws JuegoGanadoException, JuegoPerdidoException {
    	tickCount++;
    	
    	int deltaX = 0;
    	int deltaY = 0;
    	
    	// Los aliens solo se mueven 1 de cada 4 ticks
    	if (tickCount == 4) {
    		tickCount = 0;
    		
    		deltaX = 1;
    		
    		// Derecha: movDir == true | Izquierda: movDir == false
    		if (!movDir) {
    			deltaX = -deltaX;
    		}
    		if (willFall) {
    			deltaY = -1;
    			willFall = false;
    		}
    		
    		// Se les propaga el tick a los aliens para moverlos
    		for (Alien a : listaAliens) {
    			// Si al menos una de las llamadas del metodo devuelve 'true', la proxima vez que los aliens
    			// se muevan sera en la direccion contraria. Ademas, los aliens descenderan una posicion en 
    			// su siguiente movimiento.
    			if (a.tick(deltaX, deltaY, x, y) && !willFall) {
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
