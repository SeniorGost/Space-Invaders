package modelo;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class Modelo extends Observable{
	private int ventana;
	private static Modelo miModelo;
	private long contador;
	private Timer miTimer;
	
	private int gridWidth = 60;
	private int gridHeight = 100;
	
	public static Modelo getModelo() {
		if(miModelo == null) miModelo = new Modelo();
		return miModelo;
	}
	//
	public Modelo() {
		miTimer = new Timer();
	}
	
	public void notifyKey(int keyCode) {
		if(keyCode == KeyEvent.VK_ENTER) {
			if (ventana == 0) {				
				cambiarVentana(1);
				empezarJuego();
			}
			if (ventana == 2 || ventana == 3) {
				cambiarVentana(0);
			}
		}
	}
	
	private void empezarJuego() {
		
		Jugador.getJugador().inicializar();
		
		miTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ventana == 1) {
					contador = System.currentTimeMillis();
					
					try {
						Jugador.getJugador().tick();
					} catch (JuegoCambiadoException e) {
						miTimer.cancel();
						
						if (e instanceof JuegoGanadoException) {
							cambiarVentana(2); // 2: Pantalla de ganar
						};
						if (e instanceof JuegoPerdidoException) {
							cambiarVentana(3); // 3: Pantalla de perder
						};
					}
				}
				
			}
		}, 0, 50);
	}
	
	private void cambiarVentana(int pVentana) {
		ventana = pVentana;
		setChanged();
		notifyObservers(new int[] {pVentana});
	}
	
	public int getWidth() {
		return gridWidth;
	}
	
	public int getHeight() {
		return gridHeight;
	}
}