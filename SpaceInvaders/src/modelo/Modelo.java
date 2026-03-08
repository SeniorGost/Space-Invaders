package modelo;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class Modelo extends Observable {
	private int ventana;
	private static final int VENTANA_MENU = 0;
	private static final int VENTANA_JUEGO = 1;
	private static final int VENTANA_GANADO = 2;
	private static final int VENTANA_PERDIDO = 3;

	private static final int GRID_WIDTH = 100;
	private static final int GRID_HEIGHT = 60;

	private static Modelo miModelo;
	private long contador;
	private Timer miTimer;

	public static Modelo getModelo() {
		if (miModelo == null)
			miModelo = new Modelo();
		return miModelo;
	}

	//
	public Modelo() {
		miTimer = new Timer();
	}

	public void notifyKey(int keyCode) {
		if (keyCode == KeyEvent.VK_ENTER) {
			switch (ventana) {
			case VENTANA_MENU:
				cambiarVentana(VENTANA_JUEGO);
				empezarJuego();
				break;

			case VENTANA_GANADO:
			case VENTANA_PERDIDO:
				cambiarVentana(VENTANA_MENU);
				break;
			}
		}
	}

	private void empezarJuego() {

		Jugador.getJugador().inicializar();

		miTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ventana == VENTANA_JUEGO) {
					contador = System.currentTimeMillis();

					try {
						Jugador.getJugador().tick();	

					} catch (JuegoCambiadoException e) {
						miTimer.cancel();

						if (e instanceof JuegoGanadoException)
							cambiarVentana(VENTANA_GANADO); // 2: Pantalla de ganar

						if (e instanceof JuegoPerdidoException)
							cambiarVentana(VENTANA_PERDIDO); // 3: Pantalla de perder
					}
				}
			}
		}, 0, 50);
	}

	private void cambiarVentana(int pVentana) {
		ventana = pVentana;
		setChanged();
		notifyObservers(new int[] { pVentana });
	}

	public int getWidth() {
		return GRID_WIDTH;
	}

	public int getHeight() {
		return GRID_HEIGHT;
	}
}