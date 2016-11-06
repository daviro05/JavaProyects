package tp.pr5.GUI;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class ControladorGUI 
{
	private FactoriaTipoJuego factoria;
	private Partida partida;
	
	public ControladorGUI(FactoriaTipoJuego factoria, Partida partida) 
	{
		this.factoria = factoria;
		this.partida = partida;
	}
	
	public void cambioJuego(FactoriaTipoJuego factoria)
	{
		if(!partida.isTerminada())
			partida.detenerPartida();
		this.factoria = factoria;	
		this.partida.cambioJuego(factoria.creaReglas());
	}
	
	public void iniciar(){
		cambioJuego(factoria);
	}
	
	public void reset()
	{
		if(!partida.isTerminada())
			partida.detenerPartida();
		partida.reset();
	}
	
	public void aleatorio() throws MovimientoInvalido
	{	
		partida.ponerAleatorio(factoria);
	}
	
	public void poner(int col, int fila) 
	{
		partida.ejecutaMovimiento(factoria.creaMovimiento(col, fila, partida.getTurno()));
	}
	
	public Ficha getTurno()
	{
		return partida.getTurno();
	}
	
	public void undo()
	{
		partida.undo();
		comenzar();
	}
	
	public void comenzar()
	{
		partida.continuarPartida();
	}
	public void parar()
	{
		partida.detenerPartida();
	}
	

	public void addObservador(Observador o)
	{
		partida.addObservador(o);
	}

}
