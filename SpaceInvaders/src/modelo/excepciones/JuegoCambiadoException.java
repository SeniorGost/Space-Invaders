package modelo.excepciones;

public abstract class JuegoCambiadoException extends Exception {
	private int tipo;
	public JuegoCambiadoException(int tipo) {
		super();
		this.tipo = tipo;
	}
	public int getTipo() {
		return tipo;
	}
}
