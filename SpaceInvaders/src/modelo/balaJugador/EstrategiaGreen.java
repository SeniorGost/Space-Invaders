package modelo.balaJugador;

public class EstrategiaGreen implements EstrategiaDisparo {
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase("Pixel")) return "Flecha";
        return "Pixel"; 
    }
}
