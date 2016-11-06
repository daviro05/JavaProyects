package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;

public class JugadorAleatorioReversi implements Jugador{

	private FactoriaTipoJuego factoria;
	
	public JugadorAleatorioReversi(){
		this.factoria= new FactoriaReversi();
	}
	
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int col= (int) (Math.random()*tab.getColumnas()+1);
		int fila= (int) (Math.random()*tab.getFilas()+1);
		
			
			while(!casillaCorrecta(tab, col, fila,color)){
				col= (int) (Math.random()*tab.getColumnas()+1);
				fila= (int) (Math.random()*tab.getFilas()+1);
			}
		
			return factoria.creaMovimiento(col, fila, color);
	}
	


	public boolean casillaCorrecta(Tablero tab,int col, int fila, Ficha color){
		
		if(tab.getCasilla(col, fila)!= Ficha.VACIA)
			return false;
		boolean ok=false;
		boolean limite=false;
		int contador;
		for(int i=-1;i<=1;i++){
			for(int j=-1; j<=1;j++){
				limite=false;
				contador=1;
				if(tab.getCasilla(col+i,fila+j)!= color && tab.getCasilla(col+i,fila+j)!= Ficha.VACIA)
					while(limite==false){
						if(col+contador*i>tab.getColumnas()||fila+contador*j>tab.getFilas()){
							limite=true;
						}
						else if(tab.getCasilla(col+contador*i,fila+contador*j)== Ficha.VACIA){
							limite=true;
						}
						else if(tab.getCasilla(col+contador*i,fila+contador*j)!= color){
							contador++;
						}
						else{
							return true;
						}
					}
			}
		}
		return ok;
	}
}
