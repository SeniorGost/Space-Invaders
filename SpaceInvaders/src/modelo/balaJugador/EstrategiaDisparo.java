package modelo.balaJugador;

public interface EstrategiaDisparo {
    // Recibe el "input" de la vista (ej. un String o un ID) y devuelve el tipo de bala
    String elegirTipoBala(String inputVista);
    boolean puedeDisparar(String disparoTipo);
    void disparar(String disparoTipo);
}
