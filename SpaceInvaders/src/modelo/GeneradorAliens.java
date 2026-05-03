package modelo;

import java.util.Random;

public class GeneradorAliens {
	private static GeneradorAliens miGenerador;
	private String[] posibleRandomAliens = {EntityFactory.ALIEN_DEFAULT_ID, EntityFactory.STRONGER_ALIEN_ID};
	
	private GeneradorAliens() {}

	public static GeneradorAliens getGeneradorAliens() {
		if (miGenerador == null) {
			miGenerador = new GeneradorAliens();
		}
		return miGenerador;
	}
	
	public Alien generarAlien(int posX, int posY) {
		Random random = new Random();
		int indx = random.nextInt(posibleRandomAliens.length);
		
		return (Alien) EntityFactory.getEntityFactory().generate(posibleRandomAliens[indx], posX, posY);
	}
}
