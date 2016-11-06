package tp.pr5.logica;

import tp.pr5.GUI.ControladorGUI;
import tp.pr5.control.Hebra;

public class ModoAutomatico implements ModoJuego {

	private ControladorGUI control;
	private Hebra hebra;
	
	public ModoAutomatico(ControladorGUI control, Hebra hebra)
	{
		this.control = control;
		this.hebra = hebra;
	}
	
	public void comenzar() {
		
		hebra = new Hebra(control);
		hebra.start();
	}

	
	public void terminar() {
		
		if(hebra!=null)
		{
			hebra.interrupt();
		}
		
	}

}
