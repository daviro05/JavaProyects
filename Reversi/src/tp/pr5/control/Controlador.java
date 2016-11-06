package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.GUI.Observador;
import tp.pr5.GUI.Pedir;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;


public class Controlador {
	
	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego factoria;
	private Jugador jugadorBlanca;
	private Jugador jugadorNegra;
	private Jugador jugador_actual;
	
	public Controlador(FactoriaTipoJuego factoria, Partida p, java.util.Scanner in){
			this.partida=p;
			this.in=in;
			this.factoria = factoria;
			this.jugadorBlanca = factoria.creaJugadorHumanoConsola(in);
			this.jugadorNegra = factoria.creaJugadorHumanoConsola(in);
	}

	/*
	 * El método eligeOpcion, recibira una orden introducida por teclado
	 * y en base a esa orden, realizará un procedimiento u otro.
	 * Podremos poner una ficha, deshacer un movimiento
	 * reiniciar la partida, cambiar de juego o finalizar la partida saliendo.
	 */
	private void gestionaOpcion(String orden){
		
			String juego[] = new String[20];
			orden = orden.toLowerCase();
			juego = orden.split(" ");
			
			switch(juego[0])
			{
				case "jugar": 
					if(juego.length > 1)
					{
						if(juego[1].equals("co"))
						{
							factoria = new FactoriaComplica();
						}
						else if(juego[1].equals("c4"))
						{
							factoria = new FactoriaConecta4();
						}
						else if(juego[1].equals("rv"))
						{
							factoria = new FactoriaReversi();
						}
						else if(juego[1].equals("gr") && (juego.length == 4))
						{
							try
							{
							int col= Integer.parseInt(juego[2]);
							int fila= Integer.parseInt(juego[3]);
							factoria = new FactoriaGravity(col,fila);
							}
							catch(NumberFormatException e)
							{
								System.err.println("No te entiendo");
								break;
							}
						}
						else
						{
							System.err.println("No te entiendo.");
							partida.actualizaTablero();
							break;
						}
						System.out.println("Partida reiniciada.");
						this.jugadorBlanca = factoria.creaJugadorHumanoConsola(in);
						this.jugadorNegra = factoria.creaJugadorHumanoConsola(in);
						partida.cambioJuego(factoria.creaReglas());
						this.jugador_actual = jugadorBlanca;
					}
					else
						{
							System.err.println("No te entiendo.");
							partida.actualizaTablero();
						}
						
				break;
				
				case "jugador":
					String color=juego[1];
					String tipo=juego[2];
					opCrear(color,tipo,factoria);
					partida.actualizaTablero();
					
				break;
				case "ayuda":
					ayuda();
				break;
				
				case "poner":
						partida.poner(jugador_actual, factoria);			
				break;
				
				case "deshacer":
					partida.undo();
					
				break;
				
				case "reiniciar":
					partida.reset();
				break;
				
				case "salir":
					partida.setTerminada(true);
				break;
				
				default:
					System.err.println("No te entiendo.");
					partida.actualizaTablero();
			}
	}
	
	
	/*
	 * Método que muestra si un jugador ha ganado o por el contrario si s eha quedado en tablas.
	 */
	
	public void mostrarGanadores()
	{
		if(partida.isTerminada()==true)
		{
			if(partida.getGanador()!=Ficha.VACIA)
			{
				System.out.println(partida.dibujarTablero());
				System.out.println("\nGanan las " + partida.getGanador().toString().toLowerCase()+ "s");
			}
			else
			{
				System.out.println(partida.dibujarTablero());
				System.out.println("\nPartida terminada en tablas.");
			}
			
		}
	}
	
	
	/*
	 * Método de ayuda
	 */
	private void ayuda()
	{
		System.out.println("Los comandos disponibles son:");
		System.out.println("\nPONER: utilízalo para poner la siguiente ficha.");
		System.out.println("DESHACER: deshace el último movimiento hecho en la partida.");
		System.out.println("REINICIAR: reinicia la partida.");
		System.out.println("JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.");
		System.out.println("JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.");
		System.out.println("SALIR: termina la aplicación.");
		System.out.println("AYUDA: muestra esta ayuda.\n");
	}
	
	/*
	 * El método run, se encarga de pedir las instrucciones a relizar, mientras que la
	 * partida no haya finalizado.
	 */
	public void run()
	{
		jugador_actual = jugadorBlanca;
		partida.actualizaTablero();
		while(!partida.isTerminada())
		{
			String orden = in.nextLine();
			gestionaOpcion(orden);
			
		}
		
	}
	
	//En este metodo se crea un jugador aleatorio o humano segun lo pida el usuario, y por ultimo se actualiza 
	//el jugador actual por si se ha creado un jugador en el jugador que tiene el turno.
	private void opCrear(String color, String tipo, FactoriaTipoJuego factoria)
	{
		Jugador j = null;
		
		if(tipo.equals("humano"))
			j=factoria.creaJugadorHumanoConsola(in);
		else if(tipo.equals("aleatorio"))
			j=factoria.creaJugadorAleatorio();
		else
			System.err.println("No te entiendo.");
		
		if(color.equals("blancas"))
			jugadorBlanca=j;
		else if(color.equals("negras"))
			jugadorNegra=j;
		else
			System.err.println("No te entiendo.");
		
		if(partida.getTurno()== Ficha.BLANCA)
			jugador_actual = jugadorBlanca;
		else
			jugador_actual = jugadorNegra;
	}

	
	//este metodo sirve para cambiar de jugador actual, a fin de que cambie el tipo de jugador:aleatorio o humano
	//sobre el que se ejecutara el siguiente movimiento.
	public void cambioJugador()
	{
		if(jugador_actual.equals(jugadorBlanca)){
			jugador_actual = jugadorNegra;
		}
		else
			jugador_actual= jugadorBlanca;

	}
	
	
	public void addObservador(Observador o)
	{
		partida.addObservador(o);
	}
	
}
