
public abstract class Player
{
	private String name;
	public boolean state;
	public int votes=0;
	
	public Player(String name)
	{
		this.name=name;
		this.state=true;
	}
	public String getName()
	{
		return name;
	}
	public String vote(String votee)
	{
		return votee;
	}
}