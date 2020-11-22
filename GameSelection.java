import java.util.Scanner;

public class GameSelection 
{

	//Game Objects
	Crazy8s Game1;
	Poker Game2;
	President Game3;
	Spoons Game4;
	
	//User Interaction Objects & Variables
	Scanner UserChoice = new Scanner(System.in);
	int gameChoice;
	
	GameSelection()
	{
		//Printing Choice for Crazy 8s
		System.out.println("Enter 1 to Play Crazy 8s\n");
		//Printing Choice for Poker
		System.out.println("Enter 2 to Play Poker\n");
		//Printing Choice for President
		System.out.println("Enter 3 to Play President\n");
		//Printing Choice for Spoons
		System.out.println("Enter 4 to Play Spoons\n");

		gameChoice = UserChoice.nextInt();
		
		if(gameChoice == 1)
		{
			/*
			 * Will Be Added Back Into Game Selection Once Basic Functionality Work With One Use-Case
			 */
			//Game1 = new Crazy8s();
			//Was Tested with and Without the startGame() 
			//Game1.startGame();
		}
		else if(gameChoice == 2)
		{
			Game2 = new Poker();
			Game2.startGame();
		}
		else if(gameChoice == 3)
		{
			Game3 = new President();
		}
		else if(gameChoice == 4)
		{
			Game4 = new Spoons();
			Game4.Start();
		}
		else
		{
			System.out.println("Invalid Game Number Entered, System Will Close");
		}
	}
	
	public static void main(String[] args) 
	{
		GameSelection GS = new GameSelection();
	}

}
