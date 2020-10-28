import java.util.Scanner;

/*Objective: The player to put down all their card wins this game. They are given 5 cards and 
*must match the card on the pile with suite or number. If the player has an 8 in their pile, they can place it
*if they have no matching cards. If the player can not play any cards, they take from the top of the deck. 

*Win: Player plays all their cards
*Lose: Last player with any cards
*/

public class Crazy8s {
    
    //Needed Objects
    DeckofCards deck;
    Player player;
    Scanner str;
    Card[] discardPile;

    //Needed variables
    int cardsForHand = 5;
    int currentDeckCard = 0;
    int tossCardID;
    int roundCounter = -2;
    boolean winner;
    boolean newGame = true;

    Crazy8s(){
		
		//Needed objects
		discardPile = new Card[52];
        deck = new DeckofCards();
        deck.ShuffleDeck(deck.Deck);
        player = new Player(cardsForHand);
		str = new Scanner(System.in);
		
    }

    public boolean startGame(){

		//Needed variables
		/*boolean start = true;
		int decision;
		Crazy8s crazy8s = new Crazy8s();
		Scanner input = new Scanner(System.in);

		System.out.println("");
		
		while(start == true){
			
			//Prompt to start game
			System.out.println("");
			System.out.println("Welcome to Crazy8s!");
			System.out.println("Ready to start Crazy8s?");
			System.out.println("Type 1 for YES, or 2 for NO: ");
			decision = input.nextInt();
            System.out.println("");
            
			//Start game
			if(decision == 1){
				crazy8s = new Crazy8s(); //help???
				start = crazy8s.startGame();
            }
                
            else{
                start = false;
            }
		}*/
		int decision;
		Card tempCard;
		Scanner input = new Scanner(System.in);
			
			//First Round
			if(newGame == true & player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null & player.playerHand.Hand[4]==null)
			{
				System.out.println("Game Start!\n");
				newGame = false;
				//Deal 4 cards from top of Deck
				//DealCards(this.currentDeckCard,this.deck, this.player.playerHand);
				//discardPile[0] = deck.Deck[currentDeckCard];
				RiggedHand();
				Card c2 = new Card(deck.cardNumbers[0], deck.cardSuits[0]);
				player.playerHand.DisplayHand();

				discardPile[0] = c2;
				System.out.println("\n\tCurrent top card is " + discardPile[0].cardNumber + " of " + discardPile[0].cardSuit + "\n");
				//Starting the n+1 Round (Recursion)
				startGame();
			}

			//If player has matching number card
			else if(discardPile[0].cardNumber == player.playerHand.Hand[0].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[1].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[2].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[3].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[4].cardNumber)
			{

				System.out.println("\nYou have a matching card!");
				System.out.println("Discarding matching card now...\n");

				//discard card...
				for(int i = 0; i<player.playerHand.Hand.length; i++)
					{
						if(player.playerHand.Hand[i].cardNumber == discardPile[0].cardNumber){
							tempCard = player.playerHand.Hand[i];
							tossCard(player.playerHand.Hand[i].cardNumber, tempCard);

							player.playerHand.Hand[i].cardNumber = 1;
							player.playerHand.Hand[i].cardSuit = "Hearts";
							

							//remove card from hand
						}
						
					}
				
				
				replaceCard();

			}

			//if matching card suit
			else if(discardPile[0].cardSuit.equals(player.playerHand.Hand[0].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[1].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[2].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[3].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[4].cardSuit))
			{

				System.out.println("\nYou have a matching card!");
				System.out.println("Discarding matching card now...\n");

				//discard card...
				for(int i = 0; i<player.playerHand.Hand.length; i++)
					{
						if(player.playerHand.Hand[i].cardSuit.equals(discardPile[0].cardSuit)){
							tempCard = player.playerHand.Hand[i];
							tossCard(player.playerHand.Hand[i].cardNumber, tempCard);

							player.playerHand.Hand[i].cardNumber = 1;
							player.playerHand.Hand[i].cardSuit = "Hearts";
							
							//remove card from hand

						}
					}
					replaceCard();
				}

			//If player has an 8 in pile (optional to use)
			if(player.playerHand.Hand[0].cardNumber == 8 || player.playerHand.Hand[1].cardNumber == 8 || player.playerHand.Hand[2].cardNumber == 8 || player.playerHand.Hand[3].cardNumber == 8 || player.playerHand.Hand[4].cardNumber == 8)
			{
				System.out.println("\nYour cards:");
				player.playerHand.DisplayHand();
				System.out.println("");
				System.out.println("You have an 8 in your hand!");
				System.out.println("Would you like to discard it?");
				System.out.println("Type 1 for YES, or 2 for NO: \n");

				decision = input.nextInt();

				//tosses an 8
				if(decision == 1)
				{
					//discard 8
					for(int i = 0; i<player.playerHand.Hand.length; i++)
					{
						if(player.playerHand.Hand[i].cardNumber == 8){
							tempCard = player.playerHand.Hand[i];
							tossCard(player.playerHand.Hand[i].cardNumber, tempCard);

							player.playerHand.Hand[i].cardNumber = 1;
							player.playerHand.Hand[i].cardSuit = "Hearts";

							//remove card

						}
					}

					replaceCard();
				}

				//tosses another card
				if(decision == 2)
				{
					replaceCard();
					startGame();
				}

				else
				{
					System.out.println("\nDiscarding 8 card...");

					//discard 8
					for(int i = 0; i<player.playerHand.Hand.length; i++)
					{
						if(player.playerHand.Hand[i].cardNumber == 8){
							tempCard = player.playerHand.Hand[i];
							tossCard(player.playerHand.Hand[i].cardNumber, tempCard);

							player.playerHand.Hand[i].cardNumber = 1;
							player.playerHand.Hand[i].cardSuit = "Hearts";

							//remove card

						}
					}

					replaceCard();
				}
			}

			//If player does not have matching cards or 8
			if(currentDeckCard!=deck.Deck.length)
			{
				System.out.println("You do not have any matching cards!\n");
				replaceCard();
			}
			
			//Win condition
			if(newGame == false & player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null & player.playerHand.Hand[4]==null)
			{
				//player.playerHand.DisplayHand();

				System.out.println("\nCongrats! You won the game!");

				roundCounter = 0;

				winner = true;
			}
			
			//end game
			/*
			System.out.println("");
			System.out.println("Thanks for playing!");
			System.out.println("");
			
			input.close();*/

			else{
				System.out.println("new Round!\n");
				startGame();
			}
		return winner;

		
    }

	public void DealCards(int currentDeckCard,DeckofCards Deck, PlayerHand Hand)
	{
		Card tempCard;
		//First Round Deal
		if(player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null &  player.playerHand.Hand[4]==null)
		{
			//Dealing the Player's Starting Hand
			for(int i = 0; i < 5; i++)
			{
				Hand.Hand[i]=Deck.Deck[currentDeckCard];
				currentDeckCard++;
			}
			//Update the Global currentDeckCard
			this.currentDeckCard = currentDeckCard;

			System.out.println("\nYour cards:");
			Hand.DisplayHand();

		}
		
		//n+1 Round Deal
		else
		{
			//Display Hand to Player for Choice
			System.out.println("Your cards:");
			Hand.DisplayHand();
			System.out.println("");

			System.out.println("\tCurrent top card is " + discardPile[0].cardNumber + " of " + discardPile[0].cardSuit + "\n");
			
			//Obtain the Card to be Tossed from Player
			System.out.println("\nWhich Card Would You Like to Toss? (Pick a Number Between 1-5)");
			tossCardID = str.nextInt() - 1;
			
			//Replacing the Tossed Card
			Hand.Hand[tossCardID] = Deck.Deck[currentDeckCard];
			currentDeckCard++;

			//Adds new card to discard pile
			/*for(int i = 0; i<player.playerHand.Hand.length; i++)
				{
					if(player.playerHand.Hand[i].cardNumber == tossCardID){
						tempCard = player.playerHand.Hand[i];
						tossCard(player.playerHand.Hand[i].cardNumber, tempCard);
					}
				}
			*/

			//Updating the Global currentDeckCard
			this.currentDeckCard = currentDeckCard;
		}
	}
	
	public void replaceCard()
	{
		//Tosses Card and Replaces it with new Card
		DealCards(this.currentDeckCard, this.deck, this.player.playerHand);
			
		//Starting the n+1 round
		startGame();
	}

	public void tossCard(int cardPlayed, Card card) //creates a pile of cards so we can keep track of the current card at the top
	{
		int index = 0;
		discardPile[index] = card;
		System.out.println("\tCurrent top card is " + discardPile[index].cardNumber + " of " + discardPile[index].cardSuit);
	}

	public void tossCard(String cardPlayed, Card card) //creates a pile of cards so we can keep track of the current card at the top
	{
		int index = 0;
		discardPile[index] = card;
		System.out.println("\tCurrent top card is " + discardPile[index].cardNumber + " of " + discardPile[index].cardSuit);
	}


	public void removeCard(Player playerHand, Card card){
		//remove card
		
	}

	public void RiggedHand()
	{
		for(int i = 0; i < 5; i++)
		{
			Card c1 = new Card(deck.cardNumbers[i+1], deck.cardSuits[0]);
			player.playerHand.Hand[i] = c1;
		}
	}

	public static void main(String[] args){

		//Needed variables
		boolean start = true;
		int decision;
		Crazy8s crazy8s = new Crazy8s();
		Scanner input = new Scanner(System.in);

		System.out.println("");
		
		while(start == true){
			
			//Prompt to start game
			System.out.println("");
			System.out.println("Welcome to Crazy8s!");
			System.out.println("Ready to start Crazy8s?");
			System.out.println("Type 1 for YES, or 2 for NO: ");
			decision = input.nextInt();
            System.out.println("");
            
			//Start game
			if(decision == 1){
				crazy8s = new Crazy8s(); //help???
				start = crazy8s.startGame();
            }
                
            else{
                start = false;
            }
		}

		//end game
        System.out.println("");
        System.out.println("Thanks for playing!");
		System.out.println("");
		
		input.close();

		

	}
    
}
