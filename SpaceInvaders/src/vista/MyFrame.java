package vista;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import modelo.Modelo;

public class MyFrame extends JFrame {
	private Controller controller;
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
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
	public MyFrame() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Modelo.getModelo();
		this.addKeyListener(getController());

	}
	
	private Controller getController() {
		if(controller == null) controller = new Controller();
		return controller;
	}
	
	private class Controller implements KeyListener {
		private Controller() {}
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			Modelo.getModelo().notifyKey(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}

}
