import paqModelo.Modelo;
import paqVista.Juego;
import paqVista.Menu;

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
		
	}
	
}
