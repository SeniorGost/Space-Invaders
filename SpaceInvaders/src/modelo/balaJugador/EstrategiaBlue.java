package modelo.balaJugador;

public class EstrategiaBlue implements EstrategiaDisparo {
	
	private int cuentaRombo;
	
	public EstrategiaBlue() {
		cuentaRombo = 20;
	}
	
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase("Pixel")) return "Rombo";
        return "Pixel";
    }
    
    public boolean puedeDisparar(String disparoTipo) {
        // Aqui tratamos lo de las balas especiales
    	switch (disparoTipo) {
			case "Rombo":
				// Si ya se ha llegado al limite indica que no puede disparar
				if(cuentaRombo <= 0) {
					return false;
				}
				 // Decrementa los contadores de balas especiales de ser necesario
				cuentaRombo--;
				break;
		}
    	return true;
    }
    
    @Override
    public void disparar(String disparoTipo) {
    	switch (disparoTipo) {
		case "Rombo":
			cuentaRombo--;
			break;
		}
    }
}
