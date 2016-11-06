package tp.pr5;

import java.util.Scanner;

import org.apache.commons.cli.*;

import tp.pr5.GUI.ControladorGUI;
import tp.pr5.GUI.VistaConsola;
import tp.pr5.GUI.VistaGUI;
import tp.pr5.control.Controlador;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;

/*
 * Clase principal que se encarga de iniciar la partida creando
 * una partida, reiniciandola, un controlador y llamando a los métodos
 * correspondientes para iniciar el juego.
 * 
 *  Se le pueden pasar una serie de parámetros para iniciar distintos juegos con diferentes
 *  carácteristicas.
 */

public class Main 
{
	public static void main(String[] args){
		
		
		FactoriaTipoJuego factoria = new FactoriaConecta4();
		ReglasJuego regla= factoria.creaReglas();
		Partida p1 = new Partida(regla);

				
		String arg_invalidos="";
		String vista="console";
		Options options = new Options();
		
		options.addOption("g","game", true, "Tipo de juego (c4, co, gr, rv). Por defecto, c4.");
		options.addOption("h", "help", false, "Muestra esta ayuda");
		options.addOption("u", "ui", true, "Tipo de interfaz (console, window). Por defecto, console."); 
		options.addOption("x","tamX", true,  "Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
		options.addOption("y","tamY", true,  "Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
		
		CommandLineParser parser = new BasicParser();

		try{
			CommandLine cmd = parser.parse( options, args);
			String juego = cmd.getOptionValue("g");
			
			if(cmd.getArgs().length>0)
			{
				for(int i=0;i<cmd.getArgs().length;i++)
				{
					if(i==(cmd.getArgs().length-1))
					{
						arg_invalidos += cmd.getArgs()[i];
					}
					else
					arg_invalidos += cmd.getArgs()[i]+" ";
				}
			}
						
			if(cmd.hasOption("h")) 
			{
				System.out.println("usage: tp.pr3.Main [-g <game>] [-h] [-u <tipo>] [-x <columnNumber>] [-y <rowNumber>]"+
				"\n -g,--game <game>           Tipo de juego (c4, co, gr, rv). Por defecto, c4."+
				"\n -h,--help                  Muestra esta ayuda."+
				"\n -u,--ui <tipo>             Tipo de interfaz (console, window). Por"+
				"\n							   defecto, console."+
				"\n -x,--tamX <columnNumber>   Número de columnas del tablero (sólo para"+
				"\n                            Gravity). Por defecto, 10."+
				"\n -y,--tamY <rowNumber>      Número de filas del tablero (sólo para"+
				"\n                            Gravity). Por defecto, 10.");
				System.exit(0);
			}
			
			if(cmd.hasOption("g"))
			{
				if(cmd.getArgs().length>0)
				{
					throw new ParseException("Argumentos no entendidos: "+arg_invalidos);
				}
				
				switch(juego)
				{
					case "c4":
						if(cmd.hasOption("x") || cmd.hasOption("y"))
						{
							throw new ParseException("No se permite -x o -y para c4");
						}
						factoria = new FactoriaConecta4();
						
					break;
					
					case "co":
						if(cmd.hasOption("x") || cmd.hasOption("y"))
						{
							throw new ParseException("No se permite -x o -y para co");
						}
						factoria = new FactoriaComplica();
				
						
					break;
					
					case "gr":
						if(!(cmd.hasOption("x") || cmd.hasOption("y")))
						{
							factoria= new FactoriaGravity();
						}
						else
						{
							int x = Integer.parseInt(cmd.getOptionValue("x"));
							int y = Integer.parseInt(cmd.getOptionValue("y"));
							factoria= new FactoriaGravity(x,y);
						}
					break;
					
					case "rv":
						if(cmd.hasOption("x") || cmd.hasOption("y"))
						{
							throw new ParseException("No se permite -x o -y para rv");
						}
						factoria = new FactoriaReversi();
				
						
					break;
					
					default:
						throw new ParseException("Juego '"+ cmd.getOptionValue("g")+ "' incorrecto.");
				}
				
				regla=factoria.creaReglas();
				p1= new Partida(regla);
			}
			
			if(cmd.hasOption("u"))
			{
				vista = cmd.getOptionValue("u");
			}
			
			if(vista.equals("window"))
			{
				ControladorGUI control = new ControladorGUI(factoria, p1);
				new VistaGUI(control);
			}
			else
			{
				Scanner in = new Scanner(System.in);
				Controlador c1 = new Controlador(factoria,p1,in);
				new VistaConsola(c1);
				c1.run();
			}
			
		}
		catch(ParseException exp)
		{
			System.err.println("Uso incorrecto: "+exp.getMessage()+"\nUse -h|--help para más detalles.");
			System.exit(1);
		}
		catch(NumberFormatException exp)
		{
			System.err.println("Uso incorrecto: "+exp.getMessage()+"\nUse -h|--help para más detalles.");
			System.exit(1);
		}
	}
}
