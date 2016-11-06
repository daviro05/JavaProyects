package tp.pr5.logica;

public class ReglasReversi implements ReglasJuego {

	private int columnas=8;
	private int filas=8;
	
	public ReglasReversi(){
		
	}
	
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(columnas,filas);
		tab.setCasilla(4, 4, Ficha.BLANCA);
		tab.setCasilla(5, 5, Ficha.BLANCA);
		tab.setCasilla(5, 4, Ficha.NEGRA);
		tab.setCasilla(4, 5, Ficha.NEGRA);
		
		return tab;
		
	}

	@Override
	public Ficha jugadorInicial() {
	
		return Ficha.NEGRA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t, boolean terminado) {
		int blancas=0;
		int negras=0;
		Ficha ganador=Ficha.VACIA;
		if(terminado){
			for(int i=1;i<=t.getColumnas();i++){
				for(int j=1;j<=t.getColumnas();j++){
					if(t.getCasilla(i, j)==Ficha.BLANCA)
						blancas++;
					else if(t.getCasilla(i, j)==Ficha.NEGRA)
						negras++;
				}
			}
			if(negras>blancas)
				ganador=Ficha.NEGRA;
			else if(blancas > negras)
				ganador=Ficha.BLANCA;		
		}
		return ganador;
	}
	
	

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return t.tableroLleno();	
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha turno=ultimoEnPoner;
		Ficha siguiente=Ficha.VACIA;

		if(ultimoEnPoner == Ficha.BLANCA){
			siguiente = Ficha.NEGRA;
		}
		else if(ultimoEnPoner == Ficha.NEGRA){
			siguiente = Ficha.BLANCA;	
		}
		
		if(hayPosibles(t, siguiente)){
			if(ultimoEnPoner == Ficha.BLANCA)
			{
				turno = siguiente;
			}
			else if(ultimoEnPoner == Ficha.NEGRA)
			{
				turno = siguiente;
			}
		}
		else if(!hayPosibles(t, ultimoEnPoner)){
					return Ficha.VACIA;
		}
			
	

	return turno;
	}


	public int getColumnas() {

		return columnas;
	}


	public int getFilas() {
	
		return filas;
	}
	
	public boolean[][] posibles(Tablero tab, Ficha color){
		MovimientoReversi mov;
		boolean[][]posible=new boolean[tab.getColumnas()][tab.getFilas()];
		for(int i =1; i<=tab.getFilas();i++){
			for(int j=1; j<=tab.getColumnas();j++) {
				if(tab.getCasilla(j, i)==Ficha.VACIA){
					mov= new MovimientoReversi(j, i, color);
					posible[j-1][i-1]=mov.casillaCorrecta(tab, false);
				}
				else
					posible[j-1][i-1]=false;
			}
		}
		return posible;	
	}
	
	public boolean hayPosibles( Tablero tab, Ficha turno){
		MovimientoReversi mov;
		for(int i=1; i<=tab.getFilas();i++){
			for(int j=1; j<=tab.getColumnas();j++) {
				if(tab.getCasilla(j, i)==Ficha.VACIA){
					mov=new MovimientoReversi(j, i, turno);
					if(mov.casillaCorrecta(tab,false)){
						return true;
					}
				}
			}
		}
	return false;
	}
	@Override
	public boolean comprobarVertical(Ficha color, Tablero t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprobarHorizontal(Ficha color, Tablero t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprobarDiagonal(Ficha color, Tablero t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprobarDiagonalInv(Ficha color, Tablero t) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public TipoJuego juegoAtual() {

		return TipoJuego.Reversi;
	}

}
