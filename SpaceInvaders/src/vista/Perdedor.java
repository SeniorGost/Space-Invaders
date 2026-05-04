package vista;

import java.awt.BorderLayout;
import java.awt.Color; // Importado para el color de la fuente
//Estas dos son para hacer lo de pillar las dimensiones de la pantalla
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font; // Importado para la fuente
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Modelo;

public class Perdedor extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JLabel labelPuntuacion; // Etiqueta para mostrar los puntos finales

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perdedor frame = new Perdedor();
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
	public Perdedor() {
		//esto es para pillar el tamaño de la pantalla del usuario
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		//le damos a la ventana las dimensiones de la pantalla de usuario
		setBounds(0, 0, (int)width, (int)height);

		Modelo.getModelo().addObserver(this);

		//Keylistener
		addKeyListener(new Controller());

		//esto lo que hace es hacer que el Pane empieze a capturar teclas, es como clicar en una barra donde puedes empezar a rellenar
		setFocusable(true);
		requestFocusInWindow();

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(2, 0, 0, 0));

		JPanel upper_panel = new JPanel();
		add(upper_panel);
		upper_panel.setLayout(new BorderLayout(0, 0));
		//esto permite que panel_1 no obstruya la imagen
		upper_panel.setOpaque(false); 

		JLabel Title = new JLabel("");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/loser-text.png")));
		upper_panel.add(Title, BorderLayout.CENTER);
		
		// Añadimos el JLabel de la puntuación en la parte inferior del panel superior
		labelPuntuacion = new JLabel("PUNTUACIÓN FINAL: 0");
		labelPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntuacion.setFont(new Font("Arial", Font.BOLD, 45));
		labelPuntuacion.setForeground(Color.WHITE);
		upper_panel.add(labelPuntuacion, BorderLayout.SOUTH);
		JPanel lower_panel = new JPanel();
		add(lower_panel);
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
			// Interceptamos el String de los puntos para actualizar la etiqueta
			if (arg instanceof String) {
				String mensaje = (String) arg;
				if (mensaje.startsWith("PUNTOS:")) {
					labelPuntuacion.setText("PUNTUACIÓN FINAL: " + mensaje.split(":")[1]);
				}
			}
			else if (arg instanceof int[])
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
		if(pValor == 4)
			MyFrame.getMyFrame().cambiarVentana(pValor);
	}
	
	private class Controller implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {
			//Probablemente el codigo mas complejo que he escrito para hacer tan poco
			if(((Integer) e.getKeyCode()).equals(KeyEvent.VK_SPACE)) {
				Modelo.getModelo().cambiarVentana();
			}
		}
	}
	
	@Override
    protected void paintComponent(java.awt.Graphics componente) {
         super.paintComponent(componente);
         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/lose.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
    }
}
