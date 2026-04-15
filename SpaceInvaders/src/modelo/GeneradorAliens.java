package modelo;

public class GeneradorAliens {
	private static GeneradorAliens miGenerador;
	
	private GeneradorAliens() {}

	public static GeneradorAliens getGeneradorAliens() {
		if (miGenerador == null) {
			miGenerador = new GeneradorAliens();
		}
		return miGenerador;
	}
	
	public Alien generarAlien(int posX, int posY) {
		return (Alien) EntityFactory.getEntityFactory().generate(EntityFactory.ALIEN_DEFAULT_ID, posX, posY);
	}
}
