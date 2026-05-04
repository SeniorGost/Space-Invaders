package modelo.excepciones;

/**
 * Excepcion de TIPO 1.
 */
public class JuegoGanadoException extends JuegoCambiadoException {
	public static final int TIPO = 1;
	public JuegoGanadoException() {
		super(TIPO);
	}
}
