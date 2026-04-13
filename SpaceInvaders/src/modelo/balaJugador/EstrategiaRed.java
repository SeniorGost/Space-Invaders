package modelo.balaJugador;

public class EstrategiaRed implements EstrategiaDisparo {
    @Override
    public String elegirTipoBala(String inputVista) {
    	// Suponiendo que el input es "Siguiente" o un nombre específico
        if (inputVista.equalsIgnoreCase("Rombo")) return "Rombo";
        if (inputVista.equalsIgnoreCase("Flecha")) return "Flecha";
        return "Pixel";
    }
}