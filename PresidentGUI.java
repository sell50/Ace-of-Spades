import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PresidentGUI extends Application {
	
	President president = new President(); //to use the necessary methods from the President class
	
	public HBox playerHand; //global variables needed to create scene, and update it accordingly
	public HBox ai1PlayerHand;
	public HBox ai2PlayerHand;
	public HBox ai3PlayerHand;
	public Button[] playerCards;
	int playerNumCards;
	int ai1NumCards, ai2NumCards, ai3NumCards;
	HBox pCards;
	
	String PicturePath = "C:\\Users\\Brett\\eclipse-workspace\\Ace of Spades\\Cards\\PNG\\";

	public Parent createWindow(Stage stage) //creates the main scene for the game
	{
		
		Pane root = new Pane();
		root.setPrefSize(800, 600); //sets the size of the scene to the window created
		
		Region backGround = new Region();
		backGround.setPrefSize(800 , 600);
		backGround.setStyle("-fx-background-color: rgba(0, 0, 0, 1)"); //sets the background to black
		
		playerHand = new HBox(5); //initialize necessary variables
		ai1PlayerHand = new HBox(5);
		ai2PlayerHand = new HBox(5);
		ai3PlayerHand = new HBox(5);
		playerNumCards = 0;
		ai1NumCards = 0;
		ai2NumCards = 0;
		ai3NumCards = 0;
		pCards = new HBox();
		
		HBox rootDesign = new HBox(5); //create a HBox and set spacing between nodes to 5
		rootDesign.setPadding(new Insets(1, 1, 1, 1)); //set a small thin transparent border across the frame 
		Rectangle leftFrame = new Rectangle(550, 560); //make the left frame bigger then the right frame
		leftFrame.setFill(Color.GREEN);//set the colour to the left frame to green
		Rectangle rightFrame = new Rectangle(230, 560); //set the sizing for the right frame which is smaller then the left frame
		rightFrame.setFill(Color.DARKOLIVEGREEN); //set the colour to the right frame to a dark olive green
		
		//Left frame containing the cards of each player
		
		playerCards = new Button[president.player.playerHand.Hand.length]; //the players cards will be buttons that show the image of the card its index is at and are intractable
		
		for(int i = 0; i < playerCards.length; i++)
		{
			File file = new File(PicturePath + president.player.playerHand.Hand[i].cardNumber + president.player.playerHand.Hand[i].cardSuit + ".png");
			Image cards = new Image(file.toURI().toString(), 35, 60, false, true); //create a new image for each card so that each node is unique with the parent node having no duplicates
			ImageView cardsView = new ImageView(cards);
			playerCards[i] = new Button("", cardsView);
			playerCards[i].setMinSize(35, 60); //set the sizing of the buttons to be the same size as the image of the cards so that they are flush
			playerCards[i].setMaxSize(35, 60);
		}
		
		playerHand.getChildren().addAll(playerCards); //add all the buttons to the playerHand box
		
		for(int i = 0; i < playerCards.length; i++) //for the ai players we don't want the player to see which cards the ai use so set each image to the back of the card.
		{											//we create a new item instance of the backofCard image since we can't have duplicate nodes
			File file = new File(PicturePath+"CardBack.png");
			Image aiPlayerCards = new Image(file.toURI().toString(), 35, 60, false, true);
			ImageView aiPlayerCardsView = new ImageView(aiPlayerCards);
			ai1PlayerHand.getChildren().add(aiPlayerCardsView); //add directly to the HBox
		}
		for(int i = 0; i < playerCards.length; i++)
		{
			File file = new File(PicturePath+"CardBack.png");
			Image aiPlayerCards = new Image(file.toURI().toString(), 35, 60, false, true);
			ImageView aiPlayerCardsView = new ImageView(aiPlayerCards);
			ai2PlayerHand.getChildren().add(aiPlayerCardsView);
		}
		for(int i = 0; i < playerCards.length; i++)
		{
			File file = new File(PicturePath+"CardBack.png");
			Image aiPlayerCards = new Image(file.toURI().toString(), 35, 60, false, true);
			ImageView aiPlayerCardsView = new ImageView(aiPlayerCards);
			ai3PlayerHand.getChildren().add(aiPlayerCardsView);
		}
		Text player = new Text("Player");
		Text ai1 = new Text("AI 1");
		Text ai2 = new Text("AI 2");
		Text ai3 = new Text("AI 3");
		
		VBox leftVBox = new VBox(15);
		leftVBox.setAlignment(Pos.TOP_LEFT); //align all the contents to the top left
		leftVBox.getChildren().addAll(player, playerHand, ai1, ai1PlayerHand, ai2, ai2PlayerHand, ai3, ai3PlayerHand); //add in all the contents of the HBoxs to one bug VBox to create the left frame
		
		//Right frame containing the pile of cards
		
		VBox rightVBox = new VBox(25);
		rightVBox.setAlignment(Pos.CENTER);
		
		
		Image pileCards = new Image(new File(PicturePath+ president.pileDeck[0].cardNumber + president.pileDeck[0].cardSuit + ".png").toURI().toString(), 50, 80, false, true);
		ImageView pileCardsView = new ImageView(pileCards); //get the image for the current top card in the pile deck
		pCards.getChildren().add(pileCardsView);
		pCards.setAlignment(Pos.CENTER);
		
		Dialog<String> infoText = new Dialog<String>(); //create the dialog for the information button
		infoText.setTitle("How to play"); //set the title, and the content of how to play the game and what the objective is
		infoText.setContentText("Welcome to President, the obejctive is to get rid of all the cards in your hand.\nIf you place an ace down the pile resets.\nThe game has started and the president has given their worst 2 cards for the scums best 2 cards, and the vice-president has traded their worst card for the vice-scums best card.\n1st place is given the title president, 2nd is vice-president, 3rd is vice-scum, and 4th place is the scum\nIf you wish to skip or have no playable cards, then click any of your cards as long as its number is less then the current top card.\nGood luck!");
		ButtonType infobtn = new ButtonType("Ok", ButtonData.OK_DONE); //set the dialog box to close once the player hits the ok button
		infoText.getDialogPane().getButtonTypes().add(infobtn);
		Button howToPlay = new Button("Info");
		
		rightVBox.getChildren().addAll(pCards, howToPlay); //add the contents to the right VBox
		
		rootDesign.getChildren().addAll(new StackPane(leftFrame, leftVBox), new StackPane(rightFrame, rightVBox)); //create the design by adding the preferences for each frame, and its contents
		root.getChildren().addAll(backGround, rootDesign);
		
		howToPlay.setOnAction(event -> { //set the event handler when the button is clicked
			infoText.showAndWait(); //window opens and waits for the player to close it
		});
		
		for(int i = 0; i < playerCards.length; i++) //loop through in order to get the action for the correct button and use the button index for the player so they choose the correct card to put in the pile deck
		{
			int index = i;
			playerCards[i].setOnAction( event -> {
				president.playerChoice = index;
				
				president.playerTurn(); //call to players turn
				if(president.player.playerHand.Hand[index].cardNumber == president.pileDeck[0].cardNumber)
				{
					playerHand.getChildren().remove(playerCards[index]); //remove the option to pick that button at the index
					playerNumCards++; //increment to keep track of how many cards the player has used
				}
				
				president.aiPlayerTurn(president.aiPlayer1); //call the ai players turns
				if((president.aiPlayer1.playerHand.Hand[president.aiPlayerChoice].cardNumber == president.pileDeck[0].cardNumber) && ai1NumCards < 13) //if the top card is theirs and the ai's still have cards in hand 
				{
					ai1PlayerHand.getChildren().remove(0); //remove a card image from their hand
					ai1NumCards++; //and increment to keep track of how many cards they have used
				}
				if(ai1NumCards == 13) //if they finish before the player give the ai their rank
					president.checkPlayerRank("aiPlayer1");
				
				president.aiPlayerTurn(president.aiPlayer2);
				if((president.aiPlayer2.playerHand.Hand[president.aiPlayerChoice].cardNumber == president.pileDeck[0].cardNumber) && ai2NumCards < 13)
				{
					ai2PlayerHand.getChildren().remove(0);
					ai2NumCards++;
				}
				if(ai2NumCards == 13)
					president.checkPlayerRank("aiPlayer2");
				
				president.aiPlayerTurn(president.aiPlayer3);
				if((president.aiPlayer3.playerHand.Hand[president.aiPlayerChoice].cardNumber == president.pileDeck[0].cardNumber) && ai3NumCards < 13)
				{
					ai3PlayerHand.getChildren().remove(0);
					ai3NumCards++;
				}
				if(ai3NumCards == 13)
					president.checkPlayerRank("aiPlayer3");
				
				if(playerNumCards == 13 && ai1NumCards != 13 && ai2NumCards != 13 && ai3NumCards != 13) //if the player finishes first end the game and assign their ranks
					try {
						gameEnd(stage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if(playerNumCards == 13 && (ai1NumCards == 13 || ai2NumCards == 13 || ai3NumCards == 13)) //else if the player is done and any other ai are done end game and assign their ranks
					try {
						gameEnd(stage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				president.gameSession(); //call to check if the current pile within the round is still valid
				updateTopCard(); //update the image of the top card
			});
		}
		return root; //return the Pane
	}

	private void updateTopCard() 
	{
		pCards.getChildren().remove(0); //removes the old image for the top card
		Image topCard = new Image(new File(PicturePath + president.pileDeck[0].cardNumber + president.pileDeck[0].cardSuit + ".png").toURI().toString(), 50, 80, false, false); //loads the new image
		ImageView topCardView = new ImageView(topCard);
		pCards.getChildren().add(topCardView); //adds the new top card image
	}

	private void gameEnd(Stage stage) throws Exception 
	{
		president.checkPlayerRank("player"); //assigns the players ranks
		president.checkPlayerRank("aiPlayer1");
		president.checkPlayerRank("aiPlayer2");
		president.checkPlayerRank("aiPlayer3");
		
		String dialog = new String();
		
		for(int i = 0; i < 4; i++) //picks the correct dialog based on players ranking
		{
			if(president.socialRank[0] == "player")
				dialog = "Good game! You are the President!!!\nPlay again?";
			if(president.socialRank[1] == "player")
				dialog = "Good game! You are the Vice-President!!!\nPlay again?";
			if(president.socialRank[2] == "player")
				dialog = "Good game! You are the Vice-Scum.\nPlay again?";
			if(president.socialRank[3] == "player")
				dialog = "Good game! You are the Scum...\nPlay again?";
		}
		
		Alert endMsg = new Alert(AlertType.NONE, dialog, ButtonType.YES, ButtonType.NO); //open a new alert box asking the player if the would like to play again
		Optional<ButtonType> choice = endMsg.showAndWait();
		ButtonType btn = choice.orElse(ButtonType.NO);
		
		if(btn == ButtonType.YES) //if they hit yes then close the current window, reset the president class, and create a new stage
		{
			stage.close();
			president = new President();
			start(new Stage());
		}
		else
		{
			stage.close(); //else if they choose to quit then close the window and end the game
		}
	}

	@Override
	public void start(Stage presidentStage) throws Exception 
	{
		// TODO Auto-generated method stub
		presidentStage.setScene(new Scene(createWindow(presidentStage)));
		presidentStage.setWidth(800);
		presidentStage.setHeight(600);
		presidentStage.setResizable(false);
		presidentStage.setTitle("President");
		presidentStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}

}