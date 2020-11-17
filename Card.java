import javafx.scene.image.Image;


public class Card 
{

	//Variables Kept In
	int cardNumber;
	String cardSuit;
	Image cardFront,cardBack;
	
	/*
	 * Name: Default Constructor
	 * Description: This constructor is used to create a card object with a unique set of values
	 * */
	public Card (int cardNumber, String cardSuit, Image cardFront, Image cardBack) 
	{
		//Updating Global Card Number
		this.cardNumber = cardNumber;
		
		//Updating Global Card Suit
		this.cardSuit = cardSuit;
		
		//Updating Global Card Front
		this.cardFront = cardFront;
		
		//Updating Global Card Back
		this.cardBack = cardBack;
	}
}
