package tp.pr5.GUI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/*
 * La clase VistaConsola controla todas las salidas del modo
 * Visual de nuestro juego.
 */
public class VistaGUI extends JFrame implements Observador{
	
	private ControladorGUI control;
	
	private Container panelPrincipal;
	//Panel de Opciones (Aleatorio y Salir)
	private Panelabajo opciones;
	// Panel de Tablero
	private PanelIzda contenedor_tab;
	// Panel Partida
	private PanelDcha contenedor_partida;


	public VistaGUI(ControladorGUI control)
	{
		super("Practica5 - TP");
		this.control = control;
		control.addObservador(this);
		initGUI();
		//Icono de la parte izquierda superior
		this.setIconImage(new ImageIcon(getClass().getResource("imagenes/casillan.png")).getImage());
		pack();
	}
	
	public void initGUI() {
		
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout (new BorderLayout());
		
		//Panel de opciones, Aleatorio y salir.
		
		opciones = new Panelabajo(this.control);
		panelPrincipal.add(opciones, BorderLayout.SOUTH);
		
		//Panel que contiene el tablero y la etiqueta del turno del jugador.
		
		contenedor_tab = new PanelIzda(control);
		panelPrincipal.add(contenedor_tab,BorderLayout.WEST);
		
		//Panel derecho de Opciones de la Partida, contiene Partida y Cambio Juego.
		
		contenedor_partida = new PanelDcha(control);		
		panelPrincipal.add(contenedor_partida,BorderLayout.EAST);
		
		this.setVisible(true);
		this.setLocation(400,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600, 450);
		
	}


	public void onReset(TableroInmutable tab, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		
	}


	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
	}


	public void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		
	}


	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		
	}


	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual) {
		
	}



	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		
	}

	public void onPack() {
		pack();
	}

	@Override
	public void onMovStart(Ficha turno,TableroInmutable tablero) {
		
	}

	public void pintarcasilla(int[][] posible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pintarCasilla(Movimiento[] posible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,Ficha turno, boolean[][] posibles, TipoJuego actual) {
		
	}

	@Override
	public void onNotificaJuego(TipoJuego actual) {
		
		
	}
}
