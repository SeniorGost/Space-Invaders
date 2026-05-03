package modelo;

public abstract class Alien extends GameEntity {
	
	public Alien(int hp) {
		super(hp);
		setCollisionHandler(new CHAlien());
	}
}
