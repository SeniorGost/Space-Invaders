package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Modelo;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Creditos extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Creditos frame = new Creditos();
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
	public Creditos() {
		Modelo.getModelo().addObserver(this);
		 
		setBounds(100, 100, 450, 300);
		   
		addKeyListener(new Controller());
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JLabel Creditos = new JLabel("");
		Creditos.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/creditos.png")));
		Creditos.setHorizontalAlignment(SwingConstants.CENTER);
		Creditos.setOpaque(false); 
		add(Creditos, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false); 
		add(panel, BorderLayout.CENTER);
		
		//Esta guapada de meter html en el Jlabel para que te deje meter varias lineas en labels se la pille a un pavo de Stack Overflow, creditos a este man: https://stackoverflow.com/questions/685521/multiline-text-in-jlabel
		String creditos = "<html>Staff Equipo Valvula<br>Contribuidores Verificados:<br>&emsp;&emsp;Ingeniero de Jpanels - Gorka Hernández Martín<br>&emsp;&emsp;Nacionalista Albano - Ivan Andres Dominguez<br>&emsp;&emsp;Recorta pngs - Pablo Fernández González<br>Contribuidor Honorifico:<br>&emsp;&emsp;Casi Contribuidor - Aimar Carvajal Valle<br><br>Agradecimientos<br>&emsp;A Ander por hacernos bulling con que metiesemos el espacio<br>&emsp;A Shreck por aceptar ser parte de este proyecto<br>&emsp;A Gabe Newell por salvar el mercado de los videoguegos para ordenador de las garras de la malvada Microsoft varias veces<br><br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Copyleft 2026-2077 Equipo Valvula</html>";
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel text = new JLabel(creditos);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		text.setOpaque(false); 
		panel.add(text);
		
		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(Menu.class.getResource("/spritesEpicos/logo.png")));
		logo.setOpaque(false); 
		panel.add(logo, BorderLayout.SOUTH);

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
		if(pValor == 7)
			MyFrame.getMyFrame().cambiarVentana(pValor);
	}
	
	private class Controller implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {			
			Modelo.getModelo().cambiarVentana();
			System.out.print("vuelta Menu");
		}
	}
	@Override
    protected void paintComponent(java.awt.Graphics componente) {
         super.paintComponent(componente);
         componente.drawImage(new ImageIcon(Menu.class.getResource("/spritesEpicos/menu_Background.jpg")).getImage(),0, 0, getWidth(), getHeight(), this);
    }
}
