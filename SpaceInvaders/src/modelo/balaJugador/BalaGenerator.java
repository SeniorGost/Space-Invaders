package modelo.balaJugador;

public class BalaGenerator {

	private static BalaGenerator balasGenerator;
	private BalaGenerator() {
		
	}
	
	public static BalaGenerator getBalasGenerator() {
		if(balasGenerator == null) 
			balasGenerator = new BalaGenerator();
		return balasGenerator;
	}
	
	public BalaJugador generate(String type, int posX, int posY) {
		BalaJugador bala = BalaFactory.getBalaFactory().generate(type, posX, posY);
		return bala;
	}
}
