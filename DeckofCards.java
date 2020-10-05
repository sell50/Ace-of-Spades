import java.util.Random;

public class DeckofCards {

	//Variables Kept In
	Card card, tempCard;
	int[] cardNumbers = {2,3,4,5,6,7,8,9,10,11,12,13,14};
	String[] cardSuits = {"Hearts","Spades","Clubs","Diamonds"};
	int cardID=0;
	int shuffleNum;

	//Variables Sent Out
	Card[] Deck = new Card[52];
	
	/*
	 * Name: Default Constructor
	 * Description:
	 */
	public DeckofCards()
	{
		DisplayDeck(ShuffleDeck(PopulateDeck()));
	}
	
	/*
	 * Name: Deck Populate Function
	 * Type: Card[]
	 * Description: This function is used to generate a standard deck of 52 unique cards
	 * 				Each card object is then stored in the Deck[] which is returned by the function
	 */
	private Card[] PopulateDeck() 
	{
		for(int i=0;i<cardSuits.length;i++) 
		{
			for(int j=0;j<cardNumbers.length;j++) 
			{
				card = new Card(cardNumbers[j],cardSuits[i]);
				Deck[cardID]=card;
				cardID++;
			}
		}
		return Deck;
	}
	
	/*
	 * Name: Deck Display Function
	 * Type: Void
	 * Description: This function is used to display Deck[] to the User
	 */
	public void DisplayDeck(Card[] Deck) 
	{
		for(int i=0;i<Deck.length;i++) 
		{
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
		Random random = new Random();
		
		for(int i=0;i<Deck.length;i++)
		{
			shuffleNum = i + random.nextInt(52 - i);
			
			tempCard = Deck[shuffleNum];
			Deck[shuffleNum] = Deck[i];
			Deck[i] = tempCard;
		}
		
		return Deck;
	}
	
	/*
	 * Name: Main
	 * Type: Void
	 */
	public static void main(String[] args)
	{
		DeckofCards deck1 = new DeckofCards();
	}
}
