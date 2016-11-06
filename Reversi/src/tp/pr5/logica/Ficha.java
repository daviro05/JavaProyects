package tp.pr5.logica;

/*
 * Tipo enumerado que almacena el tipo de Fichas
 * para el juego.
 */

public enum Ficha 
{
	
	VACIA{
		public Ficha contrario(){
			return Ficha.VACIA;
		}
	},
	NEGRA{
		public Ficha contrario(){
			return Ficha.BLANCA;
		}
	},
	BLANCA{
		public Ficha contrario(){
			return Ficha.NEGRA;
		}
	};
	
	private ModoJuego modo;
	private TipoTurno tipo_turno;
	
	public ModoJuego getModo() {
		return modo;
	}
	public void setModo(ModoJuego modo) {
		this.modo = modo;
	}
	public TipoTurno getTipo_turno() {
		return tipo_turno;
	}
	public void setTipo_turno(TipoTurno tipo_turno) {
		this.tipo_turno = tipo_turno;
	}
	
	public abstract Ficha contrario();
	
	
	
}
