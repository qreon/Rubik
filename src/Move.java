package rubike.src;

public class Move {
	private String m;
	private boolean reverse;
	
	public Move(String m)
	{
		this.m = m;
		
		if(m.equals("R'") || m.equals("F'") || m.equals("T'"))
		{
			reverse = true;
		}
		else
		{
			reverse = false;
		}
	}
	
	public String name()
	{
		return m;
	}
	
	public Move reverse()
	{
		return new Move(reverseName());
	}
	
	public String reverseName()
	{
		String res = "";
		
		switch(m)
		{
			case "T":
				res = "T'";
				break;
			case "F":
				res = "F'";
				break;
			case "T'":
				res = "T";
				break;
			case "F'":
				res = "F";
				break;
			case "R":
				res = "R'";
				break;
			case "R'":
				res = "R";
				break;
		}
		
		return res;
	}
}
