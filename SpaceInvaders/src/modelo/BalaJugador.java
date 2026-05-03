package modelo;

public abstract class BalaJugador extends GameEntity {
	
	public BalaJugador(int hp) {
		super(hp);
		setCollisionHandler(new CHBalasJugador());
	}

}
