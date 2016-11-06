package tp.pr5.control;

import java.util.InputMismatchException;
import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

//Clase de JugadorConsola donde se pedira por pantalla la fila y la columna

public class JugadorConsolaConecta4 implements Jugador{
	
	private Scanner sc;
	private FactoriaTipoJuego factoria;
	
	public JugadorConsolaConecta4(Scanner sc, FactoriaTipoJuego f)
	{
		this.sc = sc;
		this.factoria = f;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color)
	{
		int fila = 1;
		int columna = 0;
		Movimiento movimiento = null;

		System.out.print("Introduce la columna: ");
		try{
			columna = sc.nextInt();
			sc.nextLine();
			movimiento = factoria.creaMovimiento(columna,fila,color);
		}
		catch(InputMismatchException e1)
		{
			sc.nextLine();
		}

		return movimiento;
	}
	
	

}
