package modelo;

public class GeneradorBalas {
	private static GeneradorBalas miGenerador;
	
	private String balaSeleccionada;
	private EstrategiaDisparo estrategiaActual;
	
	private GeneradorBalas() {}

	public static GeneradorBalas getGeneradorBalas() {
		if (miGenerador == null) {
			miGenerador = new GeneradorBalas();
		}
		return miGenerador;
	}
	
	public void setEstrategia(int pTipo) {
		switch (pTipo) {
		case Nave.NAVE_GREEN:
			estrategiaActual = new EstrategiaDisparoGreen();
			break;
		case Nave.NAVE_BLUE:
			estrategiaActual = new EstrategiaDisparoBlue();
			break;
		case Nave.NAVE_RED:
			estrategiaActual = new EstrategiaDisparoRed();
			break;
		}
		
		balaSeleccionada = estrategiaActual.cambiarDisparo("");
	}
	
	public void cambiarBalas() {
		balaSeleccionada = estrategiaActual.cambiarDisparo(balaSeleccionada);
	}
	
	public BalaJugador generarBalaJugador(int posX, int posY) {
		if (!estrategiaActual.puedeDisparar(balaSeleccionada)) {
			balaSeleccionada = estrategiaActual.cambiarDisparo(balaSeleccionada);
			generarBalaJugador(posX, posY);
		}
		
		estrategiaActual.disparar(balaSeleccionada);
		
		return (BalaJugador) EntityFactory.getEntityFactory().generate(balaSeleccionada, posX, posY);
	}
}
