package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BalaJugador;
import modelo.Flota;
// MODELO SE IMPORTA PARA LA LINEA 26 Y PARA EL OBSERVABLE, EL RESTO SOLO PARA EL OBSERVABLE
import modelo.Jugador;
import modelo.Alien;
import modelo.ArtilleriaJugador;
import modelo.Modelo;
import java.awt.GridLayout;
import javax.swing.JLabel;

//para Observable-Observer todas las clases que observan implementan (herencia de interfaz) Observer
public class Juego extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//creamos una matriz de JLabels para no perder las JLabels y asi poder modificarlas despues (De momento va a tener un tamaño fijo de 60 filas *100 columnas)
	//private JLabel[][] pixel= new JLabel[60][100];
	
	//forma alt para matriz de cualquier tamaño (commentada ahora para hacer las pruebas)
	private JLabel[][] pixel;
	private ArrayList<JLabel> pixelesPintados = new ArrayList<JLabel>();
	private int filas;
	private int columnas;
	

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
	public Juego(Observable modelo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		Modelo.getModelo().addObserver(this);
		Jugador.getJugador().addObserver(this);
		Flota.getFlota().addObserver(this);
		ArtilleriaJugador.getArtilleria().addObserver(this);
		
		//esto de aqui hace funcionar la imagen de fondo
	    contentPane = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics componente) {
		         super.paintComponent(componente);
		         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
		    }
		};
		
		contentPane.addKeyListener(new Controller());
		
		//esto lo que hace es hacer que el Pane empieze a capturar teclas, es como clicar en una barra donde puedes empezar a rellenar
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(60, 100, 0, 0));
	 
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
	    // 	Metodo de actualización de la vista 
		
		//Yo aqui entiendo que cuando le llega una instancia le llegara SOLO la de una bala o un jugador cada vez que se le envia, por aquello de que se envia una bala, se avanza en el bucle, se envia otra...;
		//De momento se envia solo la posicion actual
		
		if (o instanceof Jugador)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				cleanClean(); //como de momento solo hay un jugador, la vez que le llega instancia de jugador se limpia la pantalla
				System.out.println("J: " + datos[0] + " " + datos[1]);
				moverJugador(datos);
			}
		}
		
		if (o instanceof ArtilleriaJugador)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				System.out.println("B: " + datos[0] + " " + datos[1]);
				moverBala(datos);
			}
		}
		
		if (o instanceof Flota)
		{
			if (arg instanceof int[])
			{
				
				int[] datos=(int[])arg;
			    System.out.println("M: " + datos[0] + " " + datos[1]);
				moverMarciano(datos);
			}
		}
		
		// para hacer lo de matriz de cualquier tamaño voy a necesitar que me envieis el tamaño por aqui
		if (o instanceof Modelo)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				if(datos[0]==1){
					
					//esto le dice que si se pasa a la pantalla 1, que primero defina la matriz con las dimensiones que me pasa modelo
					definirMatriz(datos[1],datos[2]);
					
				}
				cambiarPantalla(datos[0]);
			}
		}
		
	}
	
	private void moverJugador (int posicion[] ){
    //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	    int fN = posicion[1];
	    int cN = posicion[0];
	    
	    //el jugador es ROJO
	    pixel[fN][cN].setBackground(Color.RED);  // le da el color
        pixel[fN][cN].setOpaque(true); // para que se vea el pixel
        
        pixelesPintados.add(pixel[fN][cN]);
	}
	
	private void moverMarciano (int posicion[] ){
	    //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	    int fN = posicion[1];
	    int cN = posicion[0];
		    
		//el marciano es VERDE
		pixel[fN][cN].setBackground(Color.GREEN); //le da el color
		pixel[fN][cN].setOpaque(true); // para que se vea el pixel

        pixelesPintados.add(pixel[fN][cN]);
	}
	
	private void moverBala (int posicion[] ){
	   //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	   int fN = posicion[1];
	   int cN = posicion[0];
		    
	   //la bala es BLANCA
	   pixel[fN][cN].setBackground(Color.WHITE); //le da el color
	   pixel[fN][cN].setOpaque(true); // para que se vea el pixel

       pixelesPintados.add(pixel[fN][cN]);
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
	
	//este método limpia la pantalla
	private void cleanClean(){
		
		for (JLabel pixel : pixelesPintados) {
			pixel.setOpaque(false); // asi no se ve el pixel
			pixel.setBackground(null); // asi se quita el color
		}
		pixelesPintados.clear();
	}
	
	private void definirMatriz( int c, int f) {
		
		//si ya existen las labels (ya se ha jugado una partida) Hay que borrar las labels de la partida anterior
		if(pixel != null) {
			for (int fila = 0; fila < this.filas; fila++) {
				for(int columna = 0; columna < this.columnas; columna++){
			        contentPane.remove(pixel[fila][columna]);
				}
			}
		}
		
		 pixel= new JLabel[f][c];
		 filas = f;
		 columnas = c; 
		 
//			for (int fila = 0; fila < 60; fila++) {
//				for(int columna = 0; columna < 100; columna++){
//					//Todas las Jlabels se añaden a la matriz para no perderlas y luego se meten en la vista
//			        pixel[fila][columna] = new JLabel();
//			        contentPane.add(pixel[fila][columna]);
//				}
//			}
			
			//en el caso de matriz de cualquier tamaño (comentado para hacer las pruebas)
			
			for (int fila = 0; fila < this.filas; fila++) {
				for(int columna = 0; columna < this.columnas; columna++){
					//Todas las Jlabels se añaden a la matriz para no perderlas y luego se meten en la vista
			        pixel[fila][columna] = new JLabel();
			        contentPane.add(pixel[fila][columna]);
				}
			}
		
	}
	
	private class Controller implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
				Jugador.getJugador().moveX(false);
				break;
			case KeyEvent.VK_D:
				Jugador.getJugador().moveX(true);
				break;
			case KeyEvent.VK_W:
				Jugador.getJugador().moveY(false);
				break;
			case KeyEvent.VK_S:
				Jugador.getJugador().moveY(true);
				break;
			case KeyEvent.VK_ENTER:
				System.out.println("POW\n");
				Jugador.getJugador().shoot();
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {}
	}
}
