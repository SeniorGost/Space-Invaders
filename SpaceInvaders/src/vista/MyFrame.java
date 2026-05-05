package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private static MyFrame frame;
	private Menu menu;
	private MenuREAL menuREAL;
	private Menu2 menu2;
	private Menu3 menu3;
	private Juego panelJuego;
	private Perdedor panelPerdedor;
	private Ganador panelGanador;
	private Creditos creditos;
	private MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//esto es para pillar el tamaño de la pantalla del usuario
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		//Mostramos por pantalla por si las moscas
		System.out.println("Dimensiones de Pantalla PC: " + width + "," + height);

		//le damos a la ventana las dimensiones de la pantalla de usuario
		//Los dos primeros numeros son la posicion en la que 'spawnea' la ventana, la pongo en el 0,0 porque va ha ser tan grande como la pantalla, luego de estar movida se vería cortada
		setBounds(0, 0, (int)width, (int)height);
		
		setExtendedState(MAXIMIZED_BOTH);
		
		//Creamos Pantalla 0//
		menu = new Menu();

		//Hacemos Visible//
		menu.setVisible(true);
		
		//Añadimos al Frame(para que salga Pantalla 0 por defecto)//
		getContentPane().add(menu);
	    setVisible(true);
		
	    //Creamos Pantalla REAL (Pantalla 5)//
	    menuREAL = new MenuREAL();
	    
		//Creamos Pantalla 1//
		menu2 = new Menu2();
		
		//Creamos Pantalla 6//
		menu3 = new Menu3();

		//Creamos Pantalla 2//
		panelJuego = new Juego();

		//Creamos Pantalla 3//
	    panelPerdedor = new Perdedor();

	    //Creamos Pantalla 4//
	    panelGanador = new Ganador();
	    
	    //Creamos Pantalla 7//
	    creditos = new Creditos();
	}

	public static MyFrame getMyFrame() {
		if(frame == null)
			frame = new MyFrame();
		return frame;
	}

	public void cambiarVentana(int panelID) {
		getContentPane().removeAll();
		switch (panelID) {
		case 0:
			getContentPane().add(menu);
			menu.setFocusable(true);
			menu.requestFocusInWindow();
			break;
		case 1:
			getContentPane().add(menu2);
			menu2.setFocusable(true);
			menu2.requestFocusInWindow();
			break;
		case 2:
			getContentPane().add(panelJuego);
			panelJuego.setFocusable(true);
			panelJuego.requestFocusInWindow();
			break;
		case 3:
			getContentPane().add(panelGanador);
			panelGanador.setFocusable(true);
			panelGanador.requestFocusInWindow();
			break;
		case 4:
			getContentPane().add(panelPerdedor);
			panelPerdedor.setFocusable(true);
			panelPerdedor.requestFocusInWindow();
			break;
		case 5:
			getContentPane().add(menuREAL);
			menuREAL.setFocusable(true);
			menuREAL.requestFocusInWindow();
			break;
		case 6:
			//Como hay que reiniciar el mensaje de 'escribe el tamaño' de haber cambiado cada vez que se relanza la ventana, re-inicializamos la ventana cada vez que se vuelve a ella
			menu3 = new Menu3();
			getContentPane().add(menu3);
			menu3.setFocusable(true);
			menu3.requestFocusInWindow();
			break;
		case 7:
			getContentPane().add(creditos);
			creditos.setFocusable(true);
			creditos.requestFocusInWindow();
			break;
		}
		repaint();
		revalidate();
	}
}
