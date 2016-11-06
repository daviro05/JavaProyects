package tp.pr5.logica;


public class MovimientoConecta4 extends Movimiento{

	
	public MovimientoConecta4(int donde, Ficha color)
	{
		super(donde, color);
	}
		
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido
	{
		/*
		 * El método ejecutaMovimiento introduce una ficha del color indicado 
		 * en la columna indicada, la fila en la que se introduce, sera la
		 * primera libre empezando desde abajo.
		 */
		
		if(getCol()>tab.getColumnas() || getCol()<1)
		{
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 7.");
		}

		int pos = tab.calcularPos(getCol());
		if(pos == 0)
		{
			throw new MovimientoInvalido("Columna llena.");
		}
		else
		{
			tab.setCasilla(getCol(), pos, getJugador());
		}
	}

	/*
	 * El método undo, lo utilizaremos para deshacer los movimientos
	 * que se han creado y colocando una Ficha vacía en el último lugar
	 * donde había una ficha puesta.
	 * 
	 */

	public void undo(Tablero tab) 
	{
		boolean des = false;
		int contador=1;

		while(des==false)
		{
			if(tab.getCasilla(getCol(),contador)!= Ficha.VACIA)
			{
				tab.setCasilla(getCol(),contador,Ficha.VACIA);
				des = true;
			}
			contador++;
		}

	}
	
	
		
}

