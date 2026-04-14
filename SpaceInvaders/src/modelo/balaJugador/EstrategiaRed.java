package modelo.balaJugador;

public class EstrategiaRed implements EstrategiaDisparo {
	private int cuentaRombo;
	private int cuentaFlecha;
	
	public EstrategiaRed() {
		cuentaRombo = 20;
		cuentaFlecha = 30;
	}
	
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase(DISPARO_PIXEL)) return DISPARO_ROMBO;
        if (inputVista.equalsIgnoreCase(DISPARO_ROMBO)) return DISPARO_FLECHA;
        return DISPARO_PIXEL;
    }
    
    public boolean puedeDisparar(String disparoTipo) {
        //Aqui tratamos lo de las balas especiales
    	switch (disparoTipo) {
		case DISPARO_ROMBO:
			// Si ya se ha llegado al limite indica que no puede disparar
			if(cuentaRombo <= 0) {
				return false;
			}
			break;
		
		case DISPARO_FLECHA:
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
		case DISPARO_ROMBO:
			cuentaRombo--;
			break;
		
		case DISPARO_FLECHA:
			cuentaFlecha--;
			break;
		}
    }
}
