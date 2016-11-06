package tp.pr5.logica;

public class ReglasConecta4 implements ReglasJuego 
{
	private int columnas=7;
	private int filas=6;
	public ReglasConecta4()
	{

	}

	public Tablero iniciaTablero() 
	{
		Tablero tab_conecta4 = new Tablero(columnas,filas);
		return tab_conecta4;
	}

	public Ficha jugadorInicial() 
	{
		return Ficha.BLANCA;
	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t, boolean terminado) 
	{
		Ficha color = ultimoMovimiento.getJugador();
		if (hayGanadorColor(color,t))
			return color;
		else
			return Ficha.VACIA;

		
	}

	// El método hayGanadorColor, comprueba para cada metodo (comprobarHorizontal, comprobarVertical, comprobarDiagonales
	// si hay algun ganador.
	
	public boolean hayGanadorColor(Ficha color,Tablero tab)
	{
		boolean ganador = false;
		if (comprobarVertical(color, tab)) 
		{
			ganador = true;
		} 
		else if (comprobarHorizontal(color, tab)) 
		{
			ganador = true;
		} 
		else if (comprobarDiagonal(color, tab)) 
		{
			ganador = true;
		} 
		else if (comprobarDiagonalInv(color, tab)) 
		{
			ganador = true;
		}
		return ganador;
		
	}

	// Comprueba verticalmente si hay algun ganador en el tablero.
	public boolean comprobarVertical(Ficha color, Tablero t){
		int ganador=0;
		
		for(int j=1; j <= t.getColumnas();j++){
			ganador=0;
			for(int i=1; i<=t.getFilas();i++){
				if(t.getCasilla(j, i)==color)
					ganador++;
				else
					ganador=0;
				if(ganador==4)
					return true;
			}
		}
		return false;
	}
	
	/*
	 * El metodo combrobarHorizontal vera si se ha gando con 4 o mas fichas
	 * colocadas horizontalmente, para ello comprobamos las fichas iguales a
	 * la ultima ficha introducida por el jugador a la izda de esta, y a
	 * continuacion las que estan a la dcha, si la suma de ambas es 3 0 mas,
	 * entonces la funcion devolvera true.
	 */
	public boolean comprobarHorizontal(Ficha color, Tablero t){
		int ganador=0;
		
		for(int i=1; i<=t.getFilas();i++){
			ganador=0;
			for(int j=1; j <= t.getColumnas();j++){
				if(t.getCasilla(j, i)==color)
					ganador++;
				else
					ganador=0;
				if(ganador==4)
					return true;
			}
		}
		return false;

	}
	/*
	 * El metodo comprobarDiagonal es similar al metodo comprobarHorizontal, ya
	 * que en ambos se comprobara por ambos lados y luego se sumaran el numero
	 * de fichas iguales, por cada lado, para decidir si se ha ganado diagonalmente,
	 * este metodo solo cubre los casos de diagonal de la manera "\".
	 */
	public boolean comprobarDiagonal(Ficha color, Tablero t)
	{
		int ganador=0;
		int contador = 0;
		for(int i=1; i<=t.getColumnas()-3;i++)
		{
			contador = 0;
			ganador = 0;
			while(i+contador<=t.getColumnas() && 1+contador <= t.getFilas())
			{
			if(t.getCasilla(i+contador, 1+contador) == color)
			{
				ganador++;	
			}
			else
			{
				ganador = 0;
			}
			if(ganador == 4)
			{
				return true;
			}
				contador++;
			}	
		}
		for(int j=2; j<=t.getFilas()-3;j++)
		{
			contador = 0;
			ganador = 0;
			while(1+contador<=t.getColumnas() && j+contador <= t.getFilas())
			{
			if(t.getCasilla(1+contador, j+contador) == color)
			{
				ganador++;	
			}
			else
			{
				ganador = 0;
			}
			if(ganador == 4)
			{
				return true;
			}
				contador++;
			}
		}
		return false;
	}
	
	/*
	 * El metodo comprobarDiagonalInv es identico al de comprobarDiagonal, excepto
	 * que comprobarDiagonalInv vera si se ha ganado en la forma "/"
	 */
	public boolean comprobarDiagonalInv(Ficha color, Tablero t)
	{
		int ganador=0;
		int contador = 0;
		for(int i=1; i<=t.getColumnas()-3;i++)
		{
			contador = 0;
			ganador = 0;
			while(i+contador<=t.getColumnas() && t.getFilas()-contador >= 1){
			if(t.getCasilla(i+contador, t.getFilas()-contador) == color)
			{
				ganador++;	
			}
			else
			{
				ganador = 0;
			}
			if(ganador == 4)
			{
				return true;
			}
				contador++;
			}
		}
		for(int j=t.getColumnas()-1; j>3;j--)
		{
			contador = 0;
			ganador = 0;
			while(1+contador<=t.getColumnas() && j-contador >=1)
			{
			if(t.getCasilla(1+contador, j-contador) == color)
			{
				ganador++;	
			}
			else
			{
				ganador = 0;
			}
			if(ganador == 4)
			{
				return true;
			}
				contador++;
			}
			
		}
		return false;
	}
	
	/*El metodo tablas de Conecta4 devuelve siempre false si
	 * la ultima ficha introducida es blanca ya que el tablero
	 *  siempre tiene un numero par de casillas, y no puede
	 *   llenarse con una blanca
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) 
	{
		boolean lleno = false;
		
		if(ultimoEnPoner == Ficha.BLANCA)
		{
			return lleno;
		}
		
		lleno = t.tableroLleno();
	
	return lleno;
	}

	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) 
	{
		Ficha turno=Ficha.VACIA;
		
			if(ultimoEnPoner == Ficha.BLANCA)
			{
				turno = Ficha.NEGRA;
			}
			else if(ultimoEnPoner == Ficha.NEGRA)
			{
				turno = Ficha.BLANCA;
			}

		return turno;
	}

	
	public int getColumnas() {
		return columnas;
	}

	
	public int getFilas() {
		return filas;
	}

	@Override
	public boolean[][] posibles(Tablero tab, Ficha color) {
		boolean [][] p=new boolean[tab.getColumnas()][tab.getFilas()];
		return p;
	}

	
	public TipoJuego juegoAtual() {
		return TipoJuego.Conecta4;
	}
	
}