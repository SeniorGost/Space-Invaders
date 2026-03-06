package paqModelo;

import java.util.ArrayList;


public class Artilleria {
	
	private static Artilleria myelvis;
	private ArrayList<Bala> artilleria;
	
	private Artilleria(){
		
	}
	
	public static Artilleria getArtilleria(){
		if (myelvis==null)
		{
			myelvis=new Artilleria();
			myelvis.artilleria=new ArrayList<Bala>();
		}
		return myelvis;
	}
	
	public void Declarator()
	{
	
		Flota flota = Flota.getFlota();
		
	}
	
}
