package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import modelo.excepciones.JuegoGanadoException;
import modelo.excepciones.JuegoPerdidoException;

public class Flota extends Observable {
    private ArrayList<Alien> listaAliens;
    
    private static Flota miFlota;
    
    private EstadoFlota estadoActual;

    int hurtboxX;
    int hurtboxY;

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
        inicializarAliens();
		estadoActual = new EstadoFlotaEsperar(true, false);
		hurtboxX = -1;
		hurtboxY = -1;
    }
    
    //Matriz de aliens
    private void inicializarAliens() {
    	listaAliens = new ArrayList<Alien>();
    	
    	// Definimos un margen derecho de 16px en el que ningun alien puede aparecer
    	int maxHPos = Modelo.getModelo().getWidth() - 16;
    	
    	// Los aliens tienen que tener al menos un pixel de separacion entre ellos, por lo que vamos a 
    	// considerar solo las posiciones horizontalmente pares como posiciones validas para que aparezca un alien.
    	
    	int alienSpacing = 7;
    	
    	ArrayList<Integer> validHPos = new ArrayList<Integer>();
    	for (int i = alienSpacing * 2; i <= maxHPos - (alienSpacing * 2); i += alienSpacing) {
    		validHPos.add(i);
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
    	
    	// GeneradorAliens.getGeneradorAliens().generarAlien
    	
    	// Intencionalmente se colocan dos aliens en dos extremos de la flota
    	listaAliens.add(GeneradorAliens.getGeneradorAliens().generarAlien(alienSpacing, alienSpacing));
    	listaAliens.add(GeneradorAliens.getGeneradorAliens().generarAlien(maxHPos - alienSpacing, alienSpacing));
    	
    	// Se instancian los aliens en las posiciones aleatorias
    	for (int j : spawnHPos) {
    		Alien nAlien = GeneradorAliens.getGeneradorAliens().generarAlien(j, alienSpacing);
    		listaAliens.add(nAlien);
    	}
    }
    
    //movimiento cada 4 ticks
    public void tick() throws JuegoGanadoException, JuegoPerdidoException {
    	if (listaAliens.isEmpty())
    		throw new JuegoGanadoException();
    	estadoActual.tick();
    	
    	Iterator<Alien> it = listaAliens.iterator();
    	
    	while(it.hasNext()) {
    		Alien curAlien = it.next();
    		if (curAlien.tick())
    			throw new JuegoPerdidoException();
    	}
    }
    
    public boolean move(int deltaX, int deltaY) throws JuegoPerdidoException {
    	boolean mustFall = false;
    	for (Alien a : listaAliens) {
    		// Si al menos una de las llamadas del metodo devuelve 'true', la proxima vez que los aliens
    		// se muevan sera en la direccion contraria. Ademas, los aliens descenderan una posicion en 
    		// su siguiente movimiento.
    		if(!a.canMoveV(deltaY))
    			throw new JuegoPerdidoException();
    		
    		a.move(deltaX, deltaY);
    		if (!a.canMoveH(deltaX))
    			mustFall = true;
    	}
    	return mustFall;
    }
    
    public boolean hit(int offsetX, int offsetY, int hurtboxX, int hurtboxY, int[] pX, int[] pY) {
    	Iterator<Alien> it = listaAliens.iterator();
    	boolean rdo = false;
    	
    	while (it.hasNext() && !rdo) {
    		Alien curAlien = it.next();
    		
    		if(curAlien.canCollide(offsetX, offsetY, hurtboxX, hurtboxY))
    			rdo = curAlien.isHit(pX, pY);
    		if(rdo)
    			it.remove();
    	}
    	return rdo;
    }
    
    public void setState(EstadoFlota nuevoEstado) {
    	estadoActual = nuevoEstado;
    }
    
    /**
	 * Mediante el 'Patron Observer' notifica al vista de las posiciones de los Aliens
	 */
	public void notifyView(int x, int y) {
		setChanged();
		notifyObservers(new int[] {x, y});
	}
    
}
