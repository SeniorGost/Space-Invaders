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

public class Ganador extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JLabel labelPuntuacion; // Etiqueta para mostrar los puntos finales


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ganador frame = new Ganador();
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
	public Ganador() {
		//Ya no es necesario adaptar aqui a las dimensiones de la pantalla de usuario porque ya se hace en MyFrame
		Modelo.getModelo().addObserver(this);
		
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
		Title.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/winner-text.png")));
		upper_panel.add(Title, BorderLayout.CENTER);
		
		// Añadimos el JLabel de la puntuación en la parte inferior del panel superior
		labelPuntuacion = new JLabel("PUNTUACIÓN FINAL: 0");
		labelPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntuacion.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
		labelPuntuacion.setForeground(Color.WHITE);
		upper_panel.add(labelPuntuacion, BorderLayout.SOUTH);
		
		JPanel lower_panel = new JPanel();
		add(lower_panel);
		lower_panel.setLayout(new GridLayout(0, 3, 0, 0));
		//esto permite que panel_1 no obstruya la imagen
		lower_panel.setOpaque(false); 
		
		JLabel BlowMe_label = new JLabel("");
		BlowMe_label.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/happy2.png")));
		lower_panel.add(BlowMe_label);
		
		JLabel Haru_Urara_label = new JLabel("");
		Haru_Urara_label.setHorizontalAlignment(SwingConstants.CENTER);
		Haru_Urara_label.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/happy.png")));
		lower_panel.add(Haru_Urara_label);
		
		JLabel Cat_label = new JLabel("");
		Cat_label.setHorizontalAlignment(SwingConstants.RIGHT);
		Cat_label.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/happy6.png")));
		lower_panel.add(Cat_label);
		
		setBorder(new EmptyBorder(5, 5, 5, 5));

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
		if(pValor == 3)
			MyFrame.getMyFrame().cambiarVentana(3);
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
         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/win.png")).getImage(),0, 0, getWidth(), getHeight(), this);
    }
}
