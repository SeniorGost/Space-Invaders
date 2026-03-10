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
	private JLabel[][] pixel= new JLabel[60][100];
	
	/* //forma alt para matriz de cualquier tamaño (commentada ahora para hacer las pruebas)
	   private JLabel[][] pixel;
	   private int filas;
	   private int columnas;
	 */

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
		            int[] objeto = new int[]{10,20};
		            cleanClean();
		            moverJugador(objeto);
		        }
		        else if (e.getKeyCode() == 68 ) { 
		            System.out.println("Se ha pulsado B");
		            int[] objeto = new int[]{11,20};
		            cleanClean();
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
		
		/*//en el caso de matriz de cualquier tamaño (comentado para hacer las pruebas)
		 
		for (int f = 0; f < this.filas; f++) {
			for(int c = 0; c < this.columnas; c++){
				//Todas las Jlabels se añaden a la matriz para no perderlas y luego se meten en la vista
		        pixel[f][c] = new JLabel();
		        contentPane.add(pixel[f][c]);
			}
		}
		
		*/
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
		
		// para hacer lo de matriz de cualquier tamaño voy a necesitar que me envieis el tamaño por aqui
		if (o instanceof Modelo)
		{
			if (arg instanceof int[])
			{
				int[] datos=(int[])arg;
				if(datos[0]==1){
					
					//esto le dice que si se pasa a la pantalla 1, que primero defina la matriz con las dimensiones que me pasa modelo
					//definirMatriz(datos[1],datos[2]);
					
				}
				cambiarPantalla(datos[0]);
			}
		}
		
	}
	
	private void moverJugador (int posicion[] ){
    //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	    int fN = posicion[0];
	    int cN = posicion[1];
	    
	    //el jugador es ROJO
	    pixel[fN][cN].setBackground(Color.RED);  // le da el color
        pixel[fN][cN].setOpaque(true); // para que se vea el pixel
	}
	
	private void moverMarciano (int posicion[] ){
	    //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	    int fN = posicion[0];
	    int cN = posicion[1];
		    
		//el marciano es VERDE
		pixel[fN][cN].setBackground(Color.GREEN); //le da el color
		pixel[fN][cN].setOpaque(true); // para que se vea el pixel
	}
	
	private void moverBala (int posicion[] ){
	   //No se envia anterior y actual, se envia solo actual porque se borra pantalla antes (N es nuevo)  
	   int fN = posicion[0];
	   int cN = posicion[1];
		    
	   //la bala es BLANCA
	   pixel[fN][cN].setBackground(Color.WHITE); //le da el color
	   pixel[fN][cN].setOpaque(true); // para que se vea el pixel	
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
		
		for (int f = 0; f < 60; f++) {
			for(int c = 0; c < 100; c++){
				
				pixel[f][c].setOpaque(false); // asi no se ve el pixel (si no estuviese esto aqui al quitarles el color se quedarian como pixeles visibles en gris)
				pixel[f][c].setBackground(null); // asi se quita el color (si no estuviese esto aqui aun poniendo el Opaque a false se queda el pixel coloreado en Rojo visible)
				//para que el pixel no sea visible tiene que NO ser opaco y NO tener color de fondo
			}
		}
		
		/*//en el caso de matriz de cualquier tamaño (comentado para hacer las pruebas)
		 
		for (int f = 0; f < this.filas; f++) {
			for(int c = 0; c < this.columnas; c++){
                pixel[f][c].setOpaque(false); // asi no se ve el pixel
				pixel[f][c].setBackground(null); // asi se quita el color
			}
		}
		
		*/
		
	}
	
	private void definirMatriz( int f, int c) {
		 
		 pixel= new JLabel[f][c];
		 //filas = f;
		 //columnas = c;
		 
	}

}
