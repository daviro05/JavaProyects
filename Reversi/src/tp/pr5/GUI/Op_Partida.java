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
 * OpPartida es el panel donde se podrá deshacer la partida 
 * o bien reiniciarla.
 */
public class Op_Partida extends JPanel implements Observador{
	
	private ControladorGUI control;
	private JButton deshacer;
	private JButton reiniciar;
	private boolean activo;
	
	public Op_Partida(ControladorGUI control){
		this.control = control;
		control.addObservador(this);
		activo=true;
		initGUI();
		confEvents();
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void initGUI()
	{
		setLayout(new BorderLayout());
		
		setBorder(javax.swing.BorderFactory.createTitledBorder("Partida"));
		
		deshacer = new JButton("Deshacer",new ImageIcon(getClass().getResource("imagenes/deshacer.png")));
		reiniciar = new JButton("Reiniciar",new ImageIcon(getClass().getResource("imagenes/reiniciar.png")));
		
		add(deshacer,BorderLayout.WEST);
		add(reiniciar,BorderLayout.EAST);
	}
	
	public void confEvents()
	{
		deshacer.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						if(activo)
							control.undo();
						
					}
				});
		reiniciar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				control.reset();
			}
		});
	}

	public void onReset(TableroInmutable tab, Ficha turno,boolean [][]posibles, TipoJuego actual) 
	{
		activo=true;
		//deshacer.setEnabled(true);

	}

	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		activo=false;
		//deshacer.setEnabled(false);
	}

	public void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		
	}

	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) 
	{
		JOptionPane.showMessageDialog(null, "Imposible deshacer");
	}

	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual) 
	{
		
	}


	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {

	}

	public void onPack() {
		
	}

	@Override
	public void onMovStart(Ficha turno,TableroInmutable tablero) {
		TipoTurno t = turno.getTipo_turno();
		if(t.equals(t.HUMANO))
			activo=true;
			//deshacer.setEnabled(true);
		else
			activo=false;
			//deshacer.setEnabled(false);
		
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

		
	}

	@Override
	public void onNotificaJuego(TipoJuego actual) {
		// TODO Auto-generated method stub
		
	}


}
