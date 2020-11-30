
public class ObjectCreator 
{
	SpoonsGUI spoons;
	Crazy8sGUI crazy8s;
	Poker poker;
	PresidentGUI president;
	
	ObjectCreator()
	{
		try 
		{
			spoons = new SpoonsGUI() ;
			crazy8s = new Crazy8sGUI();
			poker = new Poker();
			president = new PresidentGUI();
		}
		catch(Exception e) 
		{
			
		}

	}

}
