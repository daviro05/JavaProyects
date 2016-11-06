package tp.pr5.control;

import java.util.InputMismatchException;
import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorConsolaReversi implements Jugador {
	private Scanner sc;
	private FactoriaTipoJuego factoria;

	public JugadorConsolaReversi(Scanner sc, FactoriaTipoJuego factoria){
		this.sc= sc;
		this.factoria=factoria;
	}

	
public Movimiento getMovimiento(Tablero tab, Ficha color) {
	Movimiento mov=null;
	System.out.print("Introduce la columna: ");
	try{
		int columna = sc.nextInt();
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		sc.nextLine();
		mov =factoria.creaMovimiento(columna,fila,color);
	}

	catch(InputMismatchException e1)
	{
		sc.nextLine();
	}
	return mov;
	}

}
