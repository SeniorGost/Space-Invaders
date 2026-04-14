package modelo.balaJugador;

public class BalaFactory {
	private static BalaFactory balaFactory;
	private BalaFactory() {}
	
	public static BalaFactory getBalaFactory() {
		if(balaFactory == null)
			balaFactory = new BalaFactory();
		return balaFactory;
	}
	
	public BalaJugador generate(String type, int posX, int posY) {
	    switch (type) {
	        case EstrategiaDisparo.DISPARO_ROMBO: 
	            return new BalaJugadorRombo(posX, posY);
	        case EstrategiaDisparo.DISPARO_FLECHA: 
	            return new BalaJugadorFlecha(posX, posY); 
	        default:
	            return new BalaJugadorPixel(posX, posY);
	    }
	}
}