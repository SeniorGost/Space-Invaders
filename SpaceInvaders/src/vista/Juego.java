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

import modelo.BalaJugador;
import modelo.Flota;
// MODELO SE IMPORTA PARA LA LINEA 26 Y PARA EL OBSERVABLE, EL RESTO SOLO PARA EL OBSERVABLE
import modelo.Jugador;
import modelo.Alien;
import modelo.ArtilleriaJugador;
import modelo.Modelo;
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
	private ArrayList<JLabel> pixelesPintados = new ArrayList<JLabel>();
	
	// Aqui guardaremos las instrucciones para luego ejecutarlas todas de un tiron cada tick (El tipo de datos Runnable se usa para guardar comandos)
	private Queue<Runnable> aPintar  = new LinkedList();
	

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
				//System.out.println("J: " + datos[0] + " " + datos[1]);
				moverJugador(datos);
			}
		}
		
		if (o instanceof ArtilleriaJugador)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				//System.out.println("B: " + datos[0] + " " + datos[1]);
				moverBala(datos);
			}
		}
		
		if (o instanceof Flota)
		{
			if (arg instanceof int[])
			{
				
				int[] datos=(int[])arg;
				//System.out.println("M: " + datos[0] + " " + datos[1]);
				moverMarciano(datos);
			}
		}
		
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
	    //AQUI lo que estoy haciendo es meter la instruccion en la pila de instrucciones (El ()-> es para que en lugar de ejecutarse la accion inmediatamente, se guarde la accion, creo, a ver a si lo ponian en StackOverflow y yo confio en esa gente)
	    aPintar.add(() -> pixel[fN][cN].setBackground(Color.RED)); // le da el color  
	    aPintar.add(() -> pixel[fN][cN].setOpaque(true)); // para que se vea el pixel
	    aPintar.add(() -> pixelesPintados.add(pixel[fN][cN])); // añadirá el pixel a la lista de pixeles pintados para que sepamos que se ha pintado
	}
	
	private void moverMarciano (int posicion[] ){
	    //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	    int fN = posicion[1];
	    int cN = posicion[0];
		    
		//el marciano es VERDE
	    aPintar.add(() -> pixel[fN][cN].setBackground(Color.GREEN)); // le da el color  
	    aPintar.add(() ->pixel[fN][cN].setOpaque(true)); // para que se vea el pixel
	    aPintar.add(() -> pixelesPintados.add(pixel[fN][cN])); // añadirá el pixel a la lista de pixeles pintados para que sepamos que se ha pintado
	}
	
	private void moverBala (int posicion[] ){
	   //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	   int fN = posicion[1];
	   int cN = posicion[0];
		    
	   //la bala es BLANCA
	    aPintar.add(() -> pixel[fN][cN].setBackground(Color.WHITE)); // le da el color  
	    aPintar.add(() ->pixel[fN][cN].setOpaque(true)); // para que se vea el pixel
	    aPintar.add(() -> pixelesPintados.add(pixel[fN][cN])); // añadirá el pixel a la lista de pixeles pintados para que sepamos que se ha pintado
	}
	
	//Este es el tema, cada vez que se notifica a los observers, se notifica a todas las pantallas a la vez entonces se tiene que poner una sentencia como esta, donde se distinga el numero de pantalla en que estamos
	private void cambiarPantalla (int pValor) {
		if (pValor!=1)
			this.setVisible(false);
		else
			this.setVisible(true);
	}
	
	private void pintarPantalla() {
		
		//Como es una pila cada instruccion que saquemos se borrará de la cola, osea acabaremos cuando la cola este vacia
		while (!aPintar.isEmpty()) {
			//poll() saca un elemento run() lo ejecuta (Porque es una instruccion)
		    aPintar.poll().run();
		}
	}
	//este método limpia la pantalla
	private void cleanClean(){
		
		for (JLabel pixel : pixelesPintados) {
			pixel.setOpaque(false); // asi no se ve el pixel (si no estuviese esto aqui al quitarles el color se quedarian como pixeles visibles en gris)
			pixel.setBackground(null); // asi se quita el color (si no estuviese esto aqui aun poniendo el Opaque a false se queda el pixel coloreado en Rojo visible)
			//para que el pixel no sea visible tiene que NO ser opaco y NO tener color de fondo
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
		boolean isShootPressed = false;
		boolean isRightPressed = false;
		boolean isLeftPressed = false;
		boolean isDownPressed = false;
		boolean isUpPressed = false;
		
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			
			switch (keyCode) {
			case KeyEvent.VK_A:
				if(!isLeftPressed) {
					System.out.println("Tecla pulsada: A");

					isLeftPressed = true;
					Jugador.getJugador().moveLeft();
				}
				break;
			case KeyEvent.VK_D:
				if(!isRightPressed) {
					System.out.println("Tecla pulsada: D");
					
					isRightPressed = true;
					Jugador.getJugador().moveRight();
				}
				break;
			case KeyEvent.VK_W:
				if(!isUpPressed) {
					System.out.println("Tecla pulsada: W");
					
					isUpPressed = true;
					Jugador.getJugador().moveUp();
				}
				break;
			case KeyEvent.VK_S:
				if(!isDownPressed) {
					System.out.println("Tecla pulsada: S");
					
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
			}
		}
			
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			
			switch (keyCode) {
			case KeyEvent.VK_A:
				if(isLeftPressed) {
					System.out.println("Tecla soltada: A");
					
					isLeftPressed = false;
					Jugador.getJugador().moveLeft(false);
				}
				break;
			case KeyEvent.VK_D:
				if(isRightPressed) {
					System.out.println("Tecla soltada: D");
					
					isRightPressed = false;
					Jugador.getJugador().moveRight(false);
				}
				break;
			case KeyEvent.VK_W:
				if(isUpPressed) {
					System.out.println("Tecla soltada: W");
					
					isUpPressed = false;
					Jugador.getJugador().moveUp(false);
				}
				break;
			case KeyEvent.VK_S:
				if(isDownPressed) {
					System.out.println("Tecla soltada: S");
					
					isDownPressed = false;
					Jugador.getJugador().moveDown(false);
				}
				break;
			case KeyEvent.VK_ENTER:
				if (isShootPressed) isShootPressed = false;
				break;					
			}
			
		}


	}
}
