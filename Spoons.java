import java.util.Scanner;
import java.util.Random;

/*Objective: To win this game the player must get all four of the same number or face card 
 *	Round Based Game:
 *	-Round 1: Player is given 4 cards from the top of the shuffled deck
 *		- From here the player can choose what cards they like to keep and what card get tossed (Only One Card Can Be Tossed At a Time)
 *	-Round n+1: Player will continue to go through the deck choosing which cards to keep/toss
 *Win Condition: Player obtains all four of the same number
 *Semi-Win: Win the Guessing Number Game when you don't have four of a kind
 *Lose Condition: Player does not obtain all four of the same number before the deck is empty
 */
public class Spoons {

	//Needed Objects
	DeckofCards deck;
	//PlayerHand player;
	Scanner str;
	Player player;
	
	//Needed Variables
	int currentDeckCard = 0;
	int cardsForHand = 4;
	int tossCardID;
	int roundCounter = -2;
	boolean winner;
	boolean finalRound = false;

	Random dice = new Random();

	//Assigns amount of turns before an AI grabs a spoon
	int roundMax = 30 + dice.nextInt(15);
	
	Spoons()
	{
		deck = new DeckofCards();
		deck.ShuffleDeck(deck.Deck);
		player = new Player(cardsForHand);
		str = new Scanner(System.in);
	}
	
	public boolean Rounds()
	{
		roundCounter++;
		System.out.println("");
		//First Round
		if(player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null)
		{
			//Deal 4 cards from top of Deck
			DealCards(this.currentDeckCard,this.deck, this.player.playerHand);
			
			//Starting the n+1 Round (Recursion)
			Rounds();
		}
		//AI Wins Round 1 or 2; player gets chance to grab spoon
		else if(roundCounter == roundMax)
		{
			System.out.flush();
			System.out.println();
			//Player gets a chance to grab a spoon if Round 1 or 2
			//Must guess number correctly to grab a spoon
			if(finalRound == false){
				System.out.println("");
				GuessNumber minigame = new GuessNumber();
				//Method to activate Guess Number Game
				winner = minigame.GuessGame();
				//Initialize back to 0
				roundCounter = 0;
				//Determine new number of turns until AI wins for next round
				roundMax = 30 + dice.nextInt(15);
			}else{
				//If final round (Round 3), the Player cannot grab a spoon
				//when the AI wins
				winner = false;
			}

		}
		//Win Condition
		else if(player.playerHand.Hand[0].cardNumber == player.playerHand.Hand[1].cardNumber & player.playerHand.Hand[0].cardNumber == player.playerHand.Hand[2].cardNumber & player.playerHand.Hand[0].cardNumber == player.playerHand.Hand[3].cardNumber)
		{
			//Displaying the Final Cards in the Player's Hand 
			player.playerHand.DisplayHand();
			
			//Print Round (For Testing Purposes)
			System.out.println(this.roundCounter);
			
			//Display the Win Screen and prepare to exit game
			System.out.println("\t*----------------------------------------*");
			System.out.println("\tFour of a kind!!!! You grabbed a spoon!!!!");
			System.out.println("\t*----------------------------------------*");

			//Initialize back to 0
			roundCounter = 0;
			//Determine new number of turns until AI wins for next round
			roundMax = 30 + dice.nextInt(15);
			//Player wins this round
			winner = true;
		}
		//n+1 Round
		else if(currentDeckCard!=deck.Deck.length)
		{
			//Tosses Chosen Card and Replaces it with New Card
			DealCards(this.currentDeckCard,this.deck, this.player.playerHand);
			
			//Starting the n+1 Round (Recursion)
			Rounds();
		}
		//Lose Condition
		else if(player.playerHand.Hand[0] != player.playerHand.Hand[1] & player.playerHand.Hand[0] != player.playerHand.Hand[2] & player.playerHand.Hand[0] != player.playerHand.Hand[3] & currentDeckCard==deck.Deck.length)
		{
			//Displaying the Final Cards in the Player's Hand 
			player.playerHand.DisplayHand();
			
			System.out.println(this.roundCounter);
			
			//Display the Lose Screen and prepare to exit game 
			System.out.println("\t*-------------*");
			System.out.println("\tYou have Lost");
			System.out.println("\t*-------------*");
			winner = false;
		}

		return winner;
	}
	
	public void DealCards(int currentDeckCard,DeckofCards Deck, PlayerHand Hand)
	{
		//First Round Deal
		if(player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null)
		{
			//Dealing the Player's Starting Hand
			for(int i = 0; i < 4; i++)
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
			System.out.println("\nWhich Card Would You Like to Toss? (Pick a Number Between 1-4)");
			tossCardID = str.nextInt() - 1;
			
			try 
			{
				//Replacing the Tossed Card
				Hand.Hand[tossCardID] = Deck.Deck[currentDeckCard];
				currentDeckCard++;
			}
			catch(ArrayIndexOutOfBoundsException e) 
			{
				System.out.println("Hand Does Not Have That Many Cards In Hand");
				System.out.println("\n\t-------------------");
				System.out.println("\n\t ****Game Over****\n");
				System.out.println("\t  Restart Client  ");
				System.out.println("\t-------------------");
				System.exit(0);
			}

			
			
			//Updating the Global currentDeckCard
			this.currentDeckCard = currentDeckCard;
		}
	}

	//Used to flag that it's the final round
	public void finalRound(){
		finalRound = true;
	}
	
	
	public static void main(String[] args)
	{
		//Needed variables
		int games = 1;
		boolean win = true;
		int decision;
		Spoons f4 = new Spoons();
		Scanner keyboard = new Scanner(System.in);

		//Creates a new round for the Spoons Game
		win = f4.Rounds();

		//While the Player is still "alive" in the game
		while(win == true && games <= 3){
			games++;
			if(win == true && games <= 3){
				//Prompt to continue next round
				System.out.println("");
				System.out.println("Ready for Round " + games + "?");
				System.out.println("Type 1 for YES, or 2 for NO: ");
				decision = keyboard.nextInt();
				System.out.println("");
				//Starts round 2
				if(decision == 1 && games <= 2){
					f4 = new Spoons();
					win = f4.Rounds();
				}
				//Start Final Round... need to activate flag
				else if(decision == 1 && games <= 3){
					f4 = new Spoons();
					//Activates Final Round flag
					f4.finalRound();
					win = f4.Rounds();
				}
				//Player does NOT select 1 to play the next round
				else{
					games = 5;
					System.out.println("");
					System.out.println("Thank you for playing!!");
					System.out.println("");
				}
			}	
		}
		//Finished in last place
		//Lost in the first round
		if(win == false && games <= 1){
			System.out.println("\n\tGAME OVER!\n");
			System.out.println("You ranked last place  ");
		//Finished in 3rd place
		//Lost in the second round
		}else if(win == false && games <= 2){
			System.out.println("\n\tGAME OVER!\n");
			System.out.println("You ranked 3rd place  ");
		}	
		//Finished in 2nd place
		//Lost in the Final Round
		else if(win == false && games <= 3){
			System.out.println("\n\tGAME OVER!\n");
			System.out.println("\n\tYou ranked 2nd place  ");
		}
		//Finished in 1st place
		//Won the Final Round
		else if(win == true && games <= 4){

			System.out.println("\n\tCONGRATULATIONS!!!\n");
			System.out.println("\n\tYou ranked 1st place  \n");
		}			
	}

}
