package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioConecta4 implements Jugador{
	
private FactoriaTipoJuego factoria;
	
	public JugadorAleatorioConecta4()
	{
		this.factoria = new FactoriaConecta4();
	}

	
	//Genera un numero aleatorio entre 1 y el ancho y comprueba que la columna obtenida no este ya ocupada.
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		int col= (int)(Math.random()*tab.getColumnas()+1);
		
		while(tab.getCasilla(col,1)!=Ficha.VACIA){
			col= (int) (Math.random()*tab.getColumnas()+1);	
		}
		
		return factoria.creaMovimiento(col, 1, color); //Aqui pasamos un 1 en la fila porque no se utiliza
	}

}
