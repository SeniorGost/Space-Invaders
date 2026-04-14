import modelo.Modelo;
import vista.*; //esto es que necesitamos las dos clases de vista, ander no funes 

public class Main {

	public static void main(String[] args) {
		//MODELO//
		Modelo modelo = Modelo.getModelo();
		
		//Creamos Pantalla 0//
		Menu pantalla=new Menu(modelo);
		
		//Hacemos Visible//
		pantalla.setVisible(true);
		
		//Creamos Pantalla 1//
		Menu2 pantalla1=new Menu2(modelo);
		
		//Creamos Pantalla 2//
		Juego pantalla2=new Juego(modelo);
		
		//Creamos Pantalla 3//
	    Perdedor pantalla3=new Perdedor(modelo);
				
	    //Creamos Pantalla 4//
	    Ganador pantalla4=new Ganador(modelo);
		
	}
	
}
