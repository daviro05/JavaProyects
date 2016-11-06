package tp.pr5.GUI;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;

/*
 * Interfaz observador, donde están todos los métodos que utilizaremos
 * para aplicar el MVC.
 */
public interface Observador 
{
	void onReset(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual);
	void onPartidaTerminada(TableroInmutable tablero, Ficha ganador);
	void onCambioJuego(TableroInmutable tablero, Ficha turno,boolean [][]posibles, TipoJuego actual);
	// Gestión de movimientos
	void onMovStart(Ficha turno, TableroInmutable tablero);
	void onUndoNotPossible(TableroInmutable tablero, Ficha turno);
	void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas,boolean [][]posibles, TipoJuego actual);
	void onMovimientoEnd(TableroInmutable tablero,Ficha jugador, Ficha turno, boolean [][]posibles, TipoJuego actual);
	void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
	void onPack();
	void pintarCasilla(Movimiento [] posible);
	void onError(TableroInmutable tablero, Ficha turno);
	void onNotificaJuego(TipoJuego actual);

}
