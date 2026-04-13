package modelo.balaJugador;

public class EstrategiaGreen implements EstrategiaDisparo {
    @Override
    public String elegirTipoBala(String inputVista) {
        // Suponiendo que el input es "Siguiente" o un nombre específico
        if (inputVista.equalsIgnoreCase("Flecha")) return "Flecha";
        return "Pixel"; 
    }
}