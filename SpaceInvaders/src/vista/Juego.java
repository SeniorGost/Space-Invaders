package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BalaJugador;
// MODELO SE IMPORTA PARA LA LINEA 26 Y PARA EL OBSERVABLE, EL RESTO SOLO PARA EL OBSERVABLE
import modelo.Jugador;
import modelo.Alien;
import modelo.Modelo;
import java.awt.GridLayout;
import javax.swing.JLabel;

//para Observable-Observer todas las clases que observan implementan (herencia de interfaz) Observer
public class Juego extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//creamos una matriz de JLabels para no perder las JLabels y asi poder modificarlas despues (De momento va a tener un tamaño fijo de 60 filas *100 columnas)
	private JLabel[][] pixel = new JLabel[60][100];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego frame = new Juego(Modelo.getModelo());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Juego(Observable Modelo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//esto de aqui hace funcionar la imagen de fondo
	    contentPane = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics componente) {
		         super.paintComponent(componente);
		         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
		        }
		};
		
		contentPane.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		    	//Aqui llamarias a controlador, esto es por ahora para comprobar que funciona y como
		    	
		    	//e es un código que equivale al evento de la key que se ha pulsado (para A es 65, para B 68)
		        System.out.println(e.getKeyCode());
		        
		        if (e.getKeyCode() == 65 ) { 
		            System.out.println("Se ha pulsado A");
		            int[] objeto = new int[]{-1,-1,10,20};
		            moverJugador(objeto);
		        }
		        else if (e.getKeyCode() == 68 ) { 
		            System.out.println("Se ha pulsado B");
		            int[] objeto = new int[]{10,20,11,20};
		            moverJugador(objeto);
		        }
		    }
		});
		
		//esto lo que hace es hacer que el Pane empieze a capturar teclas, es como clicar en una barra donde puedes empezar a rellenar
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(60, 100, 0, 0));
		
		for (int f = 0; f < 60; f++) {
			for(int c = 0; c < 100; c++){
				//Todas las Jlabels se añaden a la matriz para no perderlas y luego se meten en la vista
		        pixel[f][c] = new JLabel();
		        contentPane.add(pixel[f][c]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
	    // 	Metodo de actualización de la vista 
		
		//Yo aqui entiendo que cuando le llega una instancia le llegara SOLO la de una bala o un jugador cada vez que se le envia, por aquello de que se envia una bala, se avanza en el bucle, se envia otra...;
		//Tambien entiendo que se le enviará lo siguiente: (11,20,12,20) -> Posicion antigua (a borrar), Posicion nueva (A escribir)
		
		if (o instanceof Jugador)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				moverJugador(datos);
			}
		}
		
		if (o instanceof BalaJugador)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				moverBala(datos);
			}
		}
		
		if (o instanceof Alien)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				moverMarciano(datos);
			}
		}
		
		if (o instanceof Modelo)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				cambiarPantalla(datos[0]);
			}
		}
		
	}
	
	private void moverJugador (int posicion[] ){
    //se envia primero la fila y luego la columna (V -> viejo, N -> Nuevo)    
		int fV = posicion[0];
	    int cV = posicion[1];
	    int fN = posicion[2];
	    int cN = posicion[3];
	    
	//la primera vez que se escribe, posicion vieja esta a -1,-1
	    if(!(posicion[0] == -1 && posicion[1] == -1) ){
	    	
	    	pixel[fV][cV].setOpaque(false); // asi no se ve el color
	    	pixel[fV][cV].setBackground(null);
	    	
	    }
	  //el jugador es ROJO
	    
	    pixel[fN][cN].setOpaque(true); // para que se vea el color
	    pixel[fN][cN].setBackground(Color.RED);
	}
	
	private void moverMarciano (int posicion[] ){
	    //se envia primero la fila y luego la columna (V -> viejo, N -> Nuevo)    
			int fV = posicion[0];
		    int cV = posicion[1];
		    int fN = posicion[2];
		    int cN = posicion[3];
		    
		//la primera vez que se escribe, posicion vieja esta a -1,-1
		    if(!(posicion[0] == -1 && posicion[1] == -1) ){
		    	
		    	pixel[fV][cV].setOpaque(false); // asi no se ve el color
		    	pixel[fV][cV].setBackground(null);
		    	
		    }
		  //el marciano es VERDE
		    pixel[fN][cN].setOpaque(true); // para que se vea el color
		    pixel[fN][cN].setBackground(Color.GREEN);
		}
	
	private void moverBala (int posicion[] ){
	    //se envia primero la fila y luego la columna (V -> viejo, N -> Nuevo)    
			int fV = posicion[0];
		    int cV = posicion[1];
		    int fN = posicion[2];
		    int cN = posicion[3];
		    
		//la primera vez que se escribe, posicion vieja esta a -1,-1
		    if(!(posicion[0] == -1 && posicion[1] == -1) ){
		    	
		        pixel[fV][cV].setOpaque(false); // asi no se ve el color
		    	pixel[fV][cV].setBackground(null);
		    	
		    }
		  //la bala es BLANCA
		    pixel[fN][cN].setOpaque(true); // para que se vea el color
		    pixel[fN][cN].setBackground(Color.WHITE);
		}
	
	//Este es el tema, cada vez que se notifica a los observers, se notifica a todas las pantallas a la vez entonces se tiene que poner una sentencia como esta, donde se distinga el numero de pantalla en que estamos
	private void cambiarPantalla (int pValor){
		
		if (pValor!=1)
		{
			this.setVisible(false);
		}
		else
		{
			this.setVisible(true);
		}
	}

}
