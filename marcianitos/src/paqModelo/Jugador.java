package paqModelo;

import java.util.Observable;

public class Jugador extends Observable{
	
	private int pos;
	private int vidas;
	
	public Jugador(int pos, int vidas) 
	{
		this.pos = pos;
		this.vidas = vidas;
		
	}
	public void Declarator() 
	{
		Artilleria artilleria = Artilleria.getArtilleria();
		artilleria.Declarator();
	}

}
