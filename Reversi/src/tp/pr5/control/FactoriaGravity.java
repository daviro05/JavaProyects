package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego {
	
	private int columnas = 10;
	private int filas = 10;
	
	public FactoriaGravity()
	{
		
	}
	
	// Constructor de FactoriaGravity donde recibe dos parametros de entrada enteros para especificar
	// el numero de filas y columnas deseado por el usuario.
	public FactoriaGravity(int x, int y)
	{
		columnas = x;
		filas = y;
	}

	public ReglasJuego creaReglas() {

		return new ReglasGravity(columnas,filas);
	}


	//este metodo devuelve un nuevo movimiento de gravity en las posiciones y el color deseados.
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoGravity(col, fila, color);
	}

	 //este metodo devuelve un nuevo jugador humano de gravity
	public Jugador creaJugadorHumanoConsola(Scanner in) {

		return new JugadorConsolaGravity(in,this);
	}

	 //este metodo devuelve un nuevo jugador aleatorio de gravity
	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioGravity();
	}

}
