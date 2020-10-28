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
		Scanner input = new Scanner(System.in);
        
		//First Round
		if(newGame == true & player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null & player.playerHand.Hand[4]==null)
		{
            System.out.println("Game Start!");
            newGame = false;
			//Deal 4 cards from top of Deck
			DealCards(this.currentDeckCard,this.deck, this.player.playerHand);
			
			//Starting the n+1 Round (Recursion)
			startGame();
        }

        //If player has matching card
        else if(discardPile[0].cardNumber == player.playerHand.Hand[0].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[1].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[2].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[3].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[4].cardNumber)
        {

			System.out.println("You have a matching card!");
			System.out.println("Discarding matching card now...");

			//discard card...
			for(int i = 0; i<player.playerHand.Hand.length; i++)
				{
					if(player.playerHand.Hand[i].cardNumber == player.playerHand.Hand[0].cardNumber)
						tossCard(player.playerHand.Hand[i].cardNumber, player);
					
					else if(player.playerHand.Hand[i].cardNumber == player.playerHand.Hand[1].cardNumber)
						tossCard(player.playerHand.Hand[i].cardNumber, player);
						
					else if(player.playerHand.Hand[i].cardNumber == player.playerHand.Hand[2].cardNumber)
						tossCard(player.playerHand.Hand[i].cardNumber, player);
					
					else if(player.playerHand.Hand[i].cardNumber == player.playerHand.Hand[3].cardNumber)
						tossCard(player.playerHand.Hand[i].cardNumber, player);
					
					else if(player.playerHand.Hand[i].cardNumber == player.playerHand.Hand[4].cardNumber)
						tossCard(player.playerHand.Hand[i].cardNumber, player);
				}
			
			
			replaceCard();

        }

        //If player has an 8 in pile (optional to use)
        else if(player.playerHand.Hand[0].cardNumber == 8 || player.playerHand.Hand[1].cardNumber == 8 || player.playerHand.Hand[2].cardNumber == 8 || player.playerHand.Hand[3].cardNumber == 8 || player.playerHand.Hand[4].cardNumber == 8)
        {
			System.out.println("You have an 8 in your hand!");
			System.out.println("Would you like to discard it?");
			System.out.println("Type 1 for YES, or 2 for NO: ");
			decision = input.nextInt();

			//tosses an 8
			if(decision == 1)
			{
				//discard 8
				for(int i = 0; i<player.playerHand.Hand.length; i++)
				{
					if(player.playerHand.Hand[i].cardNumber == 8)
						tossCard(player.playerHand.Hand[i].cardNumber, player);
				}

				replaceCard();
			}

			//tosses another card
			if(decision == 2)
			{
				replaceCard();
			}

			else
			{
				System.out.println("Discarding 8 card...");

				//discard 8
				for(int i = 0; i<player.playerHand.Hand.length; i++)
				{
					if(player.playerHand.Hand[i].cardNumber == 8)
						tossCard(player.playerHand.Hand[i].cardNumber, player);
				}

				replaceCard();
			}
        }

        //If player does not have matching cards or 8
        else if(currentDeckCard!=deck.Deck.length)
		{
			replaceCard();
        }
        
        //Win condition
        else if(newGame == false & player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null & player.playerHand.Hand[4]==null)
        {
            player.playerHand.DisplayHand();

            System.out.println("Congrats! You won the game!");

            roundCounter = 0;

            winner = true;
        }
		
		//end game
		/*
		System.out.println("");
        System.out.println("Thanks for playing!");
		System.out.println("x");
		
		input.close();*/

		return winner;
		
    }

    public void DealCards(int currentDeckCard,DeckofCards Deck, PlayerHand Hand)
	{
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
		}
		
		//n+1 Round Deal
		else
		{
			//Display Hand to Player for Choice
			Hand.DisplayHand();
			
			//Obtain the Card to be Tossed from Player
			System.out.println("\nWhich Card Would You Like to Toss? (Pick a Number Between 1-5)");
			tossCardID = str.nextInt() - 1;
			
			//Replacing the Tossed Card
			Hand.Hand[tossCardID] = Deck.Deck[currentDeckCard];
			currentDeckCard++;

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

	public void tossCard(int cardPlayed, Player player) //creates a pile of cards so we can keep track of the current card at the top
	{
		int index = 0;
		discardPile[index] = player.playerHand.Hand[cardPlayed];
		System.out.println("\tCurrent top card is " + discardPile[index].cardNumber + " of " + discardPile[index].cardSuit);
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
		System.out.println("x");
		
		input.close();

		

	}
    
}
