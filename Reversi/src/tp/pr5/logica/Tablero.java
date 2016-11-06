package tp.pr5.logica;

public class Tablero implements TableroInmutable{
	
	private Ficha [][] tab;
	private int ancho;
	private int alto;
	
	
	public Tablero(int an, int al)
	{
		if(an<=0 || al<=0)
		{
			this.alto=1;
			this.ancho=1;
			tab = new Ficha[1][1];
		}
		else
		{
			this.alto=al;
			this.ancho=an;
			tab = new Ficha[an][al];
		}
		reset();
	}
	
	/*
	 * El método reset, reinicia el tablero, dejando todas sus
	 * casillas vacias.
	 */
	public void reset()
	{
		for(int i=0; i<alto;i++)
		{
			for(int j=0;j<ancho;j++)
			{
				tab[j][i] = Ficha.VACIA;
			}
		}
	}
	
	/*
	 * El método toString de esta clase nos devuelve una cadena
	 * con el tablero y su informaci�n actualizada, con las fichas que contiene.
	 * 
	 */
	
	public String toString()
	{
		String cadena ="";
		for(int i=0;i<alto;i++)
		{
			cadena += "|";
			for(int j=0;j<ancho;j++)
			{
				if(tab[j][i] == Ficha.BLANCA)
				{
					cadena += "O";
					
				}
				else
					if(tab[j][i] == Ficha.NEGRA)
				{
					cadena += "X";
				}
					else
					cadena += " ";
			}
			cadena +="|\n";
		}
		cadena+="+";
		for(int k=0;k<ancho;k++)
		{
			cadena+="-";
		}
		cadena+="+\n ";
		for(int l=0;l<ancho;l++)
		{
			cadena+=(l+1)%10+"";
		}
		
		return cadena;
	}
	
	public int getColumnas()
	{
		return ancho;
	}
	
	public int getFilas()
	{
		return alto;
	}
	
	/*
	 * El método getCasilla devolver� una Ficha, que ser� la que
	 * se encuentra en las coordenadas x e y que pasamos como par�metro.
	 * Si x e y no están dentro del rango v�lido del tama�o de tablero
	 * devolver� Ficha.VACIA.
	 */
	
	public Ficha getCasilla(int x, int y)
	{
		Ficha f1_obtenida = Ficha.VACIA;
		if(x<=ancho && y<=alto && x>0 && y>0)
		{
			f1_obtenida = tab[x-1][y-1];
		}

		return f1_obtenida;
	}
	
    /* 
     * El método setCasilla inserta una ficha en el tablero 
     * dependiendo de la posici�n introducida y columna calculada. 
	 */
	
	public void setCasilla(int x, int y, Ficha fich)
	{
		
			if(x<=ancho && y<=alto && x>=1 && y>=1)
			{
				tab[x-1][y-1] = fich;	
			}

	}
		
	
	/*
	 * El método calcularPos, genera la posición en la que será colocada la ficha
	 * (la fila) con la información de la columna pasada como parametro.
	 * Comprobará que fila de la columna es la primera en estar vacia, y 
	 * devolvera esa información, que ser� la posición.
	 */
	
	public int calcularPos(int col)
	{
		boolean poner = false;
		int pos = 0;
		
		if(ancho < col)
		{
			return pos;
		}
			for(int i=alto;i>0;i--)
			{
				if(getCasilla(col, i) == Ficha.VACIA && poner == false)
				{
					pos = i;
					poner = true;
				}
				
			}

		return pos;
	}
	
	// RotarCol se encarga de devolver la ficha que hemos "perdido" anteriormente al 
	// haber añadido una ficha que sobrepasara las dimensiones del tabero por arriba.
	
	public Ficha rotarCol(int col)
	{
		Ficha fich, ficha_perdida=Ficha.VACIA;
		
		for(int i=getFilas();i>0;i--)
		{
			if(i==getFilas())
			{
				ficha_perdida = getCasilla(col, getFilas());
			}
			else
			{	
				fich = getCasilla(col,i);
				setCasilla(col, i+1, fich);
			}
			
		}
		setCasilla(col, 1, Ficha.VACIA);
		
		return ficha_perdida;
	}

	
	/*
	 * El metodo tableroLleno comprobara si el tablero esta completamente lleno, 
	 * utilizando getCasilla de la primera fila de la parte superior del tablero.
	 * Devolvera un true si no se encuentran casillas con fichas vacias en dicha fila.
	 * O un false si encuentra alguna no vacia.
	 */
	
	public boolean tableroLleno()
	{
		boolean lleno = true;
		
			for(int j=1;j<=ancho;j++)
			{
				for (int i=1; i <= alto; i++)
				{		
					if(getCasilla(j,i) == Ficha.VACIA)
					{
						lleno = false;
						return lleno;
					}
				}
			}
		
		return lleno;
	}

}

