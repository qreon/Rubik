package rubike.src;

import java.awt.Color;
import java.util.Stack;

public class Cube {
	
	private Face[] faces;
	
	//Les carrés et les faces sont numérotés ainsi :
	//		        - - - - -
	//		        | 0 | 1 |        			0
	//		        | 3 | 2 |
	//        		- - - - -
	//		        | 0 | 1 |        			1
	//		        | 3 | 2 |
	//		- - - - - - - - - - - - -
	//		| 0 | 1 | 0 | 1 | 0 | 1 |		4	2	5
	//		| 3 | 2 | 3 | 2 | 3 | 2 |
	//		- - - - - - - - - - - - -
	//		        | 0 | 1 |        			3
	//		        | 3 | 2 |
	//		        - - - - -

	public Cube()
	{
		faces = new Face[6];
		faces[0] = new Face(Color.YELLOW);			//Face arrière du cube (en haut du patron de la GUI)
		faces[1] = new Face(Color.RED);				//Face haute (un peu moins en haut du patron)
		faces[2] = new Face(Color.WHITE);			//Face avant (au centre)
		faces[3] = new Face(Color.ORANGE);			//Face basse (en bas)
		faces[4] = new Face(Color.BLUE);			//Face gauche (à gauche)
		faces[5] = new Face(Color.GREEN);			//Face droite (à droite)
	}
	
	//Renvoie la face i
	public Face get(int i)
	{
		return faces[i];
	}
	
	//Renvoie le square j de la face i
	public Square get(int i, int j)
	{
		return faces[i].get(j);
	}
	
	//Renvoie la couleur du square j de la face i
	public Color getColor(int i, int j)
	{
		return get(i, j).getColor();
	}
	
	private void setColor(int i, int j, Color c)
	{
		get(i, j).setColor(c);
	}
	
	//Étant donné les positions des faces et des carrés,
	//on ne va donc pas tout le temps pouvoir faire
	//les mêmes opérations selon la face que l'on tourne
	
	public void R()			//Rotation de la face droite dans le sens horaire
	{
		faces[5].rotate();
		//On vient de permuter les couleurs des Squares de faces[5]
		//Il faut aussi permuter les couleurs de quelques Squares sur les faces adjacentes
		
		//Permutation des carrés "hauts" des faces adjacentes à la face droite
		Color tmpBa = getColor(0,1);	//Couleur du carré haut de la face arrière
		Color tmpT = getColor(1,1);		//Couleur du carré haut de la face haute
		Color tmpF = getColor(2,1);		//... de la face avant
		Color tmpBo = getColor(3,1);	//... de la face basse
		
		setColor(1,1,tmpF);				//Remplacement du carré face haute par le carré face avant
		setColor(2,1,tmpBo);			//Avant par basse
		setColor(3,1,tmpBa);			//Basse par arrière
		setColor(0,1,tmpT);				//Arrière par haute
		
		//Permutation des carrés "bas"
		tmpT = getColor(1,2);			//Couleur du carré bas de la face haute
		tmpBa = getColor(0,2);			//Couleur du carré bas de la face arrière
		tmpF = getColor(2,2);			//... de la face avant
		tmpBo = getColor(3,2);			//... de la face basse
		
		setColor(1,2,tmpF);				//Remplacement du carré face haute par le carré face avant
		setColor(2,2,tmpBo);			//Avant par basse
		setColor(3,2,tmpBa);			//Basse par arrière
		setColor(0,2,tmpT);				//Arrière par haute
	}
	
	public void F()			//Rotation de la face avant
	{
		faces[2].rotate();
		//On vient de permuter les couleurs des Squares de faces[2]
		//Il faut aussi permuter les couleurs de quelques Squares sur les faces adjacentes
		
		//Permutation des carrés "hauts" des faces adjacentes à la face avant
		Color tmpL = getColor(4,1);		//Couleur du carré haut de la face gauche
		Color tmpT = getColor(1,2);		//Couleur du carré haut de la face haute
		Color tmpR = getColor(5,3);		//... de la face droite
		Color tmpBo = getColor(3,0);	//... de la face basse
		
		setColor(1,2,tmpL);				//Remplacement du carré de la face haute par celui de la face gauche
		setColor(4,1,tmpBo);			//Gauche par basse
		setColor(3,0,tmpR);				//Basse par droite
		setColor(5,3,tmpT);				//Droite par haute
		
		//Permutation des carrés "bas"
		tmpL = getColor(4,2);			//Couleur du carré bas de la face gauche
		tmpT = getColor(1,3);			//Couleur du carré bas de la face haute
		tmpR = getColor(5,0);			//... de la face droite
		tmpBo = getColor(3,1);			//... de la face basse
		
		setColor(1,3,tmpL);				//Remplacement du carré de la face haute par celui de la face gauche
		setColor(4,2,tmpBo);			//Gauche par basse
		setColor(3,1,tmpR);				//Basse par droite
		setColor(5,0,tmpT);				//Droite par haute
	}
	
	public void T()			//Rotation de la face haute
	{
		faces[1].rotate();
		//On vient de permuter les couleurs des Squares de faces[2]
		//Il faut aussi permuter les couleurs de quelques Squares sur les faces adjacentes
		
		//Permutation des carrés "hauts" des faces adjacentes à la face haute
		Color tmpL = getColor(4,1);		//Couleur du carré haut de la face gauche
		Color tmpF = getColor(2,1);		//Couleur du carré haut de la face avant
		Color tmpR = getColor(5,1);		//... de la face droite
		Color tmpBa = getColor(0,3);	//... de la face arrière
		
		setColor(2,1,tmpR);				//Remplacement du carré de la face avant par celui de la face gauche
		setColor(4,1,tmpF);				//Gauche par arrière
		setColor(0,3,tmpL);				//Arrière par droite
		setColor(5,1,tmpBa);			//Droite par avant
		
		//Permutation des carrés "bas"
		tmpL = getColor(4,0);			//Couleur du carré bas de la face gauche
		tmpF = getColor(2,0);			//Couleur du carré bas de la face avant
		tmpR = getColor(5,0);			//... de la face droite
		tmpBa = getColor(0,2);			//... de la face arrière
		
		setColor(2,0,tmpR);				//Remplacement du carré de la face avant par celui de la face gauche
		setColor(4,0,tmpF);				//Gauche par arrière
		setColor(0,2,tmpL);				//Arrière par droite
		setColor(5,0,tmpBa);			//Droite par avant
	}
	
	public void Rr()		//Rotation inverse de la face droite, ou rotation de la face gauche (équivalent)
	{
		//Dans le cas du 2x2x2, 3 mouvements dans un sens = 1 mouvement en sens inverse
		R();
		R();
		R();
	}
	
	public void Fr()		//Rotation inverse de la face avant
	{
		F();
		F();
		F();
	}
	
	public void Tr()		//Rotation inverse de la face haute
	{
		T();
		T();
		T();
	}
	
	public void move (Move m)
	{
		switch(m.name())
		{
			case "R":
				R();
				break;
			case "F":
				F();
				break;
			case "T":
				T();
				break;
			case "R'":
				Rr();
				break;
			case "F'":
				Fr();
				break;
			case "T'":
				Tr();
				break;
		}
	}
}
