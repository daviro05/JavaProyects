package tp.pr5.GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoTurno;


/*
 * Panel donde situaremos los panees de cambio de juego y pedir.
 */
public class PanelDcha extends JPanel{

	private ControladorGUI control;
	private Op_Partida op_partida;
	private CambioJuego cambio_juego;
	//private GestionJugador gestionj;
	
	public PanelDcha(ControladorGUI control)
	{	this.control = control;
		initGUI();	
	}
	
	public void initGUI()
	{
		setLayout(new BorderLayout());
		
		op_partida = new Op_Partida(control);
		add(op_partida,BorderLayout.NORTH);
		
		cambio_juego = new CambioJuego(control);
		add(cambio_juego,BorderLayout.CENTER);
	}

}
