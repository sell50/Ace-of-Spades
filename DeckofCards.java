import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

import javafx.scene.image.Image;

public class DeckofCards 
{

	//Objects Kept In
	InputStream stream;
	InputStream cardBackStream;
	Image cardFront;
	Image cardBack; 
			
	//Variables Kept In
	Card card, tempCard;
	int[] cardNumbers = {2,3,4,5,6,7,8,9,10,11,12,13,14};
	String[] cardSuits = {"H","S","C","D"};
	int cardID=0;
	int shuffleNum;
	String PicturePath = "C:\\Users\\Brett\\eclipse-workspace\\Ace of Spades\\Cards\\PNG\\";
	
	//Variables Sent Out
	Card[] Deck = new Card[52];
	
	/*
	 * Name: Default Constructor
	 * Type:Void
	 * Description: Shuffles the populated deck
	 */
	public DeckofCards()
	{
		ShuffleDeck(PopulateDeck());
	}
	
	/*
	 * Name: Deck Populate Function
	 * Type: Card[]
	 * Description: This function is used to generate a standard deck of 52 unique cards
	 * 				Each card object is then stored in the Deck[] which is returned by the function
	 */
	private Card[] PopulateDeck()
	{
		try 
		{
			//Setting CardBack Image
			cardBack = new Image(cardBackStream = new FileInputStream(PicturePath+"CardBack.png"));
			
			//4 Suits
			for(int i=0;i<cardSuits.length;i++) 
			{
				//13 Numbers
				for(int j=0;j<cardNumbers.length;j++) 
				{
					
					stream = new FileInputStream(PicturePath+cardNumbers[j]+cardSuits[i]+".png");
					cardFront = new Image(stream);
					
					//Creating Card Objects 
					card = new Card(cardNumbers[j],cardSuits[i],cardFront,cardBack);
					
					//Adding Cards to Deck
					Deck[cardID]=card;
					
					//Increasing the Card Placement in the Deck
					cardID++;
				}
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		//Returning Deck of 52
		return Deck;
	}
	
	/*
	 * Name: Deck Display Function
	 * Type: Void
	 * Description: This function is used to display Deck[] (For Testing Purposes)
	 */
	public void DisplayDeck(Card[] Deck) 
	{
		//Go Through Each Card
		for(int i=0;i<Deck.length;i++) 
		{
			//Print Current Card
			System.out.println(Deck[i].cardNumber+" of "+Deck[i].cardSuit);
		}
	}
	
	/*
	 * Name: Deck Shuffle Function
	 * Type: Card[]
	 * Description: This function is used to shuffle the populated Deck[]
	 */
	public Card[] ShuffleDeck(Card[] Deck) 
	{
		//Creating Instance of Random Object
		Random random = new Random();
		
		//Loop From Deck Placement 0 to Deck Placement 51
		for(int i=0;i<Deck.length;i++)
		{
			//Assigning a Random int to shuffleNum
			shuffleNum = i + random.nextInt(52 - i);
			
			//Basically Bubble Sort
			tempCard = Deck[shuffleNum];
			Deck[shuffleNum] = Deck[i];
			Deck[i] = tempCard;
		}
		
		//Returns Shuffled Deck
		return Deck;
	}
}
