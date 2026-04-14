package modelo.balaJugador;

public class EstrategiaRed implements EstrategiaDisparo {
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase("Pixel")) return "Rombo";
        if (inputVista.equalsIgnoreCase("Rombo")) return "Flecha";
        return "Pixel";
    }
}
