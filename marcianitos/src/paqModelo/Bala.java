package paqModelo;

import java.util.Observable;

public class Bala extends Observable{
	
	private int posicion;
	
	public Bala(int pos) 
	{
		this.posicion = pos;
	}

}
