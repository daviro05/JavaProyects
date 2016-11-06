package tp.pr5.GUI;

import tp.pr5.control.Controlador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/*
 * La clase VistaConsola controla todas las salidas del modo
 * Consola de nuestro juego.
 */
public class VistaConsola implements Observador{
	private Controlador control;
	
	public VistaConsola(Controlador control){
		
		this.control= control;
		control.addObservador(this);
	}
	

	public void onReset(TableroInmutable tab, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		System.out.println("Partida reiniciada.");
		pintar(tab, turno);
	}

	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
		System.out.println(tablero.toString());
		if(ganador!=Ficha.VACIA)
		{
			System.out.println("\nGanan las " + ganador.toString().toLowerCase()+ "s");
		}
		else
		{
			System.out.println("\nPartida terminada en tablas.");
		}
		
		
	}

	public void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual) {
		pintar(tablero, turno);
	}

	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		System.err.println("Imposible deshacer.");
		pintar(tablero, turno);
	}

	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual) {
		pintar(tablero, turno);
		
	}


	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		System.err.println(movimientoException.getMessage());
	}

	public void onPack() {
		
	}
	
	public void pintar(TableroInmutable tablero, Ficha turno){
		
		System.out.println(tablero.toString());
		System.out.println("\nJuegan "+turno.toString().toLowerCase()+"s");
		System.out.print("Qué quieres hacer? ");
}


	@Override
	public void onError(TableroInmutable tablero, Ficha turno) {
		pintar(tablero, turno);
		
	}
	
	


	@Override
	public void onMovStart(Ficha turno,TableroInmutable tablero) {

		
	}


	@Override
	public void pintarCasilla(Movimiento[] posible) {

		
	}



	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,Ficha turno, boolean[][] posibles, TipoJuego actual) {
		
		pintar(tablero, turno);
		control.cambioJugador();
		
	}


	@Override
	public void onNotificaJuego(TipoJuego actual) {
		// TODO Auto-generated method stub
		
	}


}
