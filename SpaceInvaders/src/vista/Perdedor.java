package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import modelo.Modelo;

//Estas dos son para hacer lo de pillar las dimensiones de la pantalla
import java.awt.Dimension;
import java.awt.Toolkit;

public class Perdedor extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perdedor frame = new Perdedor(Modelo.getModelo());
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
	public Perdedor(Observable modelo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//esto es para pillar el tamaño de la pantalla del usuario
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		//le damos a la ventana las dimensiones de la pantalla de usuario
		setBounds(0, 0, (int)width, (int)height);

		Modelo.getModelo().addObserver(this);
		
		//fondo EPICO
		contentPane = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics componente) {
		         super.paintComponent(componente);
		         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/lose.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
		    }
		};
		
		//Keylistener
		contentPane.addKeyListener(new Controller());
		
		//esto lo que hace es hacer que el Pane empieze a capturar teclas, es como clicar en una barra donde puedes empezar a rellenar
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel upper_panel = new JPanel();
		contentPane.add(upper_panel);
		upper_panel.setLayout(new BorderLayout(0, 0));
		//esto permite que panel_1 no obstruya la imagen
		upper_panel.setOpaque(false); 
		
		JLabel Title = new JLabel("");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/loser-text.png")));
		upper_panel.add(Title, BorderLayout.CENTER);
		
		JPanel lower_panel = new JPanel();
		contentPane.add(lower_panel);
		lower_panel.setLayout(new GridLayout(0, 3, 0, 0));
		//esto permite que panel_1 no obstruya la imagen
		lower_panel.setOpaque(false); 
		
		JLabel Sad_label = new JLabel("");
		Sad_label.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/duro2.png")));
		lower_panel.add(Sad_label);
		
		JLabel Spongebob_label = new JLabel("");
		Spongebob_label.setHorizontalAlignment(SwingConstants.CENTER);
		Spongebob_label.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/duro.png")));
		lower_panel.add(Spongebob_label);
		
		JLabel Alienitos_label = new JLabel("");
		Alienitos_label.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/duro3.png")));
		lower_panel.add(Alienitos_label);

	}
	
	@Override
	public void update(Observable o, Object arg) {
	    // 	Metodo de actualización de la vista 
		
		if (o instanceof Modelo)
		{
			if (arg instanceof int[])
			{
				// 0 Menu, 1 Juego, 2 Ganar, 3 Perder
				int[] datos=(int[])arg;
				cambiarPantalla(datos[0]);
				
			}
		}
		
	}
	
	//Este es el tema, cada vez que se notifica a los observers, se notifica a todas las pantallas a la vez entonces se tiene que poner una sentencia como esta, donde se distinga el numero de pantalla en que estamos
	private void cambiarPantalla (int pValor){
		
		if (pValor!=3)
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
		public void keyPressed(KeyEvent e) {
			Modelo.getModelo().cambiarVentana();
		}
		@Override
		public void keyReleased(KeyEvent e) {}
	}
}


