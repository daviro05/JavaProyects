package tp.pr5.logica;

import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;

public enum TipoJuego 
{
	Conecta4(new ReglasConecta4()){
		public  boolean esRedimensionable(){
			
			return false;
		}
		public FactoriaTipoJuego creaFactoria(int x, int y){
			return new FactoriaConecta4();
		}
		public boolean esGestionable() {
			return false;
		}
	},
	Complica(new ReglasComplica()){
		public  boolean esRedimensionable(){
			
			return false;
		}
		public FactoriaTipoJuego creaFactoria(int x, int y){
			return new FactoriaComplica();
		}
		public boolean esGestionable() {
			return false;
		}
	},
	
	Gravity(new ReglasGravity()){
		public  boolean esRedimensionable(){
		
			return true;
		}
		public FactoriaTipoJuego creaFactoria(int x, int y){
			return new FactoriaGravity(x,y);
		}
		public boolean esGestionable() {
			return false;
		}
	},
	Reversi(new ReglasReversi()){
		public boolean esRedimensionable(){
			
			return false;
		}
		public FactoriaTipoJuego creaFactoria(int x, int y){
			return new FactoriaReversi();
		}
		public boolean esGestionable() {
			return true;
		}
	};
	
	private final ReglasJuego reglas;
	
	TipoJuego(ReglasJuego reglas)
	{
		this.reglas = reglas;
	}
	
	public ReglasJuego getReglas()
	{
		return this.reglas;
	}
	
	public abstract FactoriaTipoJuego creaFactoria(int x, int y);
	public abstract boolean esRedimensionable();
	public abstract boolean esGestionable();
}
