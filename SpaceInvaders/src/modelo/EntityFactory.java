package modelo;

public class EntityFactory {
	
	public static final String NAVE_GREEN_ID = "NaveGreen";
	public static final String NAVE_BLUE_ID = "NaveBlue";
	public static final String NAVE_RED_ID = "NaveRed";
	
	public static final String BALA_PIXEL_ID = "BalaPixel";
	public static final String BALA_FLECHA_ID = "BalaFlecha";
	public static final String BALA_ROMBO_ID = "BalaRombo";
	
	public static final String ALIEN_DEFAULT_ID = "AlienDefault";

	private static EntityFactory miEntityFactory;
	
	private EntityFactory() {}

	public static EntityFactory getEntityFactory() {
		if (miEntityFactory == null) {
			miEntityFactory = new EntityFactory();
		}
		return miEntityFactory;
	}
	
	public GameEntity generate(String id, int offsetX, int offsetY) {
		GameEntity rdo;
		
		switch (id) {
		// Naves
		case NAVE_GREEN_ID:
			rdo = new NaveGreen();
			break;
		case NAVE_BLUE_ID:
			rdo = new NaveBlue();
			break;
		case NAVE_RED_ID:
			rdo = new NaveRed();
			break;
		// Balas
		case BALA_PIXEL_ID:
			rdo = new BalaPixel(offsetX, offsetY);
			break;
		case BALA_FLECHA_ID:
			rdo = new BalaFlecha(offsetX, offsetY);
			break;
		case BALA_ROMBO_ID:
			rdo = new BalaRombo(offsetX, offsetY);
			break;
		
		// Aliens
		case ALIEN_DEFAULT_ID:
		default:
			rdo = new AlienDefault(offsetX, offsetY);
			break;
		}
		
		return rdo;
	}
}
