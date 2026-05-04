package modelo;

public class EstrategiaDisparoGreen implements EstrategiaDisparo {

	private int ammoLeft;
	
	public EstrategiaDisparoGreen() {
		ammoLeft = 30;
	}
	
	@Override
	public String cambiarDisparo(String pDisparo) {
		switch (pDisparo) {
		case EntityFactory.BALA_PIXEL_ID:
			return EntityFactory.BALA_FLECHA_ID;
		case EntityFactory.BALA_FLECHA_ID:
		default:
			return EntityFactory.BALA_PIXEL_ID;
		}
	}

	@Override
	public boolean puedeDisparar(String pDisparo) {
		switch (pDisparo) {
		case EntityFactory.BALA_PIXEL_ID:
			return true;
		case EntityFactory.BALA_FLECHA_ID:
			if (ammoLeft > 0)
				return true;			
		}
		
		return false;
	}

	@Override
	public void disparar(String pDisparo) {
		if (pDisparo == EntityFactory.BALA_FLECHA_ID)
			ammoLeft--;
	}

}
