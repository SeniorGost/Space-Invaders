package modelo.excepciones;

public class JuegoPerdidoException extends JuegoCambiadoException {
	public static final int TIPO = 0;
	public JuegoPerdidoException() {
		super(TIPO);
	}
}