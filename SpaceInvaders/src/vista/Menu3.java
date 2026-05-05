package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Modelo;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Menu3 extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private JTextField AlturaField;
	private JTextField AchuraField;
	private JLabel mensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu3 frame = new Menu3();
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
	public Menu3() {
		Modelo.getModelo().addObserver(this);
		
		setBounds(100, 100, 450, 300);
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel Titulo = new JLabel("");
		Titulo.setOpaque(false); 
		Titulo.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/size-text.png")));
		panel_1.add(Titulo);
		panel_1.setOpaque(false); 
		
		JPanel panel = new JPanel();
		panel.setOpaque(false); 
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		mensaje = new JLabel("Escribe El Tamaño que deseas para el espacio:");
		mensaje.setHorizontalAlignment(SwingConstants.LEFT);
		mensaje.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
		mensaje.setForeground(Color.WHITE);
		panel.add(mensaje, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		panel_2.setOpaque(false); 
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(5, 0, 0, 0));
		panel_3.setOpaque(false); 
		
		JLabel lblNewLabel_3 = new JLabel("Anchura:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setOpaque(false); 
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Altura:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setOpaque(false); 
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("");
		panel_3.add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_3.add(lblNewLabel_2);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(5, 0, 0, 0));
		panel_4.setOpaque(false); 
		
		AchuraField = new JTextField();
		AchuraField.setHorizontalAlignment(SwingConstants.CENTER);
		AchuraField.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 25));
		panel_4.add(AchuraField);
		AchuraField.setColumns(10);
		
		AlturaField = new JTextField();
		AlturaField.setHorizontalAlignment(SwingConstants.CENTER);
		AlturaField.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 25));
		panel_4.add(AlturaField);
		AlturaField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_4.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		btnNewButton.addMouseListener(new Controller());
		panel_4.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		panel_4.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(5, 0, 0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("Minimo 100");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblNewLabel_7.setOpaque(false); 
		panel_5.add(lblNewLabel_7);
		panel_5.setOpaque(false); 
		
		JLabel lblNewLabel_8 = new JLabel("Minimo 60");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblNewLabel_8.setOpaque(false); 
		panel_5.add(lblNewLabel_8);
		//esto de aqui hace funcionar la imagen de fondo;

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
		if(pValor == 6)
			MyFrame.getMyFrame().cambiarVentana(pValor);
	}
	
	@Override
    protected void paintComponent(java.awt.Graphics componente) {
         super.paintComponent(componente);
         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/menu_Background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
    }
	
	private class Controller implements MouseListener {
		//Este es el evento de clicar en el boton
		@Override
		public void mouseClicked(MouseEvent e) {
			//No puedes hacer casting directo de string a int tal que (int) var-string; hay que usar un parse int
			if(!(AlturaField.getText().isEmpty()||AchuraField.getText().isEmpty())) {
				int altura = Integer.parseInt(AlturaField.getText());
				int anchura = Integer.parseInt(AchuraField.getText());
				if(!(altura < 60 || anchura < 100)){
					Modelo.getModelo().nuevaAltura(altura);
					System.out.print("nueva altura: " + altura);
					Modelo.getModelo().nuevaAchura(anchura);
					System.out.print("nueva anchura: " + anchura);
					Modelo.getModelo().cambiarVentana();
					System.out.print("vuelta Menu");
				}
				else{
					mensaje.setText("Zopenco, leete los limites");
					mensaje.setForeground(Color.RED);
				}
			}
			else {
				Modelo.getModelo().cambiarVentana();
				System.out.print("vuelta Menu");
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
