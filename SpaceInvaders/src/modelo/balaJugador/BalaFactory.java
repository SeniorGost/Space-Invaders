package modelo.balaJugador;

public class BalaFactory {
	private static BalaFactory balaFactory;
	private BalaFactory() {
		
	}
	
	public static BalaFactory getBalaFactory() {
		if(balaFactory == null)
			balaFactory = new BalaFactory();
		return balaFactory;
	}
	
	public BalaJugador generate(String type, int posX, int posY) {
	    switch (type) {
	        case "Rombo": 
	            return new BalaRombo(posX, posY);
	        case "Flecha": 
	            return new BalaFlecha(posX, posY); 
	        default:
	            return new BalaJugadorPixel(posX, posY);
	    }
	}
}
