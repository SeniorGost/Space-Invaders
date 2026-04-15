package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.JButton;
import java.awt.SystemColor;

public class Menu2 extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Este_label_solo_existe_porque_cuadrar_cosas_en_los_panes_es_una_basura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu2 frame = new Menu2(Modelo.getModelo());
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
	public Menu2(Observable modelo) {
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
		
		
		//esto de aqui hace funcionar la imagen de fondo
	    contentPane = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics componente) {
		         super.paintComponent(componente);
		         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/menu_Background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
		        }
		};
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/Chose-Text.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		//esto permite que panel_1 no obstruya la imagen
		panel_1.setOpaque(false); 
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		//esto permite que panel no obstruya la imagen
		panel.setOpaque(false);
		
		JPanel panel_Botones = new JPanel();
		panel.add(panel_Botones, BorderLayout.CENTER);
		panel_Botones.setOpaque(false); 
		panel_Botones.setLayout(new GridLayout(7, 3, 0, 0));
		
		JLabel empty_1 = new JLabel("");
		panel_Botones.add(empty_1);
		
		JLabel empty_8 = new JLabel("");
		empty_8.setForeground(Color.RED);
		panel_Botones.add(empty_8);
		
		JLabel empty_12 = new JLabel("");
		empty_12.setForeground(Color.RED);
		panel_Botones.add(empty_12);
		
		JLabel empty_2 = new JLabel("");
		panel_Botones.add(empty_2);
		
		JButton btnRed = new JButton("READY PLAYER RED");
		btnRed.setName("btnRed");
		btnRed.setForeground(Color.RED);
		btnRed.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		btnRed.addMouseListener(new Controller());
		panel_Botones.add(btnRed);
		
		//SIENTO MUCHO HABER PUESTO TANTO BOTON VACIO, SI NO NO QUEDA BONITO UnU
		
		JLabel empty_13 = new JLabel("");
		empty_13.setForeground(Color.RED);
		panel_Botones.add(empty_13);
		
		JLabel empty_3 = new JLabel("");
		empty_3.setForeground(Color.RED);
		panel_Botones.add(empty_3);
		
		JLabel empty_9 = new JLabel("");
		panel_Botones.add(empty_9);
		
		JLabel empty_14 = new JLabel("");
		panel_Botones.add(empty_14);
		
		JLabel empty_4 = new JLabel("");
		empty_4.setForeground(Color.RED);
		panel_Botones.add(empty_4);
		
		JButton btnBlue = new JButton("READY PLAYER BLUE");
		btnBlue.setName("btnBlue");
		btnBlue.setForeground(Color.BLUE);
		btnBlue.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		btnBlue.addMouseListener(new Controller());
		panel_Botones.add(btnBlue);
		
		JLabel empty_15 = new JLabel("");
		panel_Botones.add(empty_15);
		
		JLabel empty_5 = new JLabel("");
		panel_Botones.add(empty_5);
		
		JLabel empty_10 = new JLabel("");
		panel_Botones.add(empty_10);
		
		JLabel empty_16 = new JLabel("");
		panel_Botones.add(empty_16);
		
		JLabel empty_6 = new JLabel("");
		panel_Botones.add(empty_6);
		
		JButton btnGreen = new JButton("READY PLAYER GREEN");
		btnGreen.setName("btnGreen");
		btnGreen.setForeground(Color.GREEN);
		btnGreen.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		btnGreen.addMouseListener(new Controller());
		panel_Botones.add(btnGreen);
		
		JLabel empty_17 = new JLabel("");
		panel_Botones.add(empty_17);
		
		JLabel empty_7 = new JLabel("");
		panel_Botones.add(empty_7);
		
		JLabel empty_11 = new JLabel("");
		panel_Botones.add(empty_11);
		
		JLabel empty_18 = new JLabel("");
		panel_Botones.add(empty_18);
		
		JPanel panel_Ships = new JPanel();
		panel.add(panel_Ships, BorderLayout.WEST);
		panel_Ships.setOpaque(false);
		panel_Ships.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel LabelRedShip = new JLabel("");
		LabelRedShip.setHorizontalAlignment(SwingConstants.LEFT);
		LabelRedShip.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/red.png")));
		panel_Ships.add(LabelRedShip);
		
		JLabel LabelGreenShip = new JLabel("");
		LabelGreenShip.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelGreenShip.setIcon(new ImageIcon("C:\\ECLIPSE_JAVA\\aun mejores bases de datos\\SpaceInvaders\\src\\spritesEpicos\\green.png"));
		panel_Ships.add(LabelGreenShip);
		
		JPanel panel_Ships2 = new JPanel();
		panel_Ships2.setOpaque(false);
		panel.add(panel_Ships2, BorderLayout.EAST);
		panel_Ships2.setLayout(new GridLayout(0, 3, 0, 0));
		
		Este_label_solo_existe_porque_cuadrar_cosas_en_los_panes_es_una_basura = new JLabel("");
		panel_Ships2.add(Este_label_solo_existe_porque_cuadrar_cosas_en_los_panes_es_una_basura);
		
		JLabel LabelBlueShip = new JLabel("");
		LabelBlueShip.setHorizontalAlignment(SwingConstants.CENTER);
		LabelBlueShip.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/blue.png")));
		panel_Ships2.add(LabelBlueShip);
	
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
		
		if (pValor!=1)
		{
			this.setVisible(false);
		}
		else
		{
			this.setVisible(true);
		}
	}
	
	private class Controller implements MouseListener {
		//Este es el evento de clicar en el boton
		@Override
		public void mouseClicked(MouseEvent e) {
			//Gracias Aimar por descubrir el atributo nombre
			String keyCode = ((JButton) e.getSource()).getName();
			
			//ESTO ES INFORMACION DE LA VISTA PORQUE ES QUE BOTON SE HA PULSADO
			switch (keyCode) {
			case "btnBlue":
				Modelo.getModelo().empezarJuego(1);
				break;
			case "btnGreen":
				Modelo.getModelo().empezarJuego(0);
				break;
			case "btnRed":
			default:
				Modelo.getModelo().empezarJuego(2);
				break;
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

}
