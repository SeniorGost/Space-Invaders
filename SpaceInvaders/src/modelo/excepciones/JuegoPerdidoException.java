package modelo.excepciones;

/**
 * Excepcion de TIPO 0.
 */
public class JuegoPerdidoException extends JuegoCambiadoException {
	public static final int TIPO = 0;
	public JuegoPerdidoException() {
		super(TIPO);
	}
}