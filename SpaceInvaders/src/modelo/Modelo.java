package modelo;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import vista.GameFrame;
@SuppressWarnings("deprecation")
public class Modelo extends Observable{
	private int ventana;
	private static Modelo miModelo;
	private long contador;
	private Timer miTimer;
	
	public static Modelo getModelo() {
		if(miModelo == null) miModelo = new Modelo();
		return miModelo;
	}
	//
	public Modelo() {
		miTimer = new Timer();
		
		Jugador.inicializar();
		Flota.inicializar();
		ArtilleriaJugador.iniciar();
	}
	
	public void notifyKey(int keyCode) {
		if(keyCode == KeyEvent.VK_ENTER && ventana == 0) {
			cambiarVentana();
			miTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					contador = System.currentTimeMillis();
					Jugador.getJugador().tick(contador);
					
				}
			}, 0, 50);
		}
	}

	public void cambiarVentana(){
		ventana = 1;
	}
}