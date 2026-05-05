package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Modelo;

import java.awt.FlowLayout;
import javax.swing.JButton;

//Ahora extendemos Jpanel en lugar de Jframe (Todo esto es un panel de MyFrame)
public class MenuREAL extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuREAL frame = new MenuREAL();
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
	public MenuREAL() {
		Modelo.getModelo().addObserver(this);
		
		setBounds(100, 100, 450, 300);
		
		//esto lo que hace es hacer que el Pane empieze a capturar teclas, es como clicar en una barra donde puedes empezar a rellenar
		setFocusable(true);
		requestFocusInWindow();
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/title.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		//esto permite que panel_1 no obstruya la imagen
		panel_1.setOpaque(false); 
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		//esto permite que panel no obstruya la imagen
		panel.setOpaque(false); 
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setOpaque(false); 
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(9, 0, 0, 0));
		panel_3.setOpaque(false); 
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel_3.add(lblNewLabel_5);
		lblNewLabel_5.setOpaque(false); 
		
		JButton btnNewButton_3 = new JButton("Nueva Partida");
		btnNewButton_3.setName("NuevaPartida");
		btnNewButton_3.setBackground(Color.ORANGE);
		btnNewButton_3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.addMouseListener(new Controller());
		panel_3.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel_3.add(lblNewLabel_4);
		lblNewLabel_4.setOpaque(false); 
		
		JButton btnNewButton_2 = new JButton("Nave");
		btnNewButton_2.setName("Nave");
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addMouseListener(new Controller());
		panel_3.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setOpaque(false); 
		
		JButton btnNewButton_1 = new JButton("Tamaño");
		btnNewButton_1.setName("Tamano");
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.addMouseListener(new Controller());
		panel_3.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setOpaque(false); 
		
		JButton btnNewButton = new JButton("Créditos");
		btnNewButton.setName("Creditos");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Papyrus", Font.BOLD, 15));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addMouseListener(new Controller());
		panel_3.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setOpaque(false); 
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setOpaque(false); 
	}
	public void update(Observable o, Object arg) {
	    // 	Metodo de actualización de la vista 
		
		if (o instanceof Modelo)
		{
			if (arg instanceof int[])
			{
				// 0 Menu, 5 MenuREAL, 1 Menu2, 2 Juego, 3 Ganar, 4 Perder, 6 Menu3, 7 Creditos 
				int[] datos=(int[])arg;
				
				if (datos[0] == Modelo.NOTIFY_WINDOW_CHANGE)
					cambiarPantalla(datos[1]);
				
			}
		}
		
	}
	//Este es el tema, cada vez que se notifica a los observers, se notifica a todas las pantallas a la vez entonces se tiene que poner una sentencia como esta, donde se distinga el numero de pantalla en que estamos
	private void cambiarPantalla (int pValor){
		if(pValor == 5)
			MyFrame.getMyFrame().cambiarVentana(pValor);
	}
	
	private class Controller implements MouseListener {
		//Este es el evento de clicar en el boton
		@Override
		public void mouseClicked(MouseEvent e) {
			//Gracias Aimar por descubrir el atributo nombre
			String keyCode = ((JButton) e.getSource()).getName();

			//ESTO ES INFORMACION DE LA VISTA PORQUE ES QUE BOTON SE HA PULSADO
			switch (keyCode) {
			case "NuevaPartida":
				Modelo.getModelo().empezarJuego();
				break;
			case "Nave":
				Modelo.getModelo().cambiarVentana(1);
				System.out.print("AAAAAA");
				break;
			case "Tamano":
				Modelo.getModelo().cambiarVentana(6);
				break;
			case "Creditos":
				Modelo.getModelo().cambiarVentana(7);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

	}
	
	//Para poner el fondo, este era el codigo antes, que a demas estaba en constructora:
	/*
		contentPane = new JPanel() {
		 	@Override
		 	protected void paintComponent(java.awt.Graphics componente) {
		       super.paintComponent(componente);
		       componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
		   	}
		};
	*/
	//Ahora esto lo ponemos en un metodo en lugar de ponerlo como antes porque el Jpane esta guardado en myFrame
	@Override
    protected void paintComponent(java.awt.Graphics componente) {
         super.paintComponent(componente);
         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/menu_Background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
    }
}
