package modelo.balaJugador;

public class EstrategiaBlue implements EstrategiaDisparo {
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase("Pixel")) return "Rombo";
        return "Pixel";
    }
}
