package rubike.src;

import java.awt.Color;

public class Face {
	private Square[] sq;
	
	public Face(Color c)
	{
		sq = new Square[4];
		for (int i = 0; i < 4; i++)
		{
			sq[i] = new Square(c);
		}
	}
	
	public Square get(int i)
	{
		return sq[i];
	}
	
	public Color getColor(int i)
	{
		return sq[i].getColor();
	}
	
	//Tourne la face de 90° dans le sens horaire
	//(permute les couleurs des Square)
	//Les square sont numérotés :
	//	0 1
	//	3 2
	public void rotate()
	{
		Color tmp0 = sq[0].getColor();
		Color tmp1 = sq[1].getColor();
		Color tmp2 = sq[2].getColor();
		Color tmp3 = sq[3].getColor();
		
		sq[0].setColor(tmp3);
		sq[1].setColor(tmp0);
		sq[2].setColor(tmp1);
		sq[3].setColor(tmp2);
	}
}
