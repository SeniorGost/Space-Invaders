package modelo;

public class GeneradorNaves {

	private static GeneradorNaves miGenerador;
	
	private final static int START_POS_X = 50;
	private final static int START_POS_Y = 55;
	
	private GeneradorNaves() {}

	public static GeneradorNaves getGeneradorNaves() {
		if (miGenerador == null) {
			miGenerador = new GeneradorNaves();
		}
		return miGenerador;
	}
	
	public Nave generarNave(int tipoNave) {
		switch (tipoNave) {
		case Nave.NAVE_GREEN:
			return (Nave) EntityFactory.getEntityFactory().generate(EntityFactory.NAVE_GREEN_ID, START_POS_X, START_POS_Y);
		case Nave.NAVE_BLUE:
			return (Nave) EntityFactory.getEntityFactory().generate(EntityFactory.NAVE_BLUE_ID, START_POS_X, START_POS_Y);
		case Nave.NAVE_RED:
		default:
			return (Nave) EntityFactory.getEntityFactory().generate(EntityFactory.NAVE_RED_ID, START_POS_X, START_POS_Y);
		}
	}
}
