package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

//Para hacer la cola de instrucciones necesitamos estas dos
import java.util.Queue;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Flota;
import modelo.GeneradorBalas;
// MODELO SE IMPORTA PARA LA LINEA 26 Y PARA EL OBSERVABLE, EL RESTO SOLO PARA EL OBSERVABLE
import modelo.Jugador;
import modelo.ArtilleriaJugador;
import modelo.Modelo;
import modelo.Pixel;

import java.awt.GridLayout;
import javax.swing.JLabel;

//Estas dos son para hacer lo de pillar las dimensiones de la pantalla
import java.awt.Dimension;
import java.awt.Toolkit;

//para Observable-Observer todas las clases que observan implementan (herencia de interfaz) Observer
public class Juego extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//creamos una matriz de JLabels para no perder las JLabels y asi poder modificarlas despues (De momento va a tener un tamaño fijo de 60 filas *100 columnas)
	//private JLabel[][] pixel= new JLabel[60][100];
	
	//forma alt para matriz de cualquier tamaño (commentada ahora para hacer las pruebas)
	private int filas;
	private int columnas;
	
	private JLabel[][] pixel;
	private Color[][] pixelesQuePintar;
	private int[][] gridAcciones;
	
	private static final int ACCION_NADA = 0;
	private static final int ACCION_LIMPIAR = -1;
	private static final int ACCION_PINTAR = 1;

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

		//esto es para pillar el tamaño de la pantalla del usuario
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		//le damos a la ventana las dimensiones de la pantalla de usuario
		setBounds(0, 0, (int)width, (int)height);
		
		Modelo.getModelo().addObserver(this);
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
		// para hacer lo de matriz de cualquier tamaño voy a necesitar que me envieis el tamaño por aqui
		if (o instanceof Modelo)
		{

			//Aqui he cambiado el orden para revisar primero si se ha dado un tick y después si se ha cambiado a esta pantalla (Esto es porque como ocurrirá más frecuentemente que se de un tick a que se cambie a esta pantalla es ligeramente más eficiente)
			//Por cierto, esta comprobación de aqui abajo funciona porque desde modelo enviamos solo notifyobservers(-1), luego por lo del 'autoboxing' que hace el metodo, java asume que el -1 es integer, luego no hay instancia de int (Lo mismo ocurre si envias un objeto int tal que: int a = -1; notifyObservers(a); por defecto java lo combierte a integer al enviarlo)
			if (arg instanceof Integer) {
				cleanClean();
				pintarPantalla();		
			} else {
				//Si llegamos a este else sabemos que nos esta llegando instanceof int[], porque no puede ser de otra forma
				int[] datos=(int[])arg;
				
				if (datos[0] == Modelo.NOTIFY_WINDOW_CHANGE) {	
					if(datos[1]==2){
						//esto le dice que si se pasa a la pantalla 1, que primero defina la matriz con las dimensiones que me pasa modelo
						definirMatriz(datos[2],datos[3]);
					}
					//Esto es pq si no al empezar la partida escribes directamente encima de la matriz otra vez y se notan un segundo los restos de la otra
					else if(datos[1]==3 || datos[1]==4){
						cleanClean(); //System.out.println("SE ME PAROHHHH");
					}
					// 0 Menu, 1 Menu2, 2 Juego, 3 Ganar, 4 Perder
					cambiarPantalla(datos[1]);	
				}
				else if (datos[0] == Modelo.NOTIFY_PIXEL_POSITION) {

					int fN = datos[2];
				    int cN = datos[1];
				    int colorId = datos[3];
				    Color color;
				    
				    switch (colorId) {
					case Pixel.COLOR_ID_GREEN:
						color = Color.GREEN;
						break;
					case Pixel.COLOR_ID_BLUE:
						color = Color.CYAN;
						break;
					case Pixel.COLOR_ID_RED:
						color = Color.RED;
						break;
					
					// NAVES
					case Pixel.COLOR_ID_GREEN_CUERPO:
						color = new Color(0, 255, 0);
						break;
					case Pixel.COLOR_ID_GREEN_CEJA:
						color = new Color(106, 168, 79);
						break;
					case Pixel.COLOR_ID_GREEN_NARIZ:
						color = new Color(56, 118, 29);
						break;
					case Pixel.COLOR_ID_GREEN_DETALLES:
						color = new Color(255, 242, 204);
						break;
						
					case Pixel.COLOR_ID_BLUE_FRENTE:
						color = new Color(28, 69, 135);
						break;
					case Pixel.COLOR_ID_BLUE_FONDO:
						color = new Color(60, 120, 216);
						break;
					case Pixel.COLOR_ID_BLUE_BRAZOS:
						color = new Color(162, 196, 201);
						break;
					case Pixel.COLOR_ID_BLUE_DETALLES:
						color = new Color(234, 153, 153);
						break;
						
					case Pixel.COLOR_ID_RED_FRENTE:
						color = new Color(102, 0, 0);
						break;
					case Pixel.COLOR_ID_RED_FONDO:
						color = new Color(255, 0, 0);
						break;
					case Pixel.COLOR_ID_RED_DETALLES:
						color = new Color(153, 153, 153);
						break;
						
					case Pixel.COLOR_ID_WHITE:	
					default:
						color = Color.WHITE;
						break;
					}
				    
				    pixelesQuePintar[fN][cN] = color;
				    gridAcciones[fN][cN] = ACCION_PINTAR;
				}
			} 
		}
		
	}
	
	//Este es el tema, cada vez que se notifica a los observers, se notifica a todas las pantallas a la vez entonces se tiene que poner una sentencia como esta, donde se distinga el numero de pantalla en que estamos
	private void cambiarPantalla (int pValor) {
		if (pValor!=2)
			this.setVisible(false);
		else
			this.setVisible(true);
	}
	
	private void pintarPantalla() {
		
		
		for (int fila = 0; fila < this.filas; fila++) {
			for(int columna = 0; columna < this.columnas; columna++) {
				if (gridAcciones[fila][columna] == ACCION_PINTAR) {
					pixel[fila][columna].setBackground(pixelesQuePintar[fila][columna]); 
					pixel[fila][columna].setOpaque(true); 
					
					gridAcciones[fila][columna] = ACCION_LIMPIAR;
				}
			}			
		}
	}
	//este método limpia la pantalla
	private void cleanClean(){
		
		for (int fila = 0; fila < this.filas; fila++) {
			for(int columna = 0; columna < this.columnas; columna++) {
				if (gridAcciones[fila][columna] == ACCION_LIMPIAR) {					
					pixel[fila][columna].setOpaque(false);
					pixel[fila][columna].setBackground(null);
					
					gridAcciones[fila][columna] = ACCION_NADA;
				}
			}
		}
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
		 
		 	gridAcciones = new int[filas][columnas];
		 	pixelesQuePintar = new Color[filas][columnas];
		 	
			for (int fila = 0; fila < this.filas; fila++) {
				for(int columna = 0; columna < this.columnas; columna++){
					//Todas las Jlabels se añaden a la matriz para no perderlas y luego se meten en la vista
			        pixel[fila][columna] = new JLabel();
			        contentPane.add(pixel[fila][columna]);
			        
			        gridAcciones[fila][columna] = ACCION_NADA;
				}
			}
		
	}
	
	private class Controller implements KeyListener {
		boolean isShootPressed = false;
		boolean isRightPressed = false;
		boolean isLeftPressed = false;
		boolean isDownPressed = false;
		boolean isUpPressed = false;
		boolean isChangingAmmo = false;
		
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			
			switch (keyCode) {
			case KeyEvent.VK_A:
				if(!isLeftPressed) {
					//System.out.println("Tecla pulsada: A");

					isLeftPressed = true;
					Jugador.getJugador().moveLeft();
				}
				break;
			case KeyEvent.VK_D:
				if(!isRightPressed) {
					//System.out.println("Tecla pulsada: D");
					
					isRightPressed = true;
					Jugador.getJugador().moveRight();
				}
				break;
			case KeyEvent.VK_W:
				if(!isUpPressed) {
					//System.out.println("Tecla pulsada: W");
					
					isUpPressed = true;
					Jugador.getJugador().moveUp();
				}
				break;
			case KeyEvent.VK_S:
				if(!isDownPressed) {
					//System.out.println("Tecla pulsada: S");
					
					isDownPressed = true;
					Jugador.getJugador().moveDown();
				}
				break;
			case KeyEvent.VK_ENTER:
				if (!isShootPressed) {
					System.out.println("POW\n");
					
					isShootPressed = true;
					Jugador.getJugador().shoot();
				}
				break;
			
			case KeyEvent.VK_R:
				if (!isChangingAmmo) {
					System.out.println("Recharge\n");
					
					isChangingAmmo = true;
					GeneradorBalas.getGeneradorBalas().cambiarBalas();
				}
				break;
			}					
		}
			
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			
			switch (keyCode) {
			case KeyEvent.VK_A:
				if(isLeftPressed) {
					//System.out.println("Tecla soltada: A");
					
					isLeftPressed = false;
					Jugador.getJugador().moveLeft(false);
				}
				break;
			case KeyEvent.VK_D:
				if(isRightPressed) {
					//System.out.println("Tecla soltada: D");
					
					isRightPressed = false;
					Jugador.getJugador().moveRight(false);
				}
				break;
			case KeyEvent.VK_W:
				if(isUpPressed) {
					//System.out.println("Tecla soltada: W");
					
					isUpPressed = false;
					Jugador.getJugador().moveUp(false);
				}
				break;
			case KeyEvent.VK_S:
				if(isDownPressed) {
					//System.out.println("Tecla soltada: S");
					
					isDownPressed = false;
					Jugador.getJugador().moveDown(false);
				}
				break;
			case KeyEvent.VK_ENTER:
				if (isShootPressed) isShootPressed = false;
				break;					
			case KeyEvent.VK_R:
				if (isChangingAmmo) isChangingAmmo = false;
				break;					
			
		}
	  }	
	}
}
