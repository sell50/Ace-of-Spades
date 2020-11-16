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
    int roundCounter = -2; //is this used?
    boolean winner;
    boolean newGame = true;

    Crazy8s() throws Exception {
		
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
		
		/*int decision;
		Card tempCard;
		Scanner input = new Scanner(System.in);*/
	
			
			//First Round
		if(checkSize(player) == 0 & newGame){

			/*if(newGame == true & player.playerHand.Hand[0]==null & player.playerHand.Hand[1]==null & player.playerHand.Hand[2]==null & player.playerHand.Hand[3]==null & player.playerHand.Hand[4]==null)
			{*/
				firstemptyHand();

				/*System.out.println("Game Start!\n");
				newGame = false;

				//Deal 5 cards from top of Deck to populate hand
				DealCards(this.currentDeckCard,this.deck, this.player.playerHand);

				discardPile[0] = deck.Deck[currentDeckCard];

				//RiggedHand();
				//Card c2 = new Card(deck.cardNumbers[0], deck.cardSuits[0]);

				//player.playerHand.DisplayHand();

				//discardPile[0] = c2;

				System.out.println("\n\tCurrent top card is " + discardPile[0].cardNumber + " of " + discardPile[0].cardSuit + "\n");

				//Starting the next round
				
				startGame();*/
	
			
			//}
		}

		//way to access varying hand lengths? functions on the bottom
		for(int i = 0; i<15; i++){
			if(checkSize(player) == i){
				if(checkMatchNum(i, player)){
					matchNum();
				}
				if(checkMatchSuit(i, player)){
					matchSuit();
				}
				if(checkMatch8(i, player)){
					match8();
				}

				//If player does not have matching cards or 8
				if(currentDeckCard!=deck.Deck.length)
				{
					System.out.println("You do not have any matching cards!\n");
					//Add new card....
					player = addCard(this.currentDeckCard,this.deck, this.player);
	
					System.out.println("\nYour cards: after adding");
					player.playerHand.DisplayHand();
					
				}
			}
		}

		//may delete this...
			//If player has matching number card
			if(discardPile[0].cardNumber == player.playerHand.Hand[0].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[1].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[2].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[3].cardNumber || discardPile[0].cardNumber == player.playerHand.Hand[4].cardNumber)
			{

				matchNum();
				/*
				System.out.println("\nYou have a matching card number!");

				System.out.println("Would you like to discard it?");
				System.out.println("Type 1 for YES, or 2 for NO: \n");

				decision = input.nextInt();
				

				if(decision==1){

					System.out.println("Discarding matching card now...\n");

					//discard card...
					for(int i = 0; i<player.playerHand.Hand.length; i++)
						{
							if(player.playerHand.Hand[i].cardNumber == discardPile[0].cardNumber){
								
								tempCard = player.playerHand.Hand[i];
								tossCard(tempCard);

								player = removeCards(player, i);

								System.out.println("\nYour cards: after removing");
								player.playerHand.DisplayHand();
								break;
	
							}
							
						}
					
					System.out.println("\nbefore replacing card num\n");
					
					System.out.println("\nafter replacing card num\n");
				}

				//Add new card 
				if(decision == 2){
					System.out.println("\nAdd new card cuz no match num\n");
					//need to add here...
					//replaceCard();
					player = addCard(this.currentDeckCard,this.deck, this.player);

					System.out.println("\nYour cards: after adding");
					player.playerHand.DisplayHand();
					startGame();
				}*/
			}

			//if matching card suit
			else if(discardPile[0].cardSuit.equals(player.playerHand.Hand[0].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[1].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[2].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[3].cardSuit) || discardPile[0].cardSuit.equals(player.playerHand.Hand[4].cardSuit))
			{

				matchSuit();

				/*System.out.println("\nYou have a matching card suit!");

				System.out.println("Would you like to discard it?");
				System.out.println("Type 1 for YES, or 2 for NO: \n");

				decision = input.nextInt();

				if(decision==1){


					System.out.println("Discarding matching card now...\n");

					//discard card...
					for(int i = 0; i<player.playerHand.Hand.length; i++)
						{
							if(player.playerHand.Hand[i].cardSuit.equals(discardPile[0].cardSuit)){
								tempCard = player.playerHand.Hand[i];
								tossCard(tempCard);

								player = removeCards(player, i);

								System.out.println("\nYour cards:");
								player.playerHand.DisplayHand();

								break;

							}
						}
		
					}
				
				//add new card
				if(decision == 2)
				{
					System.out.println("\nAdd new card cuz no match suit\n");
				
					player = addCard(this.currentDeckCard,this.deck, this.player);

					System.out.println("\nYour cards: after adding");
					player.playerHand.DisplayHand();

					startGame();
				}*/
			}

			//If player has an 8 in pile (optional to use)
			if(player.playerHand.Hand[0].cardNumber == 8 || player.playerHand.Hand[1].cardNumber == 8 || player.playerHand.Hand[2].cardNumber == 8 || player.playerHand.Hand[3].cardNumber == 8 || player.playerHand.Hand[4].cardNumber == 8)
			{
				match8();

				/*System.out.println("\nYour cards:");
				player.playerHand.DisplayHand();
				System.out.println("");
				System.out.println("You have an 8 in your hand!");
				System.out.println("Would you like to discard it?");
				System.out.println("Type 1 for YES, or 2 for NO: \n");

				decision = input.nextInt();

				//tosses an 8
				if(decision == 1)
				{

					System.out.println("Discarding matching card now...\n");

					//discard 8
					for(int i = 0; i<player.playerHand.Hand.length; i++)
					{
						if(player.playerHand.Hand[i].cardNumber == 8){
							tempCard = player.playerHand.Hand[i];
							tossCard(tempCard);

							player = removeCards(player, i);

							System.out.println("\nYour cards:");
							player.playerHand.DisplayHand();
							break;

						}
					}

					
				}

				//Add new card
				if(decision == 2)
				{
					System.out.println("\nreplace new card cuz no 8\n");
					//need to add here...
					//replaceCard();
					player = addCard(this.currentDeckCard,this.deck, this.player);

					System.out.println("\nYour cards: after adding");
					player.playerHand.DisplayHand();
					startGame();
				}*/

				
			}

			//If player does not have matching cards or 8
			if(currentDeckCard!=deck.Deck.length)
			{
				System.out.println("You do not have any matching cards!\n");
				//Add new card....
				player = addCard(this.currentDeckCard,this.deck, this.player);

				System.out.println("\nYour cards: after adding");
				player.playerHand.DisplayHand();
				
			}
			
			//Win condition
			if(!newGame & checkSize(player) == 0)
			{
				
				emptyHandWin();
				/*
				System.out.println("\nCongrats! You won the game!");

				roundCounter = 0;

				winner = true;

				 */
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

		return !winner;
		
		
    }

	public void DealCards(int currentDeckCard,DeckofCards Deck, PlayerHand Hand)
	{
		Card tempCard1;
		
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


			System.out.println("\tCurrent top card is " + discardPile[0].cardNumber + " of " + discardPile[0].cardSuit + "\n");
			
			//Obtain the Card to be Tossed from Player
			System.out.println("\nWhich Card Would You Like to Toss? (Pick a Number Between 1-5)");
			tossCardID = str.nextInt() - 1;
			
			//adding tossed card to discard pile
			tempCard1 = player.playerHand.Hand[tossCardID];
			tossCard(tempCard1);

			//Replacing the Tossed Card
			Hand.Hand[tossCardID] = Deck.Deck[currentDeckCard];
			currentDeckCard++;

			//Updating the Global currentDeckCard
			this.currentDeckCard = currentDeckCard;

			System.out.println("\nYour cards: after toss");
			Hand.DisplayHand();
			
			System.out.println("\ntop card after toss");
			System.out.println("\tCurrent top card is " + discardPile[0].cardNumber + " of " + discardPile[0].cardSuit + "\n");
		}
	}

	public void tossCard(Card card) //creates a pile of cards so we can keep track of the current card at the top
	{
		int index = 0;
		discardPile[index] = card;
		System.out.println("\tCurrent top card is " + discardPile[index].cardNumber + " of " + discardPile[index].cardSuit);
	}

	public Player addCard(int currentDeckCard, DeckofCards deck, Player player){
		
		Card newCard = deck.Deck[currentDeckCard];
		currentDeckCard++;
		this.currentDeckCard = currentDeckCard;

		int length = player.playerHand.Hand.length;
		
		Player tempHand = new Player(length + 1);

		for(int i = 0, j = 0; i < length; i++)
		{
		
			tempHand.playerHand.Hand[j] = player.playerHand.Hand[i];
			j++;

		}

		tempHand.playerHand.Hand[length+1] = newCard;

		return tempHand;

	}


	public Player removeCards(Player player, int cardIndex){

		System.out.println("Element to be removed at index: " + cardIndex);

		if (player.playerHand == null || cardIndex < 0 || cardIndex >= player.playerHand.Hand.length) {
			System.out.println("No removal operation can be performed!!");
		} 

		Player tempHand = new Player(player.playerHand.Hand.length - 1);

		
        for (int i = 0, k = 0; i <player.playerHand.Hand.length; i++) { 
 
            
            if (i == cardIndex) {
                continue; 
            } 
 
           
            tempHand.playerHand.Hand[k++] = player.playerHand.Hand[i]; 
		} 
		
		return tempHand;
	}


/*
	public void RiggedHand()
	{
		for(int i = 0; i < 5; i++)
		{
			Card c1 = new Card(deck.cardNumbers[i+1], deck.cardSuits[0]);
			player.playerHand.Hand[i] = c1;
		}
	}
*/


public int checkSize(Player player){

	int i;

	for(i = 0; i<=10; i++){
		if(player.playerHand.Hand.length == i){
			return i;
		}
	}

	return i;

}

public void matchSuit(){
	Card tempCard;
	int decision;
	Scanner input = new Scanner(System.in);

	System.out.println("\nYou have a matching card suit!");

	System.out.println("Would you like to discard it?");
	System.out.println("Type 1 for YES, or 2 for NO: \n");

	decision = input.nextInt();

	if (decision == 1) {

		System.out.println("Discarding matching card now...\n");

		// discard card...
		for (int i = 0; i < player.playerHand.Hand.length; i++) {
			if (player.playerHand.Hand[i].cardSuit.equals(discardPile[0].cardSuit)) {
				tempCard = player.playerHand.Hand[i];
				tossCard(tempCard);

				player = removeCards(player, i);

				System.out.println("\nYour cards:");
				player.playerHand.DisplayHand();

				break;

			}
		}

	}

	// add new card
	if (decision == 2) {
		System.out.println("\nAdd new card cuz no match suit\n");

		player = addCard(this.currentDeckCard, this.deck, this.player);

		System.out.println("\nYour cards: after adding");
		player.playerHand.DisplayHand();

		startGame();
	}
	input.close();
}

public void matchNum(){
	int decision;
	Card tempCard;
	Scanner input = new Scanner(System.in);

	System.out.println("\nYou have a matching card number!");

	System.out.println("Would you like to discard it?");
	System.out.println("Type 1 for YES, or 2 for NO: \n");

	decision = input.nextInt();

	if (decision == 1) {

		System.out.println("Discarding matching card now...\n");

		// discard card...
		for (int i = 0; i < player.playerHand.Hand.length; i++) {
			if (player.playerHand.Hand[i].cardNumber == discardPile[0].cardNumber) {

				tempCard = player.playerHand.Hand[i];
				tossCard(tempCard);

				player = removeCards(player, i);

				System.out.println("\nYour cards: after removing");
				player.playerHand.DisplayHand();
				break;

			}

		}

		System.out.println("\nbefore replacing card num\n");

		System.out.println("\nafter replacing card num\n");
	}

	// Add new card
	if (decision == 2) {
		System.out.println("\nAdd new card cuz no match num\n");
		// need to add here...
		// replaceCard();
		player = addCard(this.currentDeckCard, this.deck, this.player);

		System.out.println("\nYour cards: after adding");
		player.playerHand.DisplayHand();
		startGame();
	}

	input.close();
}

public void match8(){
	int decision;
	Card tempCard;
	Scanner input = new Scanner(System.in);

	System.out.println("\nYour cards:");
	player.playerHand.DisplayHand();
	//System.out.println("");
	System.out.println("You have an 8 in your hand!");
	System.out.println("Would you like to discard it?");
	System.out.println("Type 1 for YES, or 2 for NO: \n");

	decision = input.nextInt();

	//tosses an 8
	if(decision == 1)
				{

		System.out.println("Discarding matching card now...\n");

		//discard 8
		for(int i = 0; i<player.playerHand.Hand.length; i++)
			{
				if(player.playerHand.Hand[i].cardNumber == 8){
					tempCard = player.playerHand.Hand[i];
					tossCard(tempCard);

					player = removeCards(player, i);

					System.out.println("\nYour cards:");
					player.playerHand.DisplayHand();
					break;

				}
			}
	
	}

	//Add new card
	if(decision == 2)
	{
		System.out.println("\nreplace new card cuz no 8\n");
		//need to add here...
		//replaceCard();
		player = addCard(this.currentDeckCard,this.deck, this.player);

		System.out.println("\nYour cards: after adding");
		player.playerHand.DisplayHand();
		startGame();
	}
	input.close();
}

public void firstemptyHand(){

	System.out.println("Game Start!\n");
	newGame = false;

	//Deal 5 cards from top of Deck to populate hand
	DealCards(this.currentDeckCard,this.deck, this.player.playerHand);

	discardPile[0] = deck.Deck[currentDeckCard];

	System.out.println("\n\tCurrent top card is " + discardPile[0].cardNumber + " of " + discardPile[0].cardSuit + "\n");

	//Starting the next round
				
	startGame();
}

public void emptyHandWin(){

	System.out.println("\nCongrats! You won the game!");

	roundCounter = 0;

	winner = true;
}

public boolean checkMatchNum(int n, Player player){

	for(int i = 0; i<n; i++){
		if(discardPile[0].cardNumber == player.playerHand.Hand[i].cardNumber){
			return true;
		}
	}

	return false;
}

public boolean checkMatchSuit(int n, Player player){

	for(int i = 0; i<n; i++){
		if(discardPile[0].cardSuit.equals(player.playerHand.Hand[i].cardSuit)){
			return true;
		}
	}

	return false;
}

public boolean checkMatch8(int n, Player player){
	
	for(int i = 0; i<n; i++){
		if(player.playerHand.Hand[i].cardNumber == 8){
			return true;
		}
	}

	return false;
}

	public static void main(String[] args) throws Exception {

		//Needed variables
		boolean start = true;
		int decision;
		Crazy8s crazy8s;
		Scanner input = new Scanner(System.in);

		//System.out.println("");
		
		while(start){
			
			//Prompt to start game
			//System.out.println("");
			System.out.println("Welcome to Crazy8s!");
			System.out.println("Ready to start Crazy8s?");
			System.out.println("Type 1 for YES, or 2 for NO: ");
			decision = input.nextInt();
            //System.out.println("");
            
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
        //System.out.println("");
        System.out.println("Thanks for playing!");
		//System.out.println("");
		
		input.close();

		

	}
    
}
