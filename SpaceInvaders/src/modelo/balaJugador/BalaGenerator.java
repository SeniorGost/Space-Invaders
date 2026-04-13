package modelo.balaJugador;

public class BalaGenerator {
    private static BalaGenerator balasGenerator;
    private EstrategiaDisparo estrategiaActual; // El Strategy

    private BalaGenerator() {
        // Por defecto, empezamos con la básica
        this.estrategiaActual = new EstrategiaGreen(); 
    }
    
    public static BalaGenerator getBalasGenerator() {
        if(balasGenerator == null) 
            balasGenerator = new BalaGenerator();
        return balasGenerator;
    }

    // Metodo para que el juego cambie la nave (y su estrategia)
    public void setEstrategia(EstrategiaDisparo nuevaEstrategia) {
        this.estrategiaActual = nuevaEstrategia;
    }
    
    /**
     * Este es el método que llamarás cuando la Vista envíe algo
     * @param inputDeVista Lo que recojas de las flechas del teclado
     */
    public BalaJugador generarDesdeVista(String inputDeVista, int posX, int posY) {
        // 1. El Strategy decide QUÉ tipo de bala toca
        String tipoCalculado = estrategiaActual.elegirTipoBala(inputDeVista);
        
        // 2. Usamos la Factory para crearla
        return BalaFactory.getBalaFactory().generate(tipoCalculado, posX, posY);
    }
}