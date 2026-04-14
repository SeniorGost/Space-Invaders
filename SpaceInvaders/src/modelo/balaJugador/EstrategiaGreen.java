package modelo.balaJugador;

public class EstrategiaGreen implements EstrategiaDisparo {
	private int cuentaFlecha;
	
	public EstrategiaGreen() {
		cuentaFlecha = 20;
	}
	
    @Override
    public String elegirTipoBala(String inputVista) {
        if (inputVista.equalsIgnoreCase("Pixel")) return "Flecha";
        return "Pixel"; 
    }
    
    public boolean puedeDisparar(String disparoTipo) {
        //Aqui tratamos lo de las balas especiales
    	switch (disparoTipo) {
			case "Flecha":
				// Si ya se ha llegado al limite indica que no puede disparar
				if(cuentaFlecha <= 0) {
					return false;
				}
				// Decrementa los contadores de balas especiales de ser necesario
				cuentaFlecha--;
				break;
		}
    	return true;
    }
    
    @Override
    public void disparar(String disparoTipo) {
    	switch (disparoTipo) {
			case "Flecha":
				cuentaFlecha--;
				break;
		}
    }
}
