package modelo.naves;

import java.util.ArrayList;

import modelo.ArtilleriaJugador;
import modelo.excepciones.JuegoCambiadoException;

/**
 * Implementa el patron de diseño {@code Composite}.
 * <p> Un tipo de nave compuesto por otras naves de un unico pixel. A rasgos generales, coordina multiples naves de
 * un pixel. 
 * <p> Propaga el tick y todas sus funciones a los pixeles. Mueve todas las naves de un pixel asociadas
 * mientras que actualiza su posicion central y envia sus posiciones al vista.
 * <p> Puede disparar. 
 */
@SuppressWarnings("deprecation")
public abstract class NaveMultipixel extends Nave {

	protected ArrayList<Nave> pixeles;
	private boolean willShoot;			// La nave dispara si en su tick este parametro es true.
	
	// [!] Estos atributos deben de ser definidos en la inicializacion de las naves que hereden de esta clase.
	private int bulletOffset;			// Determina cuantos pixeles por encima de la posicion central deben de
										// generarse las balas que dispare la nave.
	private int[] bulletTypes;			// Determina el tipo de bala que dispara la nave.

	/**
	 * Define ArrayList y eso...
	 * <p> Llama a super con una posicion fija.
	 */
	public NaveMultipixel(int pBulletOffset, int[] pBulletTypes) {
		// Por defecto, la nave del jugador aparece en esta posicion
		super(50, 55);
		
		bulletOffset = pBulletOffset;
		bulletTypes = pBulletTypes;
		willShoot = false;
		
		pixeles = new ArrayList<Nave>();
	}
	
	/**
	 * Mueve todos los pixeles de la nave y actualiza su posicion central. Despues envia su posicion al vista
	 * mediante el patron {@code Observer}.
	 * <p> Informa de la creacion de una nueva bala y propaga el {@code tick} a {@code ArtilleriaJugador}
	 */
	@Override
	public void tick() throws JuegoCambiadoException {
		
		// Si ejecutas el metodo para mover la nave con el movimiento actual, seguira moviendose si es posible
		// o se dentendra si no puede seguir avanzando.
		
		// Esta es una manera muy conveniente de hacer que se detenga cuando alguno de los pixeles de la nave
		// llega a un borde de la pantalla
		
		moveLeft(isMovLeft());
		moveRight(isMovRight());
		moveUp(isMovUp());
		moveDown(isMovDown());
		
		for (Nave n : pixeles) {
			
			// Actualiza el movimiento de todos los pixeles.
			
			n.moveLeft(isMovLeft());
			n.moveRight(isMovRight());
			n.moveUp(isMovUp());
			n.moveDown(isMovDown());
	    	
			// Propaga el tick a los pixeles
			
			n.tick();
			
			// Envia la posicion de cada pixel al Vista
			
	        setChanged();
	        notifyObservers(new int[] {n.getPosX(), n.getPosY()});
		}
        //logica de Disparo
        if (willShoot) {
        	ArtilleriaJugador.getArtilleria().shoot(getPosX(), getPosY() + bulletOffset);
            willShoot = false;
        }
        
        // Mueve su posicion relativa
        
        super.tick();
        
        // Recoge todas las posiciones de los pixeles en dos Array, uno para las coordenadas horizontales y otro
        // para las verticales.
        // Los elementos de misma posicion entre los dos Array forman una posicion de un pixel.
        
        int[] pixX = new int[pixeles.size()];
        int[] pixY = new int[pixeles.size()];
        
        for (int i = 0; i < pixeles.size(); i++) {
        	Nave pixel = pixeles.get(i);
        	
        	pixX[i] = pixel.getPosX();
        	pixY[i] = pixel.getPosY();
        }
        
        // Propaga el tick
        
        ArtilleriaJugador.getArtilleria().tick(pixX, pixY, getPosX(), getPosY());
	}
	/**
	 * Comprueba que el movimiento sea posible para todos y cada uno de los pixeles que conforman la nave.
	 * @return Devuelve {@code true} si el movimiento es posible, devuelve {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveLeft() {
		for (Nave n : pixeles) {
			if (!n.canMoveLeft())
				return false;
		}
		return true;
	}
	/**
	 * Comprueba que el movimiento sea posible para todos y cada uno de los pixeles que conforman la nave.
	 * @return Devuelve {@code true} si el movimiento es posible, devuelve {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveRight() {
		for (Nave n : pixeles) {
			if (!n.canMoveRight())
				return false;
		}
		return true;
	}
	/**
	 * Comprueba que el movimiento sea posible para todos y cada uno de los pixeles que conforman la nave.
	 * @return Devuelve {@code true} si el movimiento es posible, devuelve {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveUp() {
		for (Nave n : pixeles) {
			if (!n.canMoveUp())
				return false;
		}
		return true;
	}
	/**
	 * Comprueba que el movimiento sea posible para todos y cada uno de los pixeles que conforman la nave.
	 * @return Devuelve {@code true} si el movimiento es posible, devuelve {@code false} en caso contrario.
	 */
	@Override
	protected boolean canMoveDown() {
		for (Nave n : pixeles) {
			if (!n.canMoveDown())
				return false;
		}
		return true;
	}
	/**
	 * Establece que en el siguiente {@code tick} disparará una bala.
	 */
	@Override
	public void shoot() {
		willShoot = true;
	}
}
