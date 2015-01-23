package rubike.src;

import java.awt.Color;

public class Square {
	private Color c;
	
	public Square(Color c)
	{
		this.c = c;
	}
	
	//Renvoie la couleur du carré
	public Color getColor()
	{
		return c;
	}
	
	//Modifie la couleur du carré
	public void setColor(Color c)
	{
		this.c = c;
	}
}
