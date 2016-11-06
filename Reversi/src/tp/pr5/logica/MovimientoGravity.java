package tp.pr5.logica;

public class MovimientoGravity extends Movimiento
{
	public MovimientoGravity(int columna, int fila, Ficha color)
	{
		super(columna, fila, color);
		
	}

	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido 
	{
		
		if(getCol()>tab.getColumnas() || getCol()<1 || getFil()>tab.getFilas() || getFil()<1)
		{
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		if(tab.getCasilla(getCol(), getFil())!= Ficha.VACIA)
		{
			throw new MovimientoInvalido("Casilla ocupada.");
		}
			
		else 
		{
			colocaFicha(tab);
		}
	}
	
	/* Método que calcula la posicion de las fichas para el 
	 * juego gravity segun el tablero que se decida crear.
	 */

	public void colocaFicha(Tablero tab)
	{
		int menor= tab.getFilas();
		int izda = getCol()-1;
		int dcha = tab.getColumnas()-getCol();
		int alto = getFil()-1;
		int bajo = tab.getFilas()-getFil();
		
		int x=0,y=0,contador=0;
	
		if(izda < menor)
			menor = izda;
		if(dcha < menor)
			menor = dcha;
		if(alto < menor)
			menor = alto;
		if(bajo < menor)
			menor = bajo;
		
		if(izda == menor)
			x--;
		if(dcha == menor)
			x++;
		if(alto == menor)
			y--;
		if(bajo == menor)
			y++;
		boolean c1= (tab.getCasilla(getCol()+(contador*x),getFil()+(contador*y))==Ficha.VACIA);
		boolean c2= getCol()+(contador*x)>0 && getFil()+(contador*y)>0;
		boolean c3= getCol()+(contador*x)<=tab.getColumnas() && getFil()+(contador*y)<=tab.getFilas();
		while(c1 && c2 && c3 && (x!=0|| y!=0))
		{
			contador++;
			c1= (tab.getCasilla(getCol()+(contador*x),getFil()+(contador*y))==Ficha.VACIA);
			c2= getCol()+(contador*x)>0 && getFil()+(contador*y)>0;
			c3= getCol()+(contador*x)<=tab.getColumnas() && getFil()+(contador*y)<=tab.getFilas();

		}
		tab.setCasilla(getCol()+((contador-1)*x), getFil()+((contador-1)*y), getJugador());
		
		setCol(getCol()+((contador-1)*x));
		setFila(getFil()+((contador-1)*y));
		
	}
	
	// Método para deshacer en Gravity.
	
	public void undo(Tablero tab) {

		tab.setCasilla(getCol(), getFil(), Ficha.VACIA);
	}

}
