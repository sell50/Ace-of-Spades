import java.util.Scanner;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*Objective: To win this game the player must get all four of the same number or face card 
 *	Round Based Game:
 *	-Round 1: Player is given 4 cards from the top of the shuffled deck
 *		- From here the player can choose what cards they like to keep and what card get tossed (Only One Card Can Be Tossed At a Time)
 *	-Round n+1: Player will continue to go through the deck choosing which cards to keep/toss
 *Win Condition: Player obtains all four of the same number
 *Semi-Win: Win the Guessing Number Game when you don't have four of a kind
 *Lose Condition: Player does not obtain all four of the same number before the deck is empty
 */
public class Spoons{

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
	int num = 0;
	

	Random dice = new Random();

	//Assigns amount of turns before an AI grabs a spoon
	int roundMax = 30 + dice.nextInt(15);
	//int roundMax = 3;
	
	//Needed variables
	String win = "Select a card to discard until you get Four of a Kind";
	Scanner keyboard = new Scanner(System.in);		
		
	Spoons()
	{
		try {
			deck = new DeckofCards();
		} catch (Exception e) {
			System.out.println("Error creating deck");
		}
		deck.ShuffleDeck(deck.Deck);
		player = new Player(cardsForHand);
		str = new Scanner(System.in);
		//Deal 4 cards from top of Deck
		DealCards(this.currentDeckCard,this.deck, this.player.playerHand, 0);
	}

	//Starts the game when called
	public void beginSpoons() throws Exception{
			
		this.Rounds(num);

	}
	
	public void Rounds(int num) throws Exception
	{
		roundCounter++;
		//First Round
		if(player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null && roundCounter != roundMax)
		{	
			//Starting the n+1 Round (Recursion)
			//Rounds();
		}
		//AI has grabbed a spoon
		else if(roundCounter == roundMax)
		{		
			win = "GAME OVER! AI Player won. You lose";
		}
		//Win Condition
		else if(player.playerHand.Hand[0].cardNumber == player.playerHand.Hand[1].cardNumber & player.playerHand.Hand[0].cardNumber == player.playerHand.Hand[2].cardNumber & player.playerHand.Hand[0].cardNumber == player.playerHand.Hand[3].cardNumber)
		{
			win = "CONGRATULATIONS! You ranked 1st place!";
		}
		//n+1 Round
		else if(currentDeckCard!=deck.Deck.length)
		{
			//Tosses Chosen Card and Replaces it with New Card
			DealCards(this.currentDeckCard,this.deck, this.player.playerHand, num);

		}
		//Lose Condition
		else if(player.playerHand.Hand[0] != player.playerHand.Hand[1] & player.playerHand.Hand[0] != player.playerHand.Hand[2] & player.playerHand.Hand[0] != player.playerHand.Hand[3] & currentDeckCard==deck.Deck.length)
		{		
			//Display the Lose Screen and prepare to exit game 
			System.out.println("\t*-------------*");
			System.out.println("\tYou have Lost");
			System.out.println("\t*-------------*");
		}
	}
	
	public void DealCards(int currentDeckCard,DeckofCards Deck, PlayerHand Hand, int num)
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
			//Obtain the Card to be Tossed from Player
			tossCardID = num - 1;
			
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
}