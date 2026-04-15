	package modelo;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import modelo.excepciones.JuegoCambiadoException;
import modelo.excepciones.JuegoGanadoException;
import modelo.excepciones.JuegoPerdidoException;

@SuppressWarnings("deprecation")
public class Modelo extends Observable implements Observer {
	private int ventana;
	private static final int VENTANA_MENU = 0;
	private static final int VENTANA_MENU2 = 1;
	private static final int VENTANA_JUEGO = 2;
	private static final int VENTANA_GANADO = 3;
	private static final int VENTANA_PERDIDO = 4;

	private static final int GRID_WIDTH = 100;
	private static final int GRID_HEIGHT = 60;
	
	public static final int NOTIFY_WINDOW_CHANGE = 0;
	public static final int NOTIFY_PIXEL_POSITION = 1;

	private static Modelo miModelo;
	private Timer miTimer;

	public static Modelo getModelo() {
		if (miModelo == null)
			miModelo = new Modelo();
		return miModelo;
	}

	//
	public Modelo() {
		//ta vacio, lo chento :c
	}
	
	// (No voy a leer eso)
	
	/**
	 * Siempre llamar este metodo de manera externa (idealmente desde 'Controller'). Cambia la ventana actual del juego
	 * por la siguiente.
	 */
	public void cambiarVentana() {		
		switch (ventana) {
		case VENTANA_MENU:
			cambiarVentana(VENTANA_MENU2);
			break;
		case VENTANA_GANADO:
		case VENTANA_PERDIDO:
			cambiarVentana(VENTANA_MENU);
			break;
		}
	}

	public void empezarJuego(int nave) {

		//0 - green, 1 - blue, 2 - red
		Jugador.getJugador().inicializar(nave);
		
		cambiarVentana(VENTANA_JUEGO);
		
		//esto esta aqui porque si no, cuando se vuelva a lanzar no va a funcionar
		miTimer = new Timer();
		
		miTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ventana == VENTANA_JUEGO) {
					try {
						Jugador.getJugador().tick();
						
						setChanged();
						notifyObservers(-1);

					} catch (JuegoCambiadoException e) {
						miTimer.cancel();

						if (e.getTipo() == JuegoGanadoException.TIPO)
							cambiarVentana(VENTANA_GANADO); // 2: Pantalla de ganar

						if (e.getTipo() == JuegoPerdidoException.TIPO)
							cambiarVentana(VENTANA_PERDIDO); // 3: Pantalla de perder
					}
				}
			}
		}, 0, 50);
	}

	private void cambiarVentana(int pVentana) {
		ventana = pVentana;
		setChanged();
		if(ventana != VENTANA_JUEGO) {
			System.out.print("Cambio:" + pVentana + "\n");
			notifyObservers(new int[] { NOTIFY_WINDOW_CHANGE, pVentana });
		} else {
			System.out.println("Cambio:" + pVentana);
			notifyObservers(new int[] { NOTIFY_WINDOW_CHANGE, pVentana, GRID_WIDTH, GRID_HEIGHT });
		}
	}

	public int getWidth() {
		return GRID_WIDTH;
	}

	public int getHeight() {
		return GRID_HEIGHT;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Pixel) {
			if (!(arg instanceof int[]))
				return;

			int[] data = (int[]) arg;
			
			if (data.length != 3)
				return;
			
			setChanged();
			notifyObservers(new int[] {NOTIFY_PIXEL_POSITION, data[0], data[1], data[2]});
		}
		
	}
}
