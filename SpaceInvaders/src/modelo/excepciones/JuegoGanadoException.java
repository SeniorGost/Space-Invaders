package modelo.excepciones;

public class JuegoGanadoException extends JuegoCambiadoException {
	public static final int TIPO = 1;
	public JuegoGanadoException() {
		super(TIPO);
	}
}
