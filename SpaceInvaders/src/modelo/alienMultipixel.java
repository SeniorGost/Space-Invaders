package modelo;

import java.util.ArrayList;

public class alienMultipixel extends Alien {

	ArrayList<Alien> listaPixeles;
	
	public alienMultipixel(int x, int y) {
		super(x, y);
		
		listaPixeles = new ArrayList<Alien>();
		
		for (int i = -2; i <= 2; i++) 
			for (int j = -2; j <= 2; j++)
				if (i == j || i == -j)
					listaPixeles.add(new alienPixel(x + i, y + j));
		
		
		// TODO Auto-generated constructor stub
	}
	
	 public boolean tick(int deltaX, int deltaY, int jugadorX, int jugadorY) throws JuegoPerdidoException {
		 boolean reachedWall = false;
		 
		 for (Alien a : listaPixeles) {
			 reachedWall = a.tick(deltaX, deltaY, jugadorX, jugadorY) || reachedWall;
		 }
		 
		 return reachedWall;
	    }
	    

	    public boolean hit(int balaX, int balaY) {
	    	for (Alien a : listaPixeles) {
				 if(a.hit(balaX, balaY)) 
					 return true;
	    	}
	    	return false;
	    }

	    public void disparar() {
	        // De momento vacío 
	    }
}
