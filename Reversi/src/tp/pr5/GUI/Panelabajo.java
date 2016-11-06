package tp.pr5.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

/*
 * Panel Abajo, es el panel donde podremos poner fichas aleatoriamente mediante un JButton
 * o bien salir del juego.
 */
public class Panelabajo extends JPanel implements Observador{
	
	private JButton aleatorio;
	private JButton salir;
	private ControladorGUI control;
	private boolean activo;
	
	public Panelabajo(ControladorGUI control){
		this.control = control;
		control.addObservador(this);
		initGUI();
		confEvents();
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void confEvents() {
		
		salir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int op_salir = JOptionPane.showConfirmDialog(null,"Desea salir?", "Salir",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
				if (JOptionPane.OK_OPTION == op_salir)
					System.exit(0);
			}
		});
		
		aleatorio.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					if(activo)
						control.aleatorio();
				} catch (MovimientoInvalido e1) {
					onMovimientoIncorrecto(e1);
				}
			}
		});
		
	}

	public void initGUI() {
		setLayout(new BorderLayout());
		aleatorio = new JButton("Poner aleatorio",new ImageIcon(getClass().getResource("imagenes/aleatorio.png")));
		salir = new JButton("Salir",new ImageIcon(getClass().getResource("imagenes/salir.png")));
		add(aleatorio, BorderLayout.WEST);
		add(salir,BorderLayout.EAST);
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		activo=true;
		//aleatorio.setEnabled(true);
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		activo=false;
		//aleatorio.setEnabled(false);
		
	}

	public void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual) {
		
	}


	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		
	}

	@Override
	public void onPack() {
		
	}

	@Override
	public void onMovStart(Ficha turno,TableroInmutable tablero) {
		TipoTurno t = turno.getTipo_turno();
		if(t.equals(t.HUMANO))
			activo=true;
		else
			activo=false;
			//aleatorio.setEnabled(false);
		
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
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno, boolean[][] posibles, TipoJuego actual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNotificaJuego(TipoJuego actual) {
		// TODO Auto-generated method stub
		
	}

}
