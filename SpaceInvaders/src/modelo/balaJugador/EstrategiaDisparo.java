package modelo.balaJugador;

public interface EstrategiaDisparo {
    // Recibe el "input" de la vista (ej. un String o un ID) y devuelve el tipo de bala
	public final String DISPARO_ROMBO = "Rombo";
	public final String DISPARO_FLECHA = "Flecha";
	public final String DISPARO_PIXEL = "Pixel";
	
    String elegirTipoBala(String inputVista);
    boolean puedeDisparar(String disparoTipo);
    void disparar(String disparoTipo);
}
