package tp.pr5.logica;


public interface ReglasJuego 
{
	Tablero iniciaTablero();
	
	Ficha jugadorInicial();
	
	Ficha hayGanador(Movimiento ultimoMovimiento,Tablero t, boolean terminado);

	boolean tablas(Ficha ultimoEnPoner,Tablero t);
	
	Ficha siguienteTurno(Ficha ultimoEnPoner,Tablero t);
	
	public abstract int getColumnas();
	
	public abstract int getFilas();
	
	public abstract boolean comprobarVertical(Ficha color, Tablero t);
	
	public abstract boolean comprobarHorizontal(Ficha color, Tablero t);
	 
	public abstract boolean comprobarDiagonal(Ficha color, Tablero t);
	
	public abstract boolean comprobarDiagonalInv(Ficha color, Tablero t);
	
	public abstract boolean[][] posibles(Tablero tab, Ficha color);

	public abstract TipoJuego juegoAtual(); 

}
