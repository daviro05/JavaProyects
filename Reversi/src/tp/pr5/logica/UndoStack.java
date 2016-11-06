package tp.pr5.logica;

public class UndoStack 
{
	private int tamano;
	private Movimiento[] undoStack;
	private int numUndo;
	
	public UndoStack() 
	{
		this.tamano = 10;
		this.undoStack = new Movimiento[tamano];
		this.numUndo = 0;
		
	}
	
	public void push(Movimiento movimiento)
	{
		if(numUndo >= tamano)
		{
			redimensionar();
		}
		undoStack[numUndo] = movimiento;
		numUndo++;
		
	}
	
	public Movimiento pop()
	{
		numUndo--;
		return undoStack[numUndo];
	}
	
	public int getNumUndo() {
		return numUndo;
	}

	public void redimensionar()
	{ 
		Movimiento[] aux= new Movimiento[tamano+10];
		for(int i=0; i<tamano;i++){
			aux[i]=undoStack[i];
		}		
		tamano=tamano+10;
		undoStack=aux;
	}

}
