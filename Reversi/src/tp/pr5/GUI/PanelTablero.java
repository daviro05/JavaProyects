package tp.pr5.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

public class PanelTablero extends JPanel {
	
	/*
	 * PanelTablero es el panel donde se dibujara el tablero donde se colocarán las fichas.
	 * Se hará mediante botones que al pulsarlos se colocaran las fichas.
	 */
	
	private ControladorGUI control;
	private MiBoton[][] matrizBotones;
	private boolean activo;
	
	public class MiBoton extends JButton{

		private int col;
		private int fila;
		
		
		public MiBoton(int col, int fila){
			this.col = col;
			this.fila = fila;
			ImageIcon casillaVacia = new ImageIcon(getClass().getResource("imagenes/casillatras.png"));
			ImageIcon ficha_vacia = new ImageIcon(casillaVacia.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_DEFAULT));
			setIcon(ficha_vacia);
			
		}
	}
	
	public PanelTablero(ControladorGUI control) 
	{
		this.control = control;
		activo=true;
		
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void initGUI(int col, int fila)
	{
		
		matrizBotones = new MiBoton[col][fila];
		
		setLayout(new GridLayout(fila,col)); //num filas y cols
		
		for(int fj=0;fj<fila;fj++)
		{
			for(int ci=0;ci<col;ci++)
			{
				matrizBotones[ci][fj] = new MiBoton(ci,fj); 
				matrizBotones[ci][fj].setPreferredSize(new Dimension(50, 50));
				matrizBotones[ci][fj].setBorderPainted(false);
				add(matrizBotones[ci][fj]);
				confEvents(ci,fj);
			}
		}
		
	}
	
	public void confEvents(final int col, final int fila)
	{
		matrizBotones[col][fila].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(activo)
					control.poner(col+1, fila+1);
			}
		});
	}
	
	
	public void actualizaTablero(TableroInmutable tablero){
		
		ImageIcon casillaVacia = new ImageIcon(getClass().getResource("imagenes/casillatras.png"));
		ImageIcon ficha_vacia = new ImageIcon(casillaVacia.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_DEFAULT));
		
		ImageIcon casillaNegra = new ImageIcon(getClass().getResource("imagenes/casillan2.png"));
		ImageIcon ficha_negra = new ImageIcon(casillaNegra.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_DEFAULT));
		
		ImageIcon casillaBlanca = new ImageIcon(getClass().getResource("imagenes/casillab2.png"));
		ImageIcon ficha_blanca = new ImageIcon(casillaBlanca.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_DEFAULT));
		
		for(int fj=0; fj<tablero.getFilas();fj++)
		{
			for(int ci=0; ci<tablero.getColumnas();ci++)
			{
				if(tablero.getCasilla(ci+1,fj+1) == Ficha.VACIA)
				{
						matrizBotones[ci][fj].setIcon(ficha_vacia);
				}
				else if(tablero.getCasilla(ci+1,fj+1) == Ficha.BLANCA)
				{
						matrizBotones[ci][fj].setIcon(ficha_blanca);
				}
				else
					matrizBotones[ci][fj].setIcon(ficha_negra);
			}
				 
		}
		
	}

	public void actualizaPosibles(TableroInmutable tablero, boolean[][] posibles) {
		ImageIcon casillaPosible = new ImageIcon(getClass().getResource("imagenes/casillaposible2.jpg"));
		ImageIcon ficha_posible = new ImageIcon(casillaPosible.getImage().getScaledInstance(50,50,java.awt.Image.SCALE_DEFAULT));
		
		for(int fj=0; fj<tablero.getFilas();fj++)
		{
			for(int ci=0; ci<tablero.getColumnas();ci++)
			{
				if(posibles[ci][fj]){
					matrizBotones[ci][fj].setIcon(ficha_posible);
				}
			}
			
		}
	}
	
}