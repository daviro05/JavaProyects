package tp.pr5.control;

import tp.pr5.GUI.ControladorGUI;
import tp.pr5.logica.MovimientoInvalido;


public class Hebra extends Thread implements Runnable{
	
	ControladorGUI control;
	
	public Hebra(ControladorGUI control)
	{
		this.control = control;
	}

	public void run() {
		
		try {
			sleep(2000);
			control.aleatorio();
			
		} catch (InterruptedException e1) {
		
		} catch (MovimientoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
