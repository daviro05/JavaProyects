package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;

public class FactoriaConecta4 implements FactoriaTipoJuego{


	public ReglasJuego creaReglas() {

		return new ReglasConecta4();
	}

	
	//este metodo devuelve un nuevo movimiento de conecta en la posicion y el color deseados.
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoConecta4(col, color);
	}

	 //este metodo devuelve un nuevo jugador humano de conecta4
	public Jugador creaJugadorHumanoConsola(Scanner in) {

		return new JugadorConsolaConecta4(in,this);
	}

	 //este metodo devuelve un nuevo jugador aleatorio de conecta4
	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioConecta4();
	}

}
