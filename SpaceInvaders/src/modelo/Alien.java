package modelo;

public abstract class Alien extends GameEntity {
	
	public Alien() {
		setCollisionHandler(new CHAlien());
	}
}
