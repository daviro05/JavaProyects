package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioGravity implements Jugador {
	
private FactoriaTipoJuego factoria;
	
	public JugadorAleatorioGravity()
	{
		this.factoria = new FactoriaGravity();
	}
	
	//Genera las coordenadas del movimiento y comprueba que esa casilla esta vacia
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
	int col= (int) (Math.random()*tab.getColumnas()+1);
	int fila= (int) (Math.random()*tab.getFilas()+1);
		
		while(tab.getCasilla(col, fila)!=Ficha.VACIA){
			col= (int) (Math.random()*tab.getColumnas()+1);
			fila= (int) (Math.random()*tab.getFilas()+1);
		}
	
		return factoria.creaMovimiento(col, fila, color);
	}

}
