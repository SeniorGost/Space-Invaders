package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

//Estas dos son para hacer lo de pillar las dimensiones de la pantalla
import java.awt.Dimension;
import java.awt.Toolkit;

// MODELO SE IMPORTA PARA LA LINEA 26 Y PARA EL OBSERVABLE
import modelo.Modelo;

public class Menu extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(Modelo.getModelo());
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
	public Menu(Observable modelo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//esto es para pillar el tamaño de la pantalla del usuario
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		//Mostramos por pantalla por si las moscas
		System.out.println("Dimensiones de Pantalla PC: " + width + "," + height);
		
		//Hacemos la ventana tan grande como la pantalla (Los castings son porque el dato es double no int)
		//Los dos primeros numeros son la posicion en la que 'spawnea' la ventana, la pongo en el 0,0 porque va ha ser tan grande como la pantalla, luego de estar movida se vería cortada
		setBounds(0, 0, (int)width, (int)height);
		
	    Modelo.getModelo().addObserver(this);
		
		//Se activa el "COCO TACTICO", EXTREMADAMENTE IMPORTANTE: NO TOCAR//
		ImageIcon COCOTACTICO = new ImageIcon(Menu.class.getResource("/coconut.png"));
		
		//esto de aqui hace funcionar la imagen de fondo
	    contentPane = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics componente) {
		         super.paintComponent(componente);
		         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/menu_Background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
		        }
		};
		

		contentPane.addKeyListener(new Controller());
		
		//esto lo que hace es hacer que el Pane empieze a capturar teclas, es como clicar en una barra donde puedes empezar a rellenar
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/title.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		//esto permite que panel_1 no obstruya la imagen
		panel_1.setOpaque(false); 
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Pulsa Cualquier boton para continuar");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(Color.WHITE);
		panel.add(lblNewLabel_1);
		
		//esto permite que panel no obstruya la imagen
		panel.setOpaque(false);
		
		JLabel AlienitoLabel = new JLabel("");
		AlienitoLabel.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/horrores_eldricos.gif")));
		panel.add(AlienitoLabel, BorderLayout.WEST);
		
		JLabel LabelOBNI = new JLabel("");
		LabelOBNI.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/ovnmi.gif")));
		panel.add(LabelOBNI, BorderLayout.EAST);
	}

	@Override
	public void update(Observable o, Object arg) {
	    // 	Metodo de actualización de la vista 
		
		if (o instanceof Modelo)
		{
			if (arg instanceof int[])
			{
				// 0 Menu, 1 Menu2, 2 Juego, 3 Ganar, 4 Perder
				int[] datos=(int[])arg;
				
				if (datos[0] == Modelo.NOTIFY_WINDOW_CHANGE)
					cambiarPantalla(datos[1]);
				
			}
		}
		
	}
	
	//Este es el tema, cada vez que se notifica a los observers, se notifica a todas las pantallas a la vez entonces se tiene que poner una sentencia como esta, donde se distinga el numero de pantalla en que estamos
	private void cambiarPantalla (int pValor){
		
		if (pValor!=0)
		{
			this.setVisible(false);
		}
		else
		{
			this.setVisible(true);
		}
	}
	
	private class Controller implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {			
			Modelo.getModelo().cambiarVentana();
		}
	}
}
