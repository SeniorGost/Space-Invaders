package modelo.balaJugador;

import modelo.naves.Nave;

public class BalaGenerator {
    private static BalaGenerator balasGenerator;
    private String disparoTipo;

    private EstrategiaDisparo estrategiaActual; // El Strategy

    private BalaGenerator() {
        // Por defecto, empezamos con la básica
        this.estrategiaActual = new EstrategiaGreen();
        this.disparoTipo = "Pixel";
    }
    
    public static BalaGenerator getBalasGenerator() {
        if(balasGenerator == null) 
            balasGenerator = new BalaGenerator();
        return balasGenerator;
    }

    // Metodo para que el juego cambie la nave (y su estrategia)
    public void setEstrategia(int nave) {
    	// Esto cambia la estrategia
    	this.cambiarEstrategiaSegunNave(nave);
    	// Lo mismo que 'disparoTipo = "Pixel";' pero mas elegante. El disparo con el que empieza cualquier nave siempre son pixeles
        disparoTipo = estrategiaActual.elegirTipoBala(" ");
    }
    
    private void cambiarEstrategiaSegunNave(int tipo) {
		
        switch (tipo) 
        {
        case Nave.NAVE_GREEN:
            EstrategiaDisparo shrek = new EstrategiaGreen();
            this.estrategiaActual = shrek;
            break;
        case Nave.NAVE_BLUE:
            EstrategiaDisparo rei_chikita = new EstrategiaBlue();
            this.estrategiaActual = rei_chikita;
            break;
        case Nave.NAVE_RED:
        default:
        	 EstrategiaDisparo NaveGenerica = new EstrategiaRed();
        	 this.estrategiaActual = NaveGenerica;
        }

    }
    
    public void cambiarBala(){
    	disparoTipo = estrategiaActual.elegirTipoBala(disparoTipo);
    }
    
    /**
     * Este es el método que llamarás cuando la Vista envíe algo
     * @param inputDeVista Lo que recojas de las flechas del teclado
     */
    public BalaJugador generarDesdeVista(int posX, int posY) {
    	
    	if (!estrategiaActual.puedeDisparar(disparoTipo)) {
    		disparoTipo = estrategiaActual.elegirTipoBala(disparoTipo);
    		return generarDesdeVista(posX, posY);
    	}
    	
    	estrategiaActual.disparar(disparoTipo);
    	
    	//Usamos la Factory para crearla
        return BalaFactory.getBalaFactory().generate(disparoTipo, posX, posY);
    }
}
