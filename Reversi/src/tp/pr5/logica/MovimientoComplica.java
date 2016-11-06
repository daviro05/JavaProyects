package tp.pr5.logica;

public class MovimientoComplica extends Movimiento{

	public MovimientoComplica(int donde, Ficha color)
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
		
		Ficha salvada = Ficha.VACIA;
		
		if(getCol()>tab.getColumnas() || getCol()<1)
		{
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y 4.");
		}
		
		if((tab.getCasilla(getCol(), 1)!= Ficha.VACIA))
		{
			salvada = tab.rotarCol(getCol());
		}

		int pos = tab.calcularPos(getCol());
		
		tab.setCasilla(getCol(), pos, getJugador()); 
		setColor(salvada); 
	}
	
	
	/*
	 * El método undo, lo utilizaremos para deshacer los movimientos
	 * que se han creado. En el caso de complica
	 * 
	 */

	public void undo(Tablero tab) 
	{
		boolean des = false;
		int contador=1;
		Ficha fich;
		
		if(getJugador() != Ficha.VACIA)
		{
			for(int i=2;i<=tab.getFilas();i++)
			{
				fich = tab.getCasilla(getCol(),i);
				tab.setCasilla(getCol(), i-1, fich);
				if(i==tab.getFilas())
				{
					tab.setCasilla(getCol(), i, getJugador());
				}
			}
		}
		else
		{	
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
	
}
