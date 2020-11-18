public class PlayerHand 
{
	
	//Needed Variables 
	Card[] Hand;
	int xBase = 10;
	int yBase = 10;
	
	/*
	 * Name: Player Hand of Cards Function
	 * Type: Constructor 
	 * Description: This function takes in the amount of cards the player will be
	 * 				Dealt to create the "Hand" of the player
	 */
	public PlayerHand(int cardsForHand)
	{
		Hand = new Card[cardsForHand];
	}
}
