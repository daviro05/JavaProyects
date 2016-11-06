package tp.pr5.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;


/*
 * En el panel izquierda se encuentra el tablero y el turno del jugador.
 */
public class PanelIzda extends JPanel implements Observador{

	private ControladorGUI control;
	private PanelTablero tablero;
	private JLabel jugador;
	
	public PanelIzda(ControladorGUI control)
	{
		this.control = control;
		control.addObservador(this);
		initGUI();
	}
	
	public void initGUI()
	{
		setLayout(new BorderLayout());
		tablero = new PanelTablero(control);
		jugador = new JLabel();
		control.iniciar();
		
		jugador.setHorizontalAlignment(SwingConstants.CENTER);
		jugador.setBorder(LineBorder.createBlackLineBorder());
		jugador.setFont(new java.awt.Font("Tahoma", 0, 22));
		jugador.setForeground(Color.black);
		jugador.setBackground(Color.white);
		jugador.setOpaque(true);

		add(tablero, BorderLayout.NORTH);
		add(jugador, BorderLayout.CENTER);
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		this.jugador.setText("Juegan las " + turno.toString().toLowerCase()+"s");
		tablero.actualizaTablero(tab);
		this.tablero.setActivo(true);
		this.jugador.setForeground(Color.black);
		if (actual.equals(TipoJuego.Reversi)){
			this.tablero.actualizaPosibles(tab,posibles);
		}
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		this.tablero.actualizaTablero(tablero);
		this.tablero.setActivo(false);
		if (ganador==Ficha.VACIA)
			jugador.setText("Partida terminada en tablas");
		else
			jugador.setText("Ganan las " + ganador.toString().toLowerCase()+"s");
		
		this.jugador.setForeground(Color.green);
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		
		remove(this.tablero);
		this.tablero = new PanelTablero(control);
		this.tablero.initGUI(tablero.getColumnas(),tablero.getFilas());
		this.jugador.setForeground(Color.black);
		this.jugador.setText("Juegan las " + turno.toString().toLowerCase()+"s");
		add(this.tablero, BorderLayout.NORTH);
		revalidate();
		this.tablero.actualizaTablero(tablero);
		if (actual.equals(TipoJuego.Reversi)){
			this.tablero.actualizaPosibles(tablero,posibles);
		}
		
		
		
	}

	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		
	}

	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual) {
		this.tablero.actualizaTablero(tablero);
		this.jugador.setText("Juegan las " + turno.toString().toLowerCase()+"s");
		if (actual.equals(TipoJuego.Reversi)){
			this.tablero.actualizaPosibles(tablero,posibles);
		}
	}


	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		JOptionPane.showMessageDialog(null, movimientoException.getMessage());
		
	}

	public void onPack() {
		
	}

	@Override
	public void onMovStart(Ficha turno,TableroInmutable tablero) {
		TipoTurno t = turno.getTipo_turno();
		if(t.equals(t.HUMANO))
			this.tablero.setActivo(true);
			//this.tablero.habilitarBotones(true, tablero);
		else
			this.tablero.setActivo(false);
			//this.tablero.habilitarBotones(false, tablero);
	}


	public void pintarCasilla(Movimiento[] posible) {
		
		
	}

	@Override
	public void onError(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,Ficha turno, boolean[][] posibles, TipoJuego actual) {
		this.tablero.setActivo(true);
		this.tablero.actualizaTablero(tablero);
		this.jugador.setText("Juegan las " + turno.toString().toLowerCase()+"s");
		if (actual.equals(TipoJuego.Reversi)){
			this.tablero.actualizaPosibles(tablero,posibles);
		}
		
	}

	@Override
	public void onNotificaJuego(TipoJuego actual) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
