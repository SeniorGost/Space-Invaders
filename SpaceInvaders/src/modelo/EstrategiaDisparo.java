package modelo;

public interface EstrategiaDisparo {
	public String cambiarDisparo(String pDisparo);
	public boolean puedeDisparar(String pDisparo);
	public void disparar(String pDisparo);
}
