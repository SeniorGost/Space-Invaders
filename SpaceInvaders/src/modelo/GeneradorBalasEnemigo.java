package modelo;

public class GeneradorBalasEnemigo {
	private static GeneradorBalasEnemigo miGenerador;
	
	private GeneradorBalasEnemigo() {}
	
	public static GeneradorBalasEnemigo getGeneradorBalasEnemigo() {
		if (miGenerador == null) {
			miGenerador = new GeneradorBalasEnemigo();
		}
		return miGenerador;
	}
	
	public BalaEnemigo generarBalaEnemigo(String id, int posX, int posY) {
		return (BalaEnemigo) EntityFactory.getEntityFactory().generate(id, posX, posY);
	}
}
