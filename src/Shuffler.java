package rubike.src;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Shuffler {
	private LinkedBlockingQueue<Move> mv;	//Mouvements utilisé pour mélanger le cube
	private Cube c;							//Cube à mélanger
	private Set<Move> possible;				//Mouvements disponibles au tirage
	private Move lastMove;					//Dernier mouvement tiré
	private Move secToLastMove;				//Av-dernier mouvement tiré
	
	public Shuffler(Cube c)
	{
		this.c = c;
		mv = new LinkedBlockingQueue<Move>();
		possible = new HashSet<>();
	}
	
	public void shuffle(int n)	//Mélange le rubick's cube avec n mouvements aléatoires
	{
		//Mouvements types indésirables :
		//	- R R'  (car ils s'annulent)
		//	- R R R (car c'est équivalent à R')
				
		for (int i = 0; i < n; i++)
		{
			buildPossibleDir();
			Move tmp = new Move(pickDir());
			c.move(tmp);
			mv.add(tmp);
			secToLastMove = lastMove;
			lastMove = tmp;
		}
		
		
	}
	
	private void buildPossibleDir()
	{
		possible.clear();

		possible.add(new Move("R"));
		possible.add(new Move("R'"));
		possible.add(new Move("T"));
		possible.add(new Move("T'"));
		possible.add(new Move("F"));
		possible.add(new Move("F'"));
		
		if (secToLastMove != null)
		{
			//Si on a un avant dernier mouvement,
			//on a également un dernier mouvement
			//Si ce sont les mêmes, on veut empêcher que ce
			//mouvement soit tiré une troisième fois de suite
			if(secToLastMove.equals(lastMove))
			{
				possible.remove(lastMove);
				//On retire des possibilités le dernier mouvement
			}
		}
		if (lastMove != null)
		{
			//De plus on veut empêcher de tiré l'inverse du mouvement précédent
			possible.remove(new Move(lastMove.reverseName()));
			//On le retire des possibilités
		}
	}
	
	private String pickDir()
	{
		int r = (int) (Math.floor(Math.random()*possible.size()));
		Move[] tab = new Move[possible.size()];
		possible.toArray(tab);
		return tab[r].name();
	}
	
	public Stack<Move> getMoves()
	{
		Stack<Move> st = new Stack<>();
		for(Move e : mv)
		{
			st.add(new Move(e.reverseName()));
		}
		return st;
	}
	
	public Cube getCube()
	{
		return c;
	}
}
