package modelo.balaJugador;

public class EstrategiaGreen implements EstrategiaDisparo {
	private int cuentaFlecha;
	
	public EstrategiaGreen() {
		cuentaFlecha = 30;
	}
	
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase(DISPARO_PIXEL)) return DISPARO_FLECHA;
        return DISPARO_PIXEL; 
    }
    
    public boolean puedeDisparar(String disparoTipo) {
        //Aqui tratamos lo de las balas especiales
    	switch (disparoTipo) {
			case DISPARO_FLECHA:
				// Si ya se ha llegado al limite indica que no puede disparar
				if(cuentaFlecha <= 0) {
					return false;
				}
				// Decrementa los contadores de balas especiales de ser necesario
				break;
		}
    	return true;
    }
    
    @Override
    public void disparar(String disparoTipo) {
    	switch (disparoTipo) {
			case DISPARO_FLECHA:
				cuentaFlecha--;
				break;
		}
    }
}
