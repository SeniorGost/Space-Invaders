package modelo;

public abstract class BalaJugador extends GameEntity {
	
	public BalaJugador() {
		setCollisionHandler(new CHBalasJugador());
	}

}
