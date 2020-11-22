import java.io.File;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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


public class Crazy8sGUI extends Application
{

    Crazy8s crazy8s = new Crazy8s();  //used to add methods in crazy8s() class

    public HBox playerHand;  //player cards
    public Button[] playerCards; //button to toss a matching card
    public Button[] addCard;  //button to add a card when no match
	int playerNumCards;  //number of player cards
	HBox pCards;  //discard pile of cards (?)
	int rounds = 0;

	public Crazy8sGUI() throws Exception
	{

	}

	public Parent createWindow(Stage stage) throws Exception
	{
		Pane root = new Pane();
		root.setPrefSize(800, 600); //sets the size of the scene to the window created

		Region backGround = new Region();
		backGround.setPrefSize(800, 600);
		backGround.setStyle("-fx-background-color: rgba(0, 0, 0, 1)"); //sets the background to black

		playerHand = new HBox(5); //initialize necessary variables

		playerNumCards = 0;

		pCards = new HBox();

		HBox rootDesign = new HBox(5); //create a HBox and set spacing between nodes to 5
		rootDesign.setPadding(new Insets(1, 1, 1, 1)); //set a small thin transparent border across the frame


		Rectangle leftFrame = new Rectangle(550, 560); //make the left frame bigger then the right frame
		leftFrame.setFill(Color.GREEN);//set the colour to the left frame to green
		Rectangle rightFrame = new Rectangle(230, 560); //set the sizing for the right frame which is smaller then the left frame
		rightFrame.setFill(Color.DARKOLIVEGREEN); //set the colour to the right frame to a dark olive green

		//Left frame containing the cards of each player

		playerCards = new Button[crazy8s.player.playerHand.Hand.length]; //the players cards will be buttons that show the image of the card its index is at and are intractable

		for (int i = 0; i < playerCards.length; i++) {
			File file = new File("C:\\Users\\Katpa\\Documents\\GitHub\\Ace-of-Spades\\Cards\\" + crazy8s.player.playerHand.Hand[i].cardNumber + " of " + crazy8s.player.playerHand.Hand[i].cardSuit + ".png");
			Image cards = new Image(file.toURI().toString(), 35, 60, false, true); //create a new image for each card so that each node is unique with the parent node having no duplicates
			ImageView cardsView = new ImageView(cards);
			playerCards[i] = new Button("", cardsView);
			playerCards[i].setMinSize(35, 60); //set the sizing of the buttons to be the same size as the image of the cards so that they are flush
			playerCards[i].setMaxSize(35, 60);
		}

		playerHand.getChildren().addAll(playerCards); //add all the buttons to the playerHand box

		Text player = new Text("Player");

		VBox leftVBox = new VBox(15);
		leftVBox.setAlignment(Pos.TOP_LEFT); //align all the contents to the top left
		leftVBox.getChildren().addAll(player, playerHand); //add in all the contents of the HBoxs to one bug VBox to create the left frame

		//Right frame containing the pile of cards

		VBox rightVBox = new VBox(25);
		rightVBox.setAlignment(Pos.CENTER);

		Image discardPile = new Image(new File("C:\\Users\\Katpa\\Documents\\GitHub\\Ace-of-Spades\\Cards\\" + crazy8s.discardPile[0].cardNumber + " of " + crazy8s.discardPile[0].cardSuit + ".png").toURI().toString(), 50, 80, false, true);
		ImageView pileCardsView = new ImageView(discardPile); //get the image for the current top card in the pile deck
		pCards.getChildren().add(pileCardsView);
		pCards.setAlignment(Pos.CENTER);

		Dialog<String> infoText = new Dialog<String>(); //create the dialog for the information button
		infoText.setTitle("How to play"); //set the title, and the content of how to play the game and what the objective is
		infoText.setContentText("Welcome to Crazy8s! the objective is to get rid of all the cards in your hand.\nIf you place an ace down the pile resets.\nThe game has started and the president has given their worst 2 cards for the scums best 2 cards, and the vice-president has traded their worst card for the vice-scums best card.\n1st place is given the title president, 2nd is vice-president, 3rd is vice-scum, and 4th place is the scum\nIf you wish to skip or have no playable cards, then click any of your cards as long as its number is less then the current top card.\nGood luck!");
		ButtonType infobtn = new ButtonType("Ok", ButtonData.OK_DONE); //set the dialog box to close once the player hits the ok button
		infoText.getDialogPane().getButtonTypes().add(infobtn);
		Button howToPlay = new Button("Info");

		rightVBox.getChildren().addAll(pCards, howToPlay); //add the contents to the right VBox

		rootDesign.getChildren().addAll(new StackPane(leftFrame, leftVBox), new StackPane(rightFrame, rightVBox)); //create the design by adding the preferences for each frame, and its contents
		root.getChildren().addAll(backGround, rootDesign);

		howToPlay.setOnAction(event -> { //set the event handler when the button is clicked
			infoText.showAndWait(); //window opens and waits for the player to close it
		});

		//for loop goes here??
		for(int i = 0; i< playerCards.length; i++)
		{
			int index = i;

			playerCards[i].setOnAction(event -> {

				//president.playerTurn();
				if(crazy8s.player.playerHand.Hand[index].cardNumber == crazy8s.discardPile[0].cardNumber)
				{
					playerHand.getChildren().remove(playerCards[index]);
					playerNumCards--;
					rounds++;
				}

				if(playerNumCards == 0)
				{
					try {
						gameEnd(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				else if(rounds<20)
				{
					try {
						gameEnd(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		//ADD BUTTON FOR ADDING NEW CARDS!

		return root;

	}

	private void updateTopCard()
	{
		pCards.getChildren().remove(0); //removes the old image for the top card
		Image topDiscardCard = new Image(new File("C:\\Users\\Katpa\\Documents\\GitHub\\Ace-of-Spades\\Cards\\" + crazy8s.discardPile[0].cardNumber + " of " + crazy8s.discardPile[0].cardSuit + ".png").toURI().toString(), 50, 80, false, false); //loads the new image
		ImageView topCardView = new ImageView(topDiscardCard);
		pCards.getChildren().add(topCardView); //adds the new top card image
	}

	private void gameEnd(Stage stage) throws Exception //CHANGE THIS
	{
		String message = new String();
		//do something here?
		if(crazy8s.emptyHandWin()){
			message = "Congratulations! You won the game!\nThanks for Playing!\nPlay Again?";
		}
		else if(!crazy8s.winner)      {
			message = "Oh No! AI won...\nThanks for playing!\nPlay again?";
		}

		Alert endMsg = new Alert(AlertType.NONE, message, ButtonType.YES, ButtonType.NO); //open a new alert box asking the player if the would like to play again
		Optional<ButtonType> choice = endMsg.showAndWait();
		ButtonType btn = choice.orElse(ButtonType.NO);
			
		if(btn == ButtonType.YES) //if they hit yes then close the current window, reset the president class, and create a new stage
		{
			stage.close();
			crazy8s = new Crazy8s();
			start(new Stage());
		}
		else
		{
			stage.close(); //else if they choose to quit then close the window and end the game
		}
	}

	@Override
	public void start(Stage crazy8sStage) throws Exception 
	{
		// TODO Auto-generated method stub
		crazy8sStage.setScene(new Scene(createWindow(crazy8sStage)));
		crazy8sStage.setWidth(800);
		crazy8sStage.setHeight(600);
		crazy8sStage.setResizable(false);
		crazy8sStage.setTitle("Crazy8s");
		crazy8sStage.show();
	}
    public static void main(String[] args) 
	{
		launch(args);
	}
}
