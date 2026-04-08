package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

public class Flota extends Observable {
    private ArrayList<Alien> listaAliens;
    
    private static Flota miFlota;
    
    private estadoFlota estadoActual;

    private Flota() {
        listaAliens = new ArrayList<>();
    }

    public static Flota getFlota() {
        if (miFlota == null) {
            miFlota = new Flota();
        }
        return miFlota;
    }

    public void inicializar() {
        estadoActual = new estadoFlotaEsperar(true, false);
        
        inicializarAliens();
    }
    
    //Matriz de aliens
    private void inicializarAliens() {
    	listaAliens = new ArrayList<Alien>();
    	
    	// Definimos un margen derecho de 16px en el que ningun alien puede aparecer
    	int maxHPos = Modelo.getModelo().getWidth() - 16;
    	
    	// Los aliens tienen que tener al menos un pixel de separacion entre ellos, por lo que vamos a 
    	// considerar solo las posiciones horizontalmente pares como posiciones validas para que aparezca un alien.
    	ArrayList<Integer> validHPos = new ArrayList<Integer>();
    	for (int i = 0; i <= maxHPos - 2; i += 2) {
    		validHPos.add(i + 2);
    	}
    	
    	// Se encoge una cantidad de aliens aleatoria entre 4 y 6 (ambos incluidos)
    	Random rand = new Random();
    	int alienAmount = rand.nextInt(4) + 2; 
    	
    	// De manera aleatoria se eligen posiciones de entre las posiciones validas. La cantidad será la misma que la cantidad
    	// de aliens que queremos crear
    	int i = 0;
    	ArrayList<Integer> spawnHPos = new ArrayList<Integer>();
    	while (i < alienAmount && validHPos.size() > 0) {
    		int nextSpawnPos = rand.nextInt(validHPos.size() - 1);
    		spawnHPos.add(validHPos.remove(nextSpawnPos)); 
    		
    		i++;
    	}
    	
    	// Intencionalmente se colocan dos aliens en dos extremos de la flota
    	listaAliens.add(new Alien(0, 0));
    	listaAliens.add(new Alien(maxHPos, 0));
    	
    	// Se instancian los aliens en las posiciones aleatorias
    	for (int j : spawnHPos) {
    		Alien nAlien = new Alien(j, 0);
    		listaAliens.add(nAlien);
    	}
    }
    
    //movimiento cada 4 ticks
    public void tick(int x, int y) throws JuegoPerdidoException {
    	estadoActual.tick(x, y);
    	
    	notifyView();
    	
    }
    
    public boolean move(int deltaX, int deltaY, int x, int y) throws JuegoPerdidoException {
    	boolean mustFall = false;
    	for (Alien a : listaAliens) {
    		// Si al menos una de las llamadas del metodo devuelve 'true', la proxima vez que los aliens
    		// se muevan sera en la direccion contraria. Ademas, los aliens descenderan una posicion en 
    		// su siguiente movimiento.
    		
    		if (a.tick(deltaX, deltaY, x, y)) mustFall = true;
    	}
    	return mustFall;
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
        
      // Comprobamos si no quedan aliens
        if (listaAliens.isEmpty()) {
        	// Si no quedan aliens, se lanza la excepcion
        	throw new JuegoGanadoException();
        }
        return alienEncontrado;
    }

    
    public void setState(estadoFlota nuevoEstado) {
    	estadoActual = nuevoEstado;
    }
    
    /**
	 * Mediante el 'Patron Observer' notifica al vista de las posiciones de los Aliens
	 */
	private void notifyView() {
		for (Alien a : listaAliens) {
			setChanged();
			notifyObservers(new int[] {a.getPosX(), a.getPosY()});
		}
	}
    
}
