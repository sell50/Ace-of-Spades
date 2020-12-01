import java.util.Random;
/*
 * Removed redundant methods and variables to change how program interacts with GUI
 * -Removed updatePlayerHand()
 * -Removed gameFlag
 * -Changed method checkPlayerRank
 * -Changed way ai players pick cards, instead they now pick a random index and the program checks if the card at that index was already picked if so then it loops through until a valid card is chosen. If the choice is skipped then the card stays and is not picked. 0 = card was not picked, 1 = card was picked
 * -Changed method checkforNewPile() method now instead creates a new pile after 4 rounds, but still creates a new pile if the top card is an ace (ace = 14)
 */
public class President
{
	//variables initialized in the constructor
	DeckofCards deck;
	Player player;
	Player aiPlayer1;
	Player aiPlayer2;
	Player aiPlayer3;
	Card[] pileDeck;
	int cardsDrawn;
	//needed variables
	int playerChoice;
	String[] socialRank = {"player", "aiPlayer1", "aiPlayer2", "aiPlayer3"};
	int socialRankIndex = 0;
	int aiPlayerChoice;
	int newPileIndex = 0;
	int[] cardsPicked = new int[13];
	

	President() //call to constructor starts the game
	{
		this.deck = new DeckofCards();
		Card tempCard = new Card(2, "Hearts");
		this.pileDeck = new Card[52];
		this.pileDeck[0] = tempCard;
		deck.ShuffleDeck(deck.Deck);
		this.player = new Player(13);
		this.aiPlayer1 = new Player(13);
		this.aiPlayer2 = new Player(13);
		this.aiPlayer3 = new Player(13);
		this.cardsDrawn = 0;
		for(int i = 0; i < cardsPicked.length; i++)
			cardsPicked[i] = 0;

		dealCards(this.cardsDrawn, this.deck, this.player.playerHand);	 //deals the cards to all the players
		dealCards(this.cardsDrawn, this.deck, this.aiPlayer1.playerHand);
		dealCards(this.cardsDrawn, this.deck, this.aiPlayer2.playerHand);
		dealCards(this.cardsDrawn, this.deck, this.aiPlayer3.playerHand);
		
		switch2Cards(); //call to methods needed to switch necessary cards
		switch1Card();
		
		socialRank = new String[4]; //resets the social rank between players
	}
	
	public void gameSession()
	{
			if(checkforNewPile()) //creates a new pile of cards if current one cannot be used anymore
			{
				Card tempCard = new Card(2, "Hearts"); //sets the new top card to the lowest valued card
				this.pileDeck = new Card[52];
				this.pileDeck[0] = tempCard;
			}
	}
	
	public void checkPlayerRank(String rank) //assigns player rank based on gameRounds. The later the game the lower the position the player gets 
	{
		if(socialRankIndex == 0) //if the player name passed through is first they are the president
			socialRank[socialRankIndex] = rank;
		else
		{
			for(int i = 0; i < socialRankIndex; i++) //else first check to see if the player name passed through is already within the string array
			{
				if(rank == socialRank[i]) //if it's already in the array exit the method
					return;
				else
					socialRank[socialRankIndex] = rank; //else add the player name at the next rank index
			}
		}
		
		this.socialRankIndex++; //increment the index
	}
	
	public void dealCards(int cardsDrawn, DeckofCards deck, PlayerHand hand) //gives players 13 cards
	{
		
		for(int i = 0; i < 13; i++)
		{
			hand.Hand[i] = deck.Deck[cardsDrawn];
			cardsDrawn++;
		}
		
		this.cardsDrawn = cardsDrawn;
		
	}
	
	public void currentPile(Card cardPlayed) //creates a pile of cards so we can keep track of the current card at the top
	{
		pileDeck[0] = cardPlayed;
	}
	
	public void playerTurn() //real players turn where they determine what card to play
	{
		Card tempCard = this.player.playerHand.Hand[playerChoice];
		
		if(this.player.playerHand.Hand[playerChoice].cardNumber >= pileDeck[0].cardNumber)
			currentPile(tempCard);
		else
		{
			return;
		}
		
	}
	
	public void aiPlayerTurn(Player aiPlayer) //this is where the AI player determines what their move will be. If they play an invalid card it skips their turn
	{
		Random rand = new Random();
		Card tempCard;
		
		while(true)
		{
			this.aiPlayerChoice = rand.nextInt(13);
			if(cardsPicked[aiPlayerChoice] == 0)
				if(aiPlayer.playerHand.Hand[aiPlayerChoice].cardNumber < this.pileDeck[0].cardNumber)
				{
					return;
				}
				else
				{
					cardsPicked[aiPlayerChoice] = 1;
					tempCard = aiPlayer.playerHand.Hand[aiPlayerChoice];
					currentPile(tempCard);
					return;
				}
			else
				continue;
		}
	}
	
	public boolean checkforNewPile() //looks to see if any players have any playable cards and if not creates a new deck or if the top card is a 14 (ace) then it creates a new pile
	{
		if(this.pileDeck[0].cardNumber == 14) //if the top card is an ace create a new pile
		{
			newPileIndex = 0;
			return true;
		}
		if(newPileIndex == 4) //if 4 rounds have passed create a new pile
		{
			newPileIndex = 0;
			return true;
		}
		else //else keep the pile increment the index
		{
			newPileIndex++;
			return false;
		}
	}
		
	public void switch2Cards() //switches the 2 best and worst cards between the president and the Scum
	{
		Card bestCard = null;
		Card worstCard = null;
		Card tempCard;
		
		int length = this.player.playerHand.Hand.length;
		for(int count = 0; count < 2; count++)
		{
		if(socialRank[0] == "player")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.player.playerHand.Hand[j].cardNumber > this.player.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.player.playerHand.Hand[j];
						this.player.playerHand.Hand[j] = this.player.playerHand.Hand[j + 1];
						this.player.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.player.playerHand.Hand[count];
		}
		if(socialRank[0] == "aiPlayer1")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer1.playerHand.Hand[j].cardNumber > this.aiPlayer1.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer1.playerHand.Hand[j];
						this.aiPlayer1.playerHand.Hand[j] = this.aiPlayer1.playerHand.Hand[j + 1];
						this.aiPlayer1.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.aiPlayer1.playerHand.Hand[count];
		}
		if(socialRank[0] == "aiPlayer2")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer2.playerHand.Hand[j].cardNumber > this.aiPlayer2.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer2.playerHand.Hand[j];
						this.aiPlayer2.playerHand.Hand[j] = this.aiPlayer2.playerHand.Hand[j + 1];
						this.aiPlayer2.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.aiPlayer2.playerHand.Hand[count];
		}
		if(socialRank[0] == "aiPlayer3")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer3.playerHand.Hand[j].cardNumber > this.aiPlayer3.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer1.playerHand.Hand[j];
						this.aiPlayer3.playerHand.Hand[j] = this.aiPlayer3.playerHand.Hand[j + 1];
						this.aiPlayer3.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.aiPlayer3.playerHand.Hand[count];
		}
		
		if(socialRank[3] == "player")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.player.playerHand.Hand[j].cardNumber < this.player.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.player.playerHand.Hand[j + 1];
						this.player.playerHand.Hand[j + 1] = this.player.playerHand.Hand[j];
						this.player.playerHand.Hand[j] = tempCard;
					}
			bestCard = this.player.playerHand.Hand[count];
		}
		if(socialRank[3] == "aiPlayer1")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer1.playerHand.Hand[j].cardNumber < this.aiPlayer1.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer1.playerHand.Hand[j + 1];
						this.aiPlayer1.playerHand.Hand[j + 1] = this.aiPlayer1.playerHand.Hand[j];
						this.aiPlayer1.playerHand.Hand[j] = tempCard;
					}
			bestCard = this.aiPlayer1.playerHand.Hand[count];
		}
		if(socialRank[3] == "aiPlayer2")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer2.playerHand.Hand[j].cardNumber < this.aiPlayer2.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer2.playerHand.Hand[j + 1];
						this.aiPlayer2.playerHand.Hand[j + 1] = this.aiPlayer2.playerHand.Hand[j];
						this.aiPlayer2.playerHand.Hand[j] = tempCard;
					}
			bestCard = this.aiPlayer2.playerHand.Hand[count];
		}
		if(socialRank[3] == "aiPlayer3")
		{
			for(int i = 0; i < length - 1; i++)
			{
				for(int j = 0; j < length - i - 1; j++)
				{
					if(this.aiPlayer3.playerHand.Hand[j].cardNumber < this.aiPlayer3.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer3.playerHand.Hand[j + 1];
						this.aiPlayer3.playerHand.Hand[j + 1] = this.aiPlayer3.playerHand.Hand[j];
						this.aiPlayer3.playerHand.Hand[j] = tempCard;
					}
				}
			}
			bestCard = this.aiPlayer3.playerHand.Hand[count];
		}
		
		if(socialRank[0] == "player")
		{
			this.player.playerHand.Hand[0] = bestCard;
		}
		if(socialRank[0] == "aiPlayer1")
		{
			this.aiPlayer1.playerHand.Hand[0] = bestCard;
		}
		if(socialRank[0] == "aiPlayer2")
		{
			this.aiPlayer2.playerHand.Hand[0] = bestCard;
		}
		if(socialRank[0] == "aiPlayer3")
		{
			this.aiPlayer3.playerHand.Hand[0] = bestCard;
		}
		
		if(socialRank[3] == "player")
		{
			this.player.playerHand.Hand[0] = worstCard;
		}
		if(socialRank[3] == "aiPlayer1")
		{
			this.aiPlayer1.playerHand.Hand[0] = worstCard;
		}
		if(socialRank[3] == "aiPlayer2")
		{
			this.aiPlayer2.playerHand.Hand[0] = worstCard;
		}
		if(socialRank[3] == "aiPlayer3")
		{
			this.aiPlayer3.playerHand.Hand[0] = worstCard;
		}
		}
	}
	
	public void switch1Card() //switches the best and worst card between the Vice-President and the Civilian
	{
		Card bestCard = null;
		Card worstCard = null;
		Card tempCard;
		
		int length = this.player.playerHand.Hand.length;
		if(socialRank[1] == "player")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.player.playerHand.Hand[j].cardNumber > this.player.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.player.playerHand.Hand[j];
						this.player.playerHand.Hand[j] = this.player.playerHand.Hand[j + 1];
						this.player.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.player.playerHand.Hand[0];
		}
		if(socialRank[1] == "aiPlayer1")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer1.playerHand.Hand[j].cardNumber > this.aiPlayer1.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer1.playerHand.Hand[j];
						this.aiPlayer1.playerHand.Hand[j] = this.aiPlayer1.playerHand.Hand[j + 1];
						this.aiPlayer1.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.aiPlayer1.playerHand.Hand[0];
		}
		if(socialRank[1] == "aiPlayer2")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer2.playerHand.Hand[j].cardNumber > this.aiPlayer2.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer2.playerHand.Hand[j];
						this.aiPlayer2.playerHand.Hand[j] = this.aiPlayer2.playerHand.Hand[j + 1];
						this.aiPlayer2.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.aiPlayer2.playerHand.Hand[0];
		}
		if(socialRank[1] == "aiPlayer3")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer3.playerHand.Hand[j].cardNumber > this.aiPlayer3.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer3.playerHand.Hand[j];
						this.aiPlayer3.playerHand.Hand[j] = this.aiPlayer3.playerHand.Hand[j + 1];
						this.aiPlayer3.playerHand.Hand[j + 1] = tempCard;
					}
			worstCard = this.aiPlayer3.playerHand.Hand[0];
		}
		
		if(socialRank[2] == "player")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.player.playerHand.Hand[j].cardNumber < this.player.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.player.playerHand.Hand[j + 1];
						this.player.playerHand.Hand[j + 1] = this.player.playerHand.Hand[j];
						this.player.playerHand.Hand[j] = tempCard;
					}
			bestCard = this.player.playerHand.Hand[0];
		}
		if(socialRank[2] == "aiPlayer1")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j < length - i - 1; j++)
					if(this.aiPlayer1.playerHand.Hand[j].cardNumber < this.aiPlayer1.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer1.playerHand.Hand[j + 1];
						this.aiPlayer1.playerHand.Hand[j + 1] = this.aiPlayer1.playerHand.Hand[j];
						this.aiPlayer1.playerHand.Hand[j] = tempCard;
					}
			bestCard = this.aiPlayer1.playerHand.Hand[0];
		}
		if(socialRank[2] == "aiPlayer2")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j > length - i - 1; j++)
					if(this.aiPlayer2.playerHand.Hand[j].cardNumber < this.aiPlayer2.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer2.playerHand.Hand[j + 1];
						this.aiPlayer2.playerHand.Hand[j + 1] = this.aiPlayer2.playerHand.Hand[j];
						this.aiPlayer2.playerHand.Hand[j] = tempCard;
					}
			bestCard = this.aiPlayer2.playerHand.Hand[0];
		}
		if(socialRank[2] == "aiPlayer3")
		{
			for(int i = 0; i < length - 1; i++)
				for(int j = 0; j > length - i - 1; j++)
					if(this.aiPlayer3.playerHand.Hand[j].cardNumber < this.aiPlayer3.playerHand.Hand[j + 1].cardNumber)
					{
						tempCard = this.aiPlayer3.playerHand.Hand[j + 1];
						this.aiPlayer3.playerHand.Hand[j + 1] = this.aiPlayer3.playerHand.Hand[j];
						this.aiPlayer3.playerHand.Hand[j] = tempCard;
					}
			bestCard = this.aiPlayer3.playerHand.Hand[0];
		}
		
		if(socialRank[1] == "player")
		{
			this.player.playerHand.Hand[0] = bestCard;
		}
		if(socialRank[1] == "aiPlayer1")
		{
			this.aiPlayer1.playerHand.Hand[0] = bestCard;
		}
		if(socialRank[1] == "aiPlayer2")
		{
			this.aiPlayer2.playerHand.Hand[0] = bestCard;
		}
		if(socialRank[1] == "aiPlayer3")
		{
			this.aiPlayer3.playerHand.Hand[0] = bestCard;
		}
		
		if(socialRank[2] == "player")
		{
			this.player.playerHand.Hand[0] = worstCard;
		}
		if(socialRank[2] == "aiPlayer1")
		{
			this.aiPlayer1.playerHand.Hand[0] = worstCard;
		}
		if(socialRank[2] == "aiPlayer2")
		{
			this.aiPlayer2.playerHand.Hand[0] = worstCard;
		}
		if(socialRank[2] == "aiPlayer3")
		{
			this.aiPlayer3.playerHand.Hand[0] = worstCard;
		}
	
	}
	
}