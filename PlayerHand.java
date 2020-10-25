
public class PlayerHand {
	
	//Needed Variables 
	Card[] Hand;
	
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
	
	/*
	 * Name: Player Hand Display Function
	 * Type: void 
	 * Description: This Function Displays all the Current Cards in the Player's Hand
	 */
	public void DisplayHand()
	{
		//Loop for Each Card in Hand
		for(int i = 0; i < Hand.length; i++)
		{
			System.out.println(Hand[i].cardNumber+" of "+Hand[i].cardSuit);
		}
	}
}
