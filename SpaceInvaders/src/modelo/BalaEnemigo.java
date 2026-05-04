package modelo;

public abstract class BalaEnemigo extends GameEntity {

	public BalaEnemigo(int hp) {
		super(hp);
		setCollisionHandler(new CHBalaEnemigo());
	}

}
