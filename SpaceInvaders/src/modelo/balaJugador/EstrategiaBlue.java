package modelo.balaJugador;

public class EstrategiaBlue implements EstrategiaDisparo {
    @Override
    public String elegirTipoBala(String inputVista) {
    	// Suponiendo que el input es "Siguiente" o un nombre específico
        if (inputVista.equalsIgnoreCase("Rombo")) return "Rombo";
        return "Pixel";
    }
}