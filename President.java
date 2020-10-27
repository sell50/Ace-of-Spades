
import java.util.Scanner;

public class President 
{
	//variables initialized in the constructor
	DeckofCards deck;
	Player player;
	Scanner input;
	Player aiPlayer1;
	Player aiPlayer2;
	Player aiPlayer3;
	Card[] pileDeck;
	int cardsDrawn;
	//needed variables
	int playerChoice;
	int index = 0;
	String[] socialRank = {"player", "aiPlayer1", "aiPlayer2", "aiPlayer3"};
	boolean gameFlag;
	int gameRounds;
	
	President() //call to constructor starts the game
	{
		this.pileDeck = new Card[16];
		this.deck = new DeckofCards();
		deck.ShuffleDeck(deck.Deck);
		this.player = new Player(13);
		this.aiPlayer1 = new Player(13);
		this.aiPlayer2 = new Player(13);
		this.aiPlayer3 = new Player(13);
		this.input = new Scanner(System.in);
		gameFlag = true;
		gameRounds = 0;

		
		System.out.println("\tThe President is " + socialRank[0] + "\n\tThe Vice-President is " + socialRank[1] + "\n\tThe Civilian is " + socialRank[2] + "\n\tThe Scum is " + socialRank[3]);		
		System.out.println("\tThe game is President and you will play for 3 rounds...ROUND 1 GO!!!");
		startGame(); //calls method startGame
		checkPlayerRank();
		
		System.out.println("\tThe President is " + socialRank[0] + "\n\tThe Vice-President is " + socialRank[1] + "\n\tThe Civilian is " + socialRank[2] + "\n\tThe Scum is " + socialRank[3]);		
		
		this.pileDeck = new Card[16];
		this.deck = new DeckofCards();
		deck.ShuffleDeck(deck.Deck);
		this.player = new Player(13);
		this.aiPlayer1 = new Player(13);
		this.aiPlayer2 = new Player(13);
		this.aiPlayer3 = new Player(13);
		gameFlag = true;
		gameRounds = 0;
		
		System.out.println("\n\tROUND 2 GO!!!");
		
		startGame();
		checkPlayerRank();
		
		this.pileDeck = new Card[16];
		this.deck = new DeckofCards();
		deck.ShuffleDeck(deck.Deck);
		this.player = new Player(13);
		this.aiPlayer1 = new Player(13);
		this.aiPlayer2 = new Player(13);
		this.aiPlayer3 = new Player(13);
		gameFlag = true;
		gameRounds = 0;
		
		System.out.println("\tThe President is " + socialRank[0] + "\n\tThe Vice-President is " + socialRank[1] + "\n\tThe Civilian is " + socialRank[2] + "\n\tThe Scum is " + socialRank[3]);
		System.out.println("\n\tROUND 3 GO!!!"); //ends after 3 rounds
		startGame(); 
		checkPlayerRank();
		System.out.println("\tThe President is " + socialRank[0] + "\n\tThe Vice-President is " + socialRank[1] + "\n\tThe Civilian is " + socialRank[2] + "\n\tThe Scum is " + socialRank[3]);

		System.out.println("\n\tThanks for playing! GG!");
	}
	
	public void startGame() //starts the game and starts by switching the cards of the President & Scum, and the Vice-President & Civilian
	{
		this.cardsDrawn = 0;
		System.out.println("\tPRESIDENT! First we deal the cards\n");
		
		dealCards(this.cardsDrawn, this.deck, this.player.playerHand);	 //deals the cards to all the players
		playerAICards(aiPlayer1);
		playerAICards(aiPlayer2);
		playerAICards(aiPlayer3);
		System.out.println("\tNow the Scum will give their best 2 cars to the President, and the President will give their 2 worst cards to the Scum.\n\tThen the Civilian will give their best card to the Vice-President, and the VIce-President will give their worst card to the Civilian.\n");
		switch2Cards();
		switch1Card();
		
		System.out.println("\tNow the President will go first\n");
		
		gameSession(); 
	}
	
	public void gameSession()
	{
		if(gameFlag) //if gameFlag is true
		{
			gameRounds++; //increment gameRounds
			
			for(int i = 0; i < 4; i++) //i = 0 is president to i = 4 which is scum which goes through to check what social rank each player is
			{
				if(socialRank[i] == "player")
				{
					
					if(this.player.playerHand.Hand.length == 0)
						continue;
					else
						playerTurn();
				}
				if(socialRank[i] == "aiPlayer1")
				{
					if(this.aiPlayer1.playerHand.Hand.length == 0)
						continue;
					else
						this.aiPlayer1 = aiPlayerTurn(this.aiPlayer1);
				}
				if(socialRank[i] == "aiPlayer2")
				{
					if(this.aiPlayer2.playerHand.Hand.length == 0)
						continue;
					else
						this.aiPlayer2 = aiPlayerTurn(this.aiPlayer2);
				}
				if(socialRank[i] == "aiPlayer3")
				{
					if(this.aiPlayer3.playerHand.Hand.length == 0)
						continue;
					else
						this.aiPlayer3 = aiPlayerTurn(this.aiPlayer3);
				}
			}
		
			if(checkforNewPile()) //creates a new pile of cards if current one cannot be used anymore
			{
				System.out.println("\tNo one else can play a card...New pile!\n");
				this.pileDeck = new Card[52];
				this.index = 0;
			}
			
			if(this.player.playerHand.Hand.length == 0) //if player hand is empty set gameFlag to false
				gameFlag = false;
			
			
			
			gameSession(); //recursion method to keep the game going until the roles are filled
		}
		else //if gameFlag is false return 
		{
			return;
		}
			
	}
	
	public void checkPlayerRank() //assigns player rank based on gameRounds. The later the game the lower the position the player gets 
	{
		if(gameRounds < 15)
		{
			socialRank[0] = "player";
			socialRank[1] = "aiPlayer1";
			socialRank[2] = "aiPlayer2";
			socialRank[3] = "aiPlayer3";
		}
		if(gameRounds >= 15 && gameRounds < 30)
		{
			socialRank[1] = "player";
			socialRank[0] = "aiPlayer1";
			socialRank[2] = "aiPlayer2";
			socialRank[3] = "aiPlayer3";
		}
		if(gameRounds >= 30 && gameRounds < 45)
		{
			socialRank[2] = "player";
			socialRank[0] = "aiPlayer1";
			socialRank[1] = "aiPlayer2";
			socialRank[3] = "aiPlayer3";
		}
		if(gameRounds >= 45 && (gameRounds < 60 || gameRounds > 60))
		{
			socialRank[3] = "player";
			socialRank[0] = "aiPlayer1";
			socialRank[1] = "aiPlayer2";
			socialRank[2] = "aiPlayer3";
		}
	}
	
	public void playerAICards(Player aiPlayer) //gets AI player cards
	{
		dealCards(this.cardsDrawn, this.deck, aiPlayer.playerHand);
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
	
	public Player updatePlayerHand(int cardPlayed, Player player) //updates the players hand when they play a card
	{
		int length = player.playerHand.Hand.length;
		
		Player tempHand = new Player(length - 1);
		
		for(int i = 0, j = 0; i < length; i++)
		{
			if(i == cardPlayed)
				continue;
			else 
			{
				tempHand.playerHand.Hand[j] = player.playerHand.Hand[i];
				j++;
			}
		}
		player = new Player(tempHand.playerHand.Hand.length);
		
		for(int i = 0; i < tempHand.playerHand.Hand.length; i++)
			player.playerHand.Hand[i] = tempHand.playerHand.Hand[i];
		
		return player;
		
	}
	
	public void currentPile(int cardnumber, Card cardPlayed) //creates a pile of cards so we can keep track of the current card at the top
	{
		int index = 0;
		pileDeck[index] = cardPlayed;
		System.out.println("\tCurrent top card is " + pileDeck[index].cardNumber + " of " + pileDeck[index].cardSuit);
		this.index += index++;
	}
	
	public void playerTurn() //real playeres turn where they determine what card to play
	{
		Card tempCard;
		this.player.playerHand.DisplayHand();
		System.out.printf("\tWhich card would you like to play? (Enter a number for between 1-13 for the card you would like to put in): ");
		playerChoice = input.nextInt() - 1;
		
		if(this.player.playerHand.Hand[0].cardNumber == 0)
			return;
		tempCard = this.player.playerHand.Hand[playerChoice];
		currentPile(playerChoice, tempCard);
		this.player = updatePlayerHand(playerChoice, this.player);
		
	}
	
	public Player aiPlayerTurn(Player aiPlayer) //this is where the AI player determines what their move will be. If they play an invalid card it skips their turn
	{
		int aiPlayerChoice;
		Card tempCard;
		//aiPlayer.playerHand.DisplayHand();
		System.out.printf("\tPlease wait for AI players to finish\n");
		
		aiPlayerChoice = 0;
		
		if(aiPlayer.playerHand.Hand[aiPlayerChoice].cardNumber < this.pileDeck[index].cardNumber)
		{
			System.out.println("\tSkip...");
			return aiPlayer;
		}
		else
		{
			tempCard = aiPlayer.playerHand.Hand[aiPlayerChoice];
			currentPile(aiPlayerChoice, tempCard);
			return updatePlayerHand(aiPlayerChoice, aiPlayer);
		}
	}
	
	public boolean checkforNewPile() //looks to see if any players have any playable cards and if not creates a new deck or if the top card is a 14 (ace) then it creates a new pile
	{
		int index = this.index;
		if(this.pileDeck[index].cardNumber == 14)
			return true;
		
		int length1 = player.playerHand.Hand.length;
		int length2 = aiPlayer1.playerHand.Hand.length;
		int length3 = aiPlayer2.playerHand.Hand.length;
		int length4 = aiPlayer3.playerHand.Hand.length;
		
		for(int i = 0; i < length1; i++)
			if(player.playerHand.Hand[i].cardNumber >= pileDeck[index].cardNumber)
				return false;
		
		for(int i = 0; i < length2; i++)
			if(aiPlayer1.playerHand.Hand[i].cardNumber >= pileDeck[index].cardNumber)
				return false;
		
		for(int i = 0; i < length3; i++)
			if(aiPlayer2.playerHand.Hand[i].cardNumber >= pileDeck[index].cardNumber)
				return false;
		
		for(int i = 0; i < length4; i++)
			if(aiPlayer3.playerHand.Hand[i].cardNumber >= pileDeck[index].cardNumber)
				return false;
		
		return true;
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
