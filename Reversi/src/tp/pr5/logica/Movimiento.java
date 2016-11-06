package tp.pr5.logica;

/*
 * La clase abstracta Movimiento es la clase padre de la que heredan MovmientoConecta4
 * y MovmientoComplica todos los métodos no definidos en esta clase.
 */

public abstract class Movimiento 
{
	private int col;
	private int fila;
	private Ficha color;
	
	public Movimiento(int donde, Ficha color)
	{
		this.col = donde;
		this.color = color;	
	}
	
	public Movimiento(int numCol, int numFil, Ficha color)
	{
		this.col = numCol;
		this.fila = numFil;
		this.color = color;	
	}
	
	
	public int getCol() {
		return col;
	}
	
	public int getFil() {
		return fila;
	}
	
	public Ficha getJugador()
	{
		return color;
	}

	public void setColor(Ficha color) {
		this.color = color;
	}
	
	
	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	public abstract void undo(Tablero tab);

	
	
}
