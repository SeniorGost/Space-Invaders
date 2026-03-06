package paqModelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;

//esto no tiene lo de los ticks pq no me he metido con eso
// 			"darle tiempo al tiempo" -refran popular

//para Observable-Observer todas las clases Modelo que envian info heredan de Observable
public class Modelo extends Observable{
	
	//aquí irian (todos) los parámetros que ya me direis amiges :D
	private int ventana;
	private ArrayList<Jugador> jugadores;
	private static Modelo myelvis;
	
	private Modelo() 
	{
		//aqui se inicializarían dichos parámetros en un mundo ideal 0.0
		
		//luego empezamos la cascada de declaraciones
		this.Declarator();
	}
	
	public static Modelo getModelo() {
		if (myelvis==null)
		{
			myelvis=new Modelo();
			myelvis.jugadores=new ArrayList<Jugador>();
		}
		return myelvis;
	}
	
	private void Declarator() 
	{
		jugadores = new ArrayList<Jugador>();
		Jugador player1 = new Jugador(10,5);
		player1.Declarator();
	}
	
}
