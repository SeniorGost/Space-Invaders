package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import modelo.alien.Alien;
import modelo.alien.alienMultipixel;
import modelo.excepciones.JuegoGanadoException;
import modelo.excepciones.JuegoPerdidoException;

public class Flota extends Observable {
    private ArrayList<Alien> listaAliens;
    
    private static Flota miFlota;
    
    private estadoFlota estadoActual;

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
		estadoActual = new estadoFlotaEsperar(true, false);
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
    	
    	// Intencionalmente se colocan dos aliens en dos extremos de la flota
    	listaAliens.add(new alienMultipixel(alienSpacing, alienSpacing));
    	listaAliens.add(new alienMultipixel(maxHPos - alienSpacing, alienSpacing));
    	
    	// Se instancian los aliens en las posiciones aleatorias
    	for (int j : spawnHPos) {
    		Alien nAlien = new alienMultipixel(j, alienSpacing);
    		listaAliens.add(nAlien);
    	}
    }
    
    //movimiento cada 4 ticks
    public void tick(int[] pixNaveX, int[] pixNaveY, int naveX, int naveY) throws JuegoGanadoException, JuegoPerdidoException {
    	if (hurtboxX == -1)
    		calculateHurtbox(pixNaveX, pixNaveY, naveX, naveY);
    	
    	estadoActual.tick(pixNaveX, pixNaveY, naveX, naveY);
    	
    	for (Alien a : listaAliens) {
        	// Verifica si su posicion es la misma que la del jugador
    		boolean con = a.playerCollided(pixNaveX, pixNaveY, naveX, naveY, hurtboxX, hurtboxY);
    		if (con) {
    			throw new JuegoPerdidoException();
    		}
    		
    		Iterator<Integer> itDisplayX = a.getDisplayX().iterator();
    		Iterator<Integer> itDisplayY = a.getDisplayY().iterator();
    		
    		while (itDisplayX.hasNext() && itDisplayY.hasNext()) {
    			int posX = itDisplayX.next();
    			int posY = itDisplayY.next();
    			Flota.getFlota().notifyView(posX, posY);    			
    		}
    	}
    }
    
    public boolean move(int deltaX, int deltaY) throws JuegoPerdidoException {
    	boolean mustFall = false;
    	for (Alien a : listaAliens) {
    		// Si al menos una de las llamadas del metodo devuelve 'true', la proxima vez que los aliens
    		// se muevan sera en la direccion contraria. Ademas, los aliens descenderan una posicion en 
    		// su siguiente movimiento.
    		
    		if (a.tick(deltaX, deltaY))
    			mustFall = true;
    	}
    	return mustFall;
    }
    
    /**
     * Calcula los margenes de la 'hurtbox' de la nave del jugador y guarda esa información en atributos.
     * <p> Este calculo solo deberia de hacerse una vez por partida. En el primer tick.
     * 
     * @param pixNaveX - Los componentes x de las posiciones de los pixeles de la nave.
     * @param pixNaveY - Los componentes x de las posiciones de los pixeles de la nave.
     * @param naveX - Componente x de la posición central de la nave.
     * @param naveY - Componente y de la posición central de la nave.
     */
    private void calculateHurtbox(int[] pixNaveX, int[] pixNaveY, int naveX, int naveY) {
    	// En resumen, se hace un bucle en el que se calcula la diferencia horizontal y vertical de cada pixel respecto
    	// al centro de la nave (consideramos que la diferencia solo puede ser positiva). La diferencia mas grande 
    	// en la coordenada 'x' y en 'y' corresponderan con los margenes horizontales y verticales de la 'hurtbox' del 
    	// jugador.
    	
    	for (int i = 0; i < pixNaveX.length; i++) {
    		int valX = pixNaveX[i] - naveX;
    		if (valX < 0)
    			valX = -valX;
    		
    		if (hurtboxX < valX)
    			hurtboxX = valX;
    		
    		int valY = pixNaveY[i] - naveY;
    		if (valY < 0)
    			valY = -valY;
    		
    		if (hurtboxY < valY)
    			hurtboxY = valY;
    	}
    }
    
   //si se alcanza un alien se elimina de la flota
    public boolean hit(int[] pixelesX, int[] pixelesY, int pPosX, int pPosY, int hurtboxX, int hurtboxY) throws JuegoGanadoException {
    	boolean alienEncontrado = false;
    	
        Iterator<Alien> it = listaAliens.iterator();
        while (it.hasNext() && !alienEncontrado) {
            Alien a = it.next();
            // El tick del alien devuelve true si hay colisión
                if (a.hit(pixelesX, pixelesY, pPosX, pPosY, hurtboxX, hurtboxY)) {
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
	public void notifyView(int x, int y) {
		setChanged();
		notifyObservers(new int[] {x, y});
	}
    
}
