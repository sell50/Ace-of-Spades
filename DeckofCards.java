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
	 * Description: Shuffles the populated deck
	 */
	public DeckofCards()
	{
		ShuffleDeck(PopulateDeck());
		System.out.println("\n*-----------------------------------*\n");
	}
	
	/*
	 * Name: Deck Populate Function
	 * Type: Card[]
	 * Description: This function is used to generate a standard deck of 52 unique cards
	 * 				Each card object is then stored in the Deck[] which is returned by the function
	 */
	private Card[] PopulateDeck() 
	{
		//4 Suits
		for(int i=0;i<cardSuits.length;i++) 
		{
			//13 Numbers
			for(int j=0;j<cardNumbers.length;j++) 
			{
				//Creating Card Objects 
				card = new Card(cardNumbers[j],cardSuits[i]);
				
				//Adding Cards to Deck
				Deck[cardID]=card;
				
				//Increasing the Card Placement in the Deck
				cardID++;
			}
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
