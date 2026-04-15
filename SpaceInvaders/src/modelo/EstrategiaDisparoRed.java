package modelo;

public class EstrategiaDisparoRed implements EstrategiaDisparo {

	private int ammoLeftF;
	private int ammoLeftR;
	
	public EstrategiaDisparoRed() {
		ammoLeftF = 30;
		ammoLeftR = 20;
	}
	
	@Override
	public String cambiarDisparo(String pDisparo) {
		switch (pDisparo) {
		case EntityFactory.BALA_PIXEL_ID:
			return EntityFactory.BALA_FLECHA_ID;
		case EntityFactory.BALA_FLECHA_ID:
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
		case EntityFactory.BALA_FLECHA_ID:
			if (ammoLeftF > 0)
				return true;			
		case EntityFactory.BALA_ROMBO_ID:
			if (ammoLeftR > 0)
				return true;			
		}
		
		return false;
	}

	@Override
	public void disparar(String pDisparo) {
		if (pDisparo == EntityFactory.BALA_FLECHA_ID)
			ammoLeftF--;
		if (pDisparo == EntityFactory.BALA_ROMBO_ID)
			ammoLeftR--;
	}

}
