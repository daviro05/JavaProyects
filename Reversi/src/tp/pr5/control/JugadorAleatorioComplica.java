package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioComplica implements Jugador {
	
private FactoriaTipoJuego factoria;
	
	public JugadorAleatorioComplica()
	{
		this.factoria = new FactoriaComplica();
	}
	
	// Genera un numero aleatorio entre 1 y el ancho y crea un movimiento en esa columna
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int col= (int) (Math.floor(Math.random()*tab.getColumnas()+1));
	
		return factoria.creaMovimiento(col, 1, color); //Aqui pasamos un uno en la fila porque no se utiliza
	}

}
