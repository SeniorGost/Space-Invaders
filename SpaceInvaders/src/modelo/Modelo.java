package modelo;

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
	private static final int VENTANA_MENU_REAL = 5;
	private static final int VENTANA_MENU3 = 6;
	private static final int VENTANA_CREDITOS = 7;

	//Guarda las dimensiones de la pantalla (Se usa para varios calculos en muchos sitios, cuidado con esto)
	private int GRID_WIDTH = 100;
	private int GRID_HEIGHT = 60;
	
	//Guarda la nave seleccionada por el usuario (Se usa en empezarJuego)
	//Por Defecto, Shreck
	private int nave;
	
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
		case VENTANA_MENU2:
		case VENTANA_MENU3:
		case VENTANA_CREDITOS:
			cambiarVentana(VENTANA_MENU_REAL);
			break;
		case VENTANA_GANADO:
		case VENTANA_PERDIDO:
			cambiarVentana(VENTANA_MENU);
			break;
		}
	}
	
	public void cambiarNave(int nave) {
			//0 - green, 1 - blue, 2 - red
			this.nave = nave;
	}
	
	public void empezarJuego() {

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
						acabarPartida(e.getTipo());
					}
				}
			}
		}, 0, 50);
	}
	public void nuevaAltura(int altura) {
		//0 - green, 1 - blue, 2 - red
		this.GRID_HEIGHT = altura;
	}
	public void nuevaAchura(int anchura) {
		//0 - green, 1 - blue, 2 - red
		this.GRID_WIDTH = anchura;
	}

	/**
	 * 
	 * @param tipo - El {@code 'TIPO'} de la excepcion que a causado el final de la partida.
	 * @see JuegoGanadoException
	 * @see JuegoPerdidoException
	 */
	public void acabarPartida(int tipo) {
		miTimer.cancel();

		if (tipo == JuegoGanadoException.TIPO)
			cambiarVentana(VENTANA_GANADO); // 2: Pantalla de ganar

		if (tipo == JuegoPerdidoException.TIPO)
			cambiarVentana(VENTANA_PERDIDO); // 3: Pantalla de perder
	}
	
	//Aqui va a tener que ser publico porque MenuREAL le tiene que decir a que ventana se cambia o si se empieza el juego segun que boton se ha pulsado
	public void cambiarVentana(int pVentana) {
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

	public void notificarPuntos(int puntos) {
		setChanged();
		notifyObservers("PUNTOS:" + puntos);
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
