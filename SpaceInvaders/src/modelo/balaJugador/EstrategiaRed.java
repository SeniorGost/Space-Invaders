package modelo.balaJugador;

public class EstrategiaRed implements EstrategiaDisparo {
	private int cuentaRombo;
	private int cuentaFlecha;
	
	public EstrategiaRed() {
		cuentaRombo = 30;
		cuentaFlecha = 20;
	}
	
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase("Pixel")) return "Rombo";
        if (inputVista.equalsIgnoreCase("Rombo")) return "Flecha";
        return "Pixel";
    }
    
    public boolean puedeDisparar(String disparoTipo) {
        //Aqui tratamos lo de las balas especiales
    	switch (disparoTipo) {
		case "Rombo":
			// Si ya se ha llegado al limite indica que no puede disparar
			if(cuentaRombo <= 0) {
				return false;
			}
			break;
		
		case "Flecha":
			// Si ya se ha llegado al limite indica que no puede disparar
			if(cuentaFlecha <= 0) {
				return false;
			}
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
		
		case "Flecha":
			cuentaFlecha--;
			break;
		}
    }
}
