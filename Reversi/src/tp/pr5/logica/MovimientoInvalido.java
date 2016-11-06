package tp.pr5.logica;


public class MovimientoInvalido extends Exception
{
	public MovimientoInvalido(){}
	
	// Constructor donde se le pasa un mensaje como argumento que será el que se muestre
	// cuando se lance la excepcion.
	 
	MovimientoInvalido(java.lang.String msg)
	{
		super(msg);
	}
	 
	MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg){
	}
	 
	MovimientoInvalido(java.lang.Throwable arg){	
	}
}
