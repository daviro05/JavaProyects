package tp.pr5.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import javax.swing.SpringLayout;

import tp.pr5.control.Hebra;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.ModoAutomatico;
import tp.pr5.logica.ModoHumano;
import tp.pr5.logica.ModoJuego;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

public class GestionJugador extends JPanel implements Observador{
	
	private ControladorGUI control;
	private Hebra hebra;
	private JLabel jblancas;
	private JLabel jnegras;
	private JComboBox modoblancas;
	private JComboBox modonegras;
	private JPanel pblanca = new JPanel();
	private JPanel pnegra = new JPanel();
	private PanelTablero tab;
	
	public GestionJugador(ControladorGUI control){
		
		this.control = control;
		control.addObservador(this);
		initGUI();
		confEvents();
		
	}
	
	public void initGUI()
	{
		setLayout(new BorderLayout());
		setBorder(javax.swing.BorderFactory.createTitledBorder("Gestión de Jugadores"));
		
		modoblancas = new JComboBox<TipoTurno>(TipoTurno.values());
		modonegras = new JComboBox<TipoTurno>(TipoTurno.values());
		
		jblancas = new JLabel("Jugador de blancas");
		jnegras = new JLabel("Jugador de negras  ");
		
		pblanca.add(jblancas,BorderLayout.WEST);
		pblanca.add(modoblancas,BorderLayout.EAST);
		
		pnegra.add(jnegras,BorderLayout.WEST);
		pnegra.add(modonegras,BorderLayout.EAST);
		
		add(pblanca,BorderLayout.NORTH);
		add(pnegra,BorderLayout.CENTER);
		
	}
	
public void confEvents(){
		
	modoblancas.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			TipoTurno t1=(TipoTurno)modoblancas.getSelectedItem();
			ModoJuego m = new ModoAutomatico(control, hebra);
			ModoJuego m2 = new ModoHumano();
			
			if(t1.equals(TipoTurno.AUTOMATICO)){
				control.parar();
				Ficha.BLANCA.setTipo_turno(TipoTurno.AUTOMATICO);
				Ficha.BLANCA.setModo(m);

			}
			else
			{
				control.parar();
				Ficha.BLANCA.setTipo_turno(TipoTurno.HUMANO);
				Ficha.BLANCA.setModo(m2);
			}
			control.comenzar();
		}
	});
	modonegras.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			TipoTurno t1=(TipoTurno)modonegras.getSelectedItem();
			ModoJuego m = new ModoAutomatico(control, hebra);
			ModoJuego m2 = new ModoHumano();
			
			if(t1.equals(TipoTurno.AUTOMATICO)){
				control.parar();
				Ficha.NEGRA.setTipo_turno(TipoTurno.AUTOMATICO);
				Ficha.NEGRA.setModo(m);

			}
			else
			{
				control.parar();
				Ficha.NEGRA.setTipo_turno(TipoTurno.HUMANO);
				Ficha.NEGRA.setModo(m2);
			}
			control.comenzar();
		}
	});
	
}

@Override
public void onReset(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
	control.parar();
	modoblancas.setSelectedItem(TipoTurno.HUMANO);
	modonegras.setSelectedItem(TipoTurno.HUMANO);
	
}

@Override
public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
	//control.parar();
	
}

@Override
public void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
	modoblancas.setSelectedItem(TipoTurno.HUMANO);
	modonegras.setSelectedItem(TipoTurno.HUMANO);
	
}

@Override
public void onMovStart(Ficha turno, TableroInmutable tablero) {
	
	
}

@Override
public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
	// TODO Auto-generated method stub
	
}

@Override
public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual) {
	//control.comenzar(); hay que mirarlo
	
}



@Override
public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {

}

@Override
public void onPack() {
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
public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
		Ficha turno, boolean[][] posibles, TipoJuego actual) {
	control.parar();
	control.comenzar();
	
}

@Override
public void onNotificaJuego(TipoJuego actual) {
	// TODO Auto-generated method stub
	
}



}
