package paqModelo;

import java.util.ArrayList;

public class Flota {

	private static Flota myelvis;
	private ArrayList<Marciano> flota;
	
	private Flota(){
		
		
	}
	
	public static Flota getFlota(){
		if (myelvis==null)
		{
			myelvis=new Flota();
			myelvis.flota=new ArrayList<Marciano>();
		}
		return myelvis;
	}
	
}
