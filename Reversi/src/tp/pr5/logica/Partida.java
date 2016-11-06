package tp.pr5.logica;

import java.util.ArrayList;

import tp.pr5.GUI.Observador;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.Jugador;
 

public class Partida {
	

	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private Movimiento[] posible;
	private UndoStack undoStack;
	private ReglasJuego reglas;
	private ArrayList <Observador> obs;
	private TipoJuego actual;
	
	public Partida(ReglasJuego reglas)
	{
		obs = new ArrayList<Observador>();
		this.reglas = reglas;
		this.tablero=reglas.iniciaTablero();
		//actual=reglas.juegoAtual();
		//reset(reglas);
		cambioJuego(reglas);
	}
	
	
	public boolean isTerminada()
	{
		return terminada;
	}
	
	public void setTerminada(boolean terminada)
	{
		this.terminada = terminada;
	}
	
	public Ficha getTurno() {
		return turno;
	}
	
	public Tablero getTablero()
	{
		return tablero;
	}
	
	public void setTurno(Ficha turno) {
		this.turno = turno;
	}
	
	public void setGanador(Ficha ganador) {
		this.ganador=ganador;
	}

	public Ficha getGanador() {
		return ganador;
	}
/* El metodo comprobar limites, devuelve false si las coordenadas
 * introducidas estan fuera del tablero.
 */
	
	public void comprobarLimites(int col) throws MovimientoInvalido
	{
		if(col>tablero.getColumnas() || col<1)
		{
			throw new MovimientoInvalido("Movmiento Incorrecto");
		}
	}
	
/* El metodo undo de partida llamara segun el movimiento
 * a los metodos undo de movimientoConecto o movimientoComplica
 */
	public void undo()
	{
		boolean hayMas = undoStack.getNumUndo()!=0;
		if(hayMas)
		{
			Movimiento movimiento=undoStack.pop();
			movimiento.undo(tablero);
			turno = reglas.siguienteTurno(getTurno(), tablero);

			for(Observador o: obs)
			{
				o.onUndo(tablero, turno, hayMas,reglas.posibles(tablero, turno),actual);
			}
		}
		else{
			for(Observador o: obs)
			{
				o.onUndoNotPossible(tablero, turno);
			}
		}
		
		
	}
	/*El metodo ejecutaMovimiento recibe un movimento que sera de
	 * tipo movimientoComplica o movimientoConecta segun al tipo de
	 * juego al que estemos jugando y ejecutara el movimiento segun 
	 * las reglas de ese juego
	 */
	public void ejecutaMovimiento(Movimiento movimiento)
	{
		Ficha fich_ganador;
		
		if(movimiento == null){
			MovimientoInvalido m1 = new MovimientoInvalido("No te entiendo.");
			for(Observador o: obs)
			{
				o.onMovimientoIncorrecto(m1);
			}
			actualizaTablero();
		}
			
		else if(movimiento.getJugador() != turno || isTerminada())
		{
			MovimientoInvalido m1 = new MovimientoInvalido("Movimiento incorrecto.");
			for(Observador o: obs)
			{
				o.onMovimientoIncorrecto(m1);
			}
			actualizaTablero();
			
		}
		else
		{
			try{
				movimiento.ejecutaMovimiento(tablero);
				undoStack.push(movimiento);
				fich_ganador = reglas.hayGanador(movimiento, tablero, tablero.tableroLleno());
			
				if(fich_ganador!=Ficha.VACIA)
				{
					ganador = fich_ganador;
					terminada = true;
				}
				else if(reglas.tablas(getTurno(), tablero) == true)
				{
					terminada = true;
				}
				
				turno = reglas.siguienteTurno(getTurno(), tablero);
				
				if(turno==Ficha.VACIA){
					
					ganador=reglas.hayGanador(movimiento, tablero, true);
					terminada=true;
				}
			
				
				for(Observador o: obs)
				{
					if(isTerminada())
					{
						o.onPartidaTerminada(tablero, ganador);
					}
					else
						
						o.onMovimientoEnd(tablero,movimiento.getJugador(),turno,reglas.posibles(tablero, turno),actual);
				}
		
			}
			catch(MovimientoInvalido m2){
				for(Observador o: obs)
				{
					o.onMovimientoIncorrecto(m2);
				}
				actualizaTablero();
				
			}
		}
			
		for(Observador o2:obs)
		{
			o2.onPack();
		}
		

	}
	
	public void poner(Jugador juega, FactoriaTipoJuego factoria)
	{
		Movimiento mov;
		mov = juega.getMovimiento(tablero,turno);
		ejecutaMovimiento(mov);
		
	}
	
	
	public void ponerAleatorio(FactoriaTipoJuego factoria) throws MovimientoInvalido{
		
		//Aqui tratarlo con un try y catch
		Movimiento mov=factoria.creaJugadorAleatorio().getMovimiento(tablero, turno);
		ejecutaMovimiento(mov);
	}
	/* Este metodo se utiliza para sacar el tablero por pantalla
	 * sin violar la encapsulacion.
	 */
	
	public String dibujarTablero()
	{
		return tablero.toString();
	}
	
	/*
	 * El metodo reset de la clase Partida, reinicia la partida creada
	 * dejando el primer turno a las fichas blancas, dejando terminada y ganador a false
	 * y creando un tablero.
	 * Las variables numUndo se pone a 0 y se crea un array undoStack con el tamaño
	 * en proporcion al tamaño del tablero.
	 */
	
	public void reset(ReglasJuego reglas){
		
		Ficha.BLANCA.setTipo_turno(TipoTurno.HUMANO);
		Ficha.NEGRA.setTipo_turno(TipoTurno.HUMANO);
		ModoJuego modo=new ModoHumano();
		Ficha.BLANCA.setModo(modo);
		Ficha.NEGRA.setModo(modo);
		setTurno(reglas.jugadorInicial());
		detenerPartida();
		this.reglas=reglas;
		setTerminada(false);
		setGanador(Ficha.VACIA);
		tablero = reglas.iniciaTablero();
		undoStack = new UndoStack();
		continuarPartida();
		for(Observador o2: obs)
		{
			o2.onNotificaJuego(actual);
		}
	}
	
	
	public void cambioJuego(ReglasJuego reglas){
		
		actual=reglas.juegoAtual();
		reset(reglas);
		for(Observador o: obs){
			
			o.onCambioJuego(tablero, turno,reglas.posibles(tablero, turno),actual);
		}
		for(Observador o2: obs)
		{
			o2.onPack();
		}
	}
	
	public void reset(){
		reset(reglas);
		for(Observador o: obs)	
		{
			o.onReset(tablero, turno,reglas.posibles(tablero, turno),actual);
		}
		
	}
	
	public void detenerPartida()
	{
		ModoJuego modo=turno.getModo();
		modo.terminar();
	}
	
	public void continuarPartida()
	{
		ModoJuego modo= turno.getModo();
		modo.comenzar();
		for(Observador o: obs)	
		{
			o.onMovStart(turno, tablero);;
		}
	}
	
	public void addObservador(Observador o)
	{
		this.obs.add(o);
	}
	
	public void actualizaTablero() {
		for(Observador o: obs)
		{
			o.onError(tablero, turno);
		}
		
	}
}
