package tp.pr5.GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

/*
 * La clase Pedir es un JPanel situado dentro del panel derecho
 * donde pediremos al usuario que introduzca la fila y columna para
 * el juego Gravity.
 */
public class Pedir extends JPanel{
	
	private JLabel pedirFilas;
	private JLabel pedirCol;
	private JTextField filas;
	private JTextField col;
	
	public Pedir(){
		
		initGUI();
	}
		
	private void initGUI() {
		pedirFilas = new JLabel("Filas:");
		pedirCol = new JLabel("Columnas:");
		filas = new JTextField(5);
		col = new JTextField(5);
		
		add(pedirFilas);
		add(filas);
		add(pedirCol);
		add(col);
		
		
	}
	public int getCol(){
		String cont=col.getText();
		if(cont.equals(""))
			return 0;
		int columna = Integer.parseInt(cont);
		
		return columna;
	}

public int getFila(){
	String cont=filas.getText();
	int fila=0;
	if(cont.equals(""))
	{
		return 0;
	}
	fila = Integer.parseInt(cont);
	return fila;
}

}
