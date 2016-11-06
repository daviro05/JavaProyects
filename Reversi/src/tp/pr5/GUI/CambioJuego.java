package tp.pr5.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/*
 * Clase CambioJuego en la que se declara el panel donde 
 * se podrá cambiar el tipo de juego.
 */

public class CambioJuego extends JPanel implements Observador{
	
	private ControladorGUI control;
	private JComboBox lista_juegos;
	private JButton cambiar;
	private Pedir pedir = new Pedir();
	private GestionJugador gestionj;
	private int col;
	private int fila;
	
	
	public CambioJuego(ControladorGUI control)
	{
		this.control = control;
		control.addObservador(this);
		initGUI();
		confEvents();
		control.reset();
		
	}
	
	public void initGUI()
	{
		gestionj = new GestionJugador(control);
		setLayout(new BorderLayout());
		setSize(50,50);
		setBorder(javax.swing.BorderFactory.createTitledBorder("Cambio Juego"));
		lista_juegos = new JComboBox(TipoJuego.values());
		cambiar = new JButton("Cambiar",new ImageIcon(getClass().getResource("imagenes/cambiar.png")));
		
		JPanel modos = new JPanel();
		modos.setLayout(new BorderLayout());
		
		modos.add(pedir,BorderLayout.NORTH);
		modos.add(gestionj,BorderLayout.CENTER);
		
		
		add(lista_juegos,BorderLayout.NORTH);
		add(modos,BorderLayout.CENTER);
		add(cambiar,BorderLayout.SOUTH);
		
		pedir.setVisible(false);
		gestionj.setVisible(true);
	}

	public void confEvents(){
		
		lista_juegos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				TipoJuego tj=(TipoJuego)lista_juegos.getSelectedItem();
				pedir.setVisible(tj.esRedimensionable());

			}
		});
		cambiar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				TipoJuego tj = (TipoJuego)lista_juegos.getSelectedItem();
				try{
					col=pedir.getCol();
					fila=pedir.getFila();
					if(col==0 && fila==0)
					{
						col=10;
						fila=10;
					}
					
					if(col>20 || fila>20)
					{
						JOptionPane.showMessageDialog(null, "Introduce un valor entre 1 y 20");
					}
					else
						control.cambioJuego(tj.creaFactoria(col,fila));
				}
				catch(NumberFormatException ex){JOptionPane.showMessageDialog(null, "Uso incorrecto: "+ex.getLocalizedMessage());}
			}
			});
	
	}
	
	public void onReset(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		//cambiar.setEnabled(true);
		
	}

	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		//cambiar.setEnabled(false);
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {

	}

	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {

	}

	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual) {
		
	}



	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovStart(Ficha turno, TableroInmutable tablero) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNotificaJuego(TipoJuego actual) {
		lista_juegos.setSelectedItem(actual);
		
	}

}
