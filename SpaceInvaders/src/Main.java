import modelo.Modelo;
import vista.*; //esto es que necesitamos las dos clases de vista, ander no funes 

public class Main {

	public static void main(String[] args) {
		//MODELO//
		Modelo modelo = Modelo.getModelo();
		
		//Creamos Pantalla 1//
		Juego pantalla1=new Juego(modelo);
		
		//Hacemos Visible//
		pantalla1.setVisible(true);
		
		//Creamos Pantalla 2//
		Menu pantalla2=new Menu(modelo);
		
		//Creamos Pantalla 3//
	    Perdedor pantalla3=new Perdedor(modelo);
				
	    //Creamos Pantalla 4//
	    Ganador pantalla4=new Ganador(modelo);
		
	}
	
}


