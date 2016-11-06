package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego{

	//este metodo devuelve unas reglas nuevas de complica
	public ReglasJuego creaReglas() 
	{	
		return new ReglasComplica();
	}
	//este metodo devuelve un nuevo movimiento de complica en la posicion y el color deseado.
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoComplica(col, color);
	}

	 //este metodo devuelve un nuevo jugador humano de complica
	public Jugador creaJugadorHumanoConsola(Scanner in) {

		return new JugadorConsolaComplica(in,this);
	}

	 //este metodo devuelve un nuevo jugador aleatorio de complica
	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioComplica();
	}

}
