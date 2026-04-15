package modelo;

public class EstrategiaDisparoBlue implements EstrategiaDisparo {
	private int ammoLeft;
	
	public EstrategiaDisparoBlue() {
		ammoLeft = 20;
	}
	
	@Override
	public String cambiarDisparo(String pDisparo) {
		switch (pDisparo) {
		case EntityFactory.BALA_PIXEL_ID:
			return EntityFactory.BALA_ROMBO_ID;
		case EntityFactory.BALA_ROMBO_ID:
		default:
			return EntityFactory.BALA_PIXEL_ID;
		}
	}

	@Override
	public boolean puedeDisparar(String pDisparo) {
		switch (pDisparo) {
		case EntityFactory.BALA_PIXEL_ID:
			return true;
		case EntityFactory.BALA_ROMBO_ID:
			if (ammoLeft > 0)
				return true;			
		}
		
		return false;
	}

	@Override
	public void disparar(String pDisparo) {
		if (pDisparo == EntityFactory.BALA_ROMBO_ID)
			ammoLeft--;
	}

}
