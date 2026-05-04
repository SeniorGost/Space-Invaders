package modelo;

import java.util.Iterator;
import java.util.LinkedList;

public class ArtilleriaEnemigo {
	private LinkedList<BalaEnemigo> listaBalas;
	
	private static ArtilleriaEnemigo miArtilleriaEnemiga;
	//private LinkedList<BalaEnemigo> listaBalas;
	public static ArtilleriaEnemigo getArtilleria() {
		if (miArtilleriaEnemiga == null) miArtilleriaEnemiga = new ArtilleriaEnemigo();
		return miArtilleriaEnemiga;
	}
	
	/**
	 * Debes de llamar a este metodo al pricipio de cada partida, pero solo UNA VEZ
	 */
	public void iniciar() {
		listaBalas = new LinkedList<BalaEnemigo>();
	}
	
	public void tick() {
		
		Iterator<BalaEnemigo> it = listaBalas.iterator();
		
		while(it.hasNext()) {
			BalaEnemigo curBala = it.next();
			
			if (curBala.canMoveV(1)) {				
				curBala.move(0, 1);
				if(curBala.collide()) {					
					curBala.hit();
					if (curBala.isDead())
						it.remove();
				}
				curBala.draw();
			} else {
				it.remove();
			}
		}
	}
	
	/**
	 * Genera una bala del tipo indicado en la posicion indicada.
	 * 
	 * @param posX - Componente x de la posicion en la que se desea que aparezca la nueva bala.
	 * @param posY - Componente y de la posicion en la que se desea que aparezca la nueva bala.
	 */
	public void crearBala(int posX, int posY) {
		BalaEnemigo nBala = GeneradorBalasEnemigo.getGeneradorBalasEnemigo().generarBalaEnemigo(EntityFactory.DEFAULT_ALIEN_BULLET_ID, posX, posY);
		
		if (!nBala.canMoveH(0) && !nBala.canMoveV(0))
			return;
		
		listaBalas.add(nBala);
	}
}
