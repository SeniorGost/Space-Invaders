package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.RenderingHints.Key;

import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel gridPanel;
	private JLabel icono;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
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
	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getGridPanel());
		contentPane.add(getIcono());
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				icono.setLocation(icono.getX(), icono.getY()-10);
			}
		});
		getGridPanel().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.equals(KeyEvent.VK_H)) {
					getGridPanel().setVisible(false);
				}
			}
		});
	}
	private JPanel getGridPanel() {
		if (gridPanel == null) {
			gridPanel = new JPanel();
			gridPanel.setBounds(0, 0, 434, 125);
			gridPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			gridPanel.add(new JButton());
			gridPanel.add(getBtnNewButton());
			gridPanel.add(new JButton());
			gridPanel.add(new JButton());
		}
		
		return gridPanel;
	}
	private JLabel getIcono() {
		if (icono == null) {
			icono = new JLabel();
			icono.setBackground(Color.BLUE);
			icono.setOpaque(true);
			icono.setBounds(175, 196, 46, 14);
		}
		return icono;
	}
	
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
		}
		return btnNewButton;
	}
}
