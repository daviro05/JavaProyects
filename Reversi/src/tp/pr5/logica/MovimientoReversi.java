package tp.pr5.logica;

import java.util.ArrayList;

public class MovimientoReversi extends Movimiento{
	
	private boolean[][] giradas;
	
	public MovimientoReversi(int columna, int fila, Ficha color)
	{
		super(columna, fila, color);
		giradas = new boolean[8][8];
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				giradas[i][j] = false;
	}
		
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido
	{
		if(getCol()>tab.getColumnas() || getCol()<1 || getFil()>tab.getFilas() || getFil()<1)
		{
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		else if(tab.getCasilla(getCol(), getFil())!= Ficha.VACIA)
		{
			throw new MovimientoInvalido("Casilla ocupada.");
		}
		else if(casillaCorrecta(tab,true))
			tab.setCasilla(getCol(), getFil(), getJugador());
		else
		{
			throw new MovimientoInvalido("Posicion incorrecta.");
		}
	}

	public boolean casillaCorrecta(Tablero tab, boolean voltear){
		boolean ok=false;
		boolean limite=false;
		int contador;
		for(int i=-1;i<=1;i++){
			for(int j=-1; j<=1;j++){
				limite=false;
				contador=1;
				if((tab.getCasilla(getCol()+i,getFil()+j)!= getJugador()) && (tab.getCasilla(getCol()+i,getFil()+j)!= Ficha.VACIA))
					while(limite==false){
						if(getCol()+contador*i>tab.getColumnas()||getFil()+contador*j>tab.getFilas())
							limite=true;
						else if(tab.getCasilla(getCol()+contador*i,getFil()+contador*j)== Ficha.VACIA){
							limite=true;
						}
						else if(tab.getCasilla(getCol()+contador*i,getFil()+contador*j) != getJugador()) //Aqui esta el fallo
						{
							contador++;
						}
						else{
							limite=true;
							ok=true;
							if(voltear)
								volteaVector(tab,i,j,contador);
						}
					}
			}
		}
		return ok;
	}
	
	public void volteaVector(Tablero tab, int x, int y, int modulo){
		for(int i=1; i<modulo; i++){
			tab.setCasilla(getCol()+i*x,getFil()+i*y, getJugador());
			giradas [getCol()+i*x-1][getFil()+i*y-1] = true;
			
		}
	}

	public void undo(Tablero tab) 
	{
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(giradas[i][j])
					tab.setCasilla(i+1, j+1, getJugador().contrario());
		
		tab.setCasilla(getCol(), getFil(), Ficha.VACIA);

	}
	
	
	}
