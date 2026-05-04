package modelo.naves;

import modelo.CHNave;
import modelo.GameEntity;

public abstract class Nave extends GameEntity {
	public static final int NAVE_GREEN = 0;
	public static final int NAVE_BLUE = 1;
	public static final int NAVE_RED = 2;
	
	public Nave(int hp) {
		super(hp);
		setCollisionHandler(new CHNave());
	}
}
