
public class Card 
{

	//Variables Kept In
	int cardNumber;
	String cardSuit;
	
	/*
	 * Name: Default Constructor
	 * Description: This constructor is used to create a card object with a unique set of values
	 * */
	public Card (int cardNumber, String cardSuit) 
	{
		//Updating Global Card Number
		this.cardNumber = cardNumber;
		
		//Updating Global Card Suit
		this.cardSuit = cardSuit;
	}
}
