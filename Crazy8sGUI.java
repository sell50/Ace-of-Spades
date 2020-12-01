import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class Crazy8sGUI extends Application implements EventHandler<ActionEvent>
{

    Crazy8s crazy8s;  //used to add methods in crazy8s() class

    //needed variables
    public VBox format1; //outside vbox
    public VBox format2; //top vbox
    public VBox format3; //bottom vbox
    
    public HBox textBox; //Horizontal box for messages... can change?
    public HBox playerHand; //Horizontal box for hand
    public HBox discardPile; //Horizontal box for discard pile
    public HBox addCardButton;
    
    public Text yourCardsText; //Text to put into textBox (HBox) over playerHand
    
    public Button[] playerCards; //Buttons for playerHand
    public Button addCard; //Button to add a new card to hand
    
    public Image card; //Needed to display image of cards;
    public String message; //Shows messages on screen
    int numPlayerCards; //Sets the number of player cards in hand
    
    String PicturePath = "C:\\Users\\Brett\\eclipse-workspace\\Ace of Spades\\Cards\\PNG\\";

	public Crazy8sGUI() throws Exception
	{
		crazy8s = new Crazy8s();
	}

	public Parent createWindow(Stage stage) throws Exception
	{
		//root
		Pane root = new Pane(); //Sets the scene
		root.setPrefSize(800, 600); //Sets scene size
		
		root.styleProperty().set("-fx-background-color: rgba(48, 89, 54, 1)"); //Sets the background colour to dark green
		
		//initialize vbox
		format1 = new VBox();
		format2 = new VBox();
		format3 = new VBox();
		
		//initialize hbox
		textBox = new HBox();
		playerHand = new HBox(5); 
		discardPile = new HBox(); 
		//TAKE THIS OUT
		addCardButton = new HBox();
		
		//Texts on screen
		yourCardsText = new Text("Your Cards"); //Adds text on top of player cards
		
		//playerCards = new Button[10];	
		playerCards = new Button[crazy8s.player.playerHand.Hand.length]; //Player cards are buttons equal to the length of the hand
		
		//TAKE THIS OUT
		addCard = new Button("Add Card"); //Creates a button to add a card to hand
		
		numPlayerCards = 0;
		
		//Player card images
		System.out.println(playerCards.length); //test
		
		for(int i = 0; i < playerCards.length; i++)
		{
			System.out.println("*"+crazy8s.player.playerHand.Hand[i].cardNumber + crazy8s.player.playerHand.Hand[i].cardSuit); //test
			File file = new File(PicturePath + crazy8s.player.playerHand.Hand[i].cardNumber + crazy8s.player.playerHand.Hand[i].cardSuit + ".png");
			Image cards = new Image(file.toURI().toString(), 35, 60, false, true); //create a new image for each card so that each node is unique with the parent node having no duplicates
			ImageView cardsView = new ImageView(cards);
			playerCards[i] = new Button("", cardsView);
			playerCards[i].setOnAction(this);
			playerCards[i].setMinSize(35, 60); //set the sizing of the buttons to be the same size as the image of the cards so that they are flush
			playerCards[i].setMaxSize(35, 60);
		}
		
		//Discard pile images
		System.out.println(crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit); //test
		Image pileCards = new Image(new File(PicturePath + crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit + ".png").toURI().toString(), 50, 80, false, true);
		ImageView pileCardsView = new ImageView(pileCards); //get the image for the current top card in the pile deck
		
		//HBox set
		textBox.getChildren().add(yourCardsText);
		textBox.setAlignment(Pos.CENTER);
		playerHand.getChildren().addAll(playerCards);
		playerHand.setAlignment(Pos.CENTER);
		discardPile.getChildren().add(pileCardsView);
		discardPile.setAlignment(Pos.CENTER);
		
		//TAKE THIS OUT
		addCardButton.getChildren().add(addCard);
		
		//VBox set
		format1.getChildren().addAll(textBox, playerHand);
		format1.setAlignment(Pos.CENTER);
		format2.getChildren().addAll(discardPile,addCardButton);
		format2.setAlignment(Pos.CENTER);
		format3.getChildren().addAll(format1, format2);
		format3.setAlignment(Pos.CENTER);
		
		//root set
		root.getChildren().addAll(format3);
		
		//if no match
		
		if(crazy8s.currentDeckCard!=crazy8s.deck.Deck.length) {
			
			System.out.println("Got to no match!!");
			
			playerHand.getChildren().removeAll(playerCards);
			//update new hand?
			
			playerCards = new Button[crazy8s.player.playerHand.Hand.length];
			
			for(int i = 0; i < playerCards.length; i++)
			{
				
				File file = new File(PicturePath + crazy8s.player.playerHand.Hand[i].cardNumber +  crazy8s.player.playerHand.Hand[i].cardSuit + ".png");
				Image cards = new Image(file.toURI().toString(), 35, 60, false, true); //create a new image for each card so that each node is unique with the parent node having no duplicates
				ImageView cardsView = new ImageView(cards);
				playerCards[i] = new Button("", cardsView);
				playerCards[i].setOnAction(this);
				playerCards[i].setMinSize(35, 60); //set the sizing of the buttons to be the same size as the image of the cards so that they are flush
				playerCards[i].setMaxSize(35, 60);
			}
			
			//add it to scene
			
			playerHand.getChildren().addAll(playerCards);
		}
		
		return root; //Returns to scene pane
	}
	
	//clicking anything
	@Override
	public void handle(ActionEvent e){
		if(e.getSource()==playerCards[0]) {
			try {
				
				if(crazy8s.cardClicked(0)) {
					System.out.println("got to first CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(0);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		
		
		else if(e.getSource()==playerCards[1]) {
			try {
				if(crazy8s.cardClicked(1)) {
					System.out.println("got to second CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(1);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==playerCards[2]) {
			try {
				
				if(crazy8s.cardClicked(2)) {
					System.out.println("got to third CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(2);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==playerCards[3]) {
			try {
				
				if(crazy8s.cardClicked(3)) {
					System.out.println("got to fourth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(3);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==playerCards[4]) {
			try {
				if(crazy8s.cardClicked(4)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(4);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource()==playerCards[5]) {
			try {
				if(crazy8s.cardClicked(5)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(5);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==playerCards[6]) {
			try {
				if(crazy8s.cardClicked(6)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(6);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==playerCards[7]) {
			try {
				if(crazy8s.cardClicked(7)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(7);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==playerCards[8]) {
			try {
				if(crazy8s.cardClicked(8)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(8);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==playerCards[9]) {
			try {
				if(crazy8s.cardClicked(9)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(9);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==playerCards[10]) {
			try {
				if(crazy8s.cardClicked(10)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(10);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==playerCards[11]) {
			try {
				if(crazy8s.cardClicked(11)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(11);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==playerCards[12]) {
			try {
				if(crazy8s.cardClicked(12)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(12);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==playerCards[13]) {
			try {
				if(crazy8s.cardClicked(13)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(13);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==playerCards[14]) {
			try {
				if(crazy8s.cardClicked(14)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(14);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==playerCards[15]) {
			try {
				if(crazy8s.cardClicked(15)) {
					System.out.println("got to fifth CardClicked!");
					updateTopCard();
					playerHand.getChildren().remove(15);
					endGame();
					System.out.println("New top card: " +crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit);
				}
				else {
					addCard();
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public void endGame() {
		if(crazy8s.rounds==10) {
			message="Oh No! AI Won! Game Over!";
			yourCardsText.setText(message);
			System.exit(0);
			
		}
	}
	
	public void addCard() {
		if(crazy8s.currentDeckCard!=crazy8s.deck.Deck.length) {
					
			System.out.println("Got to no match!!");
					
			playerHand.getChildren().removeAll(playerCards);
			//update new hand?
					
			playerCards = new Button[crazy8s.player.playerHand.Hand.length];
					
			for(int i = 0; i < playerCards.length; i++)
			{
						
				File file = new File(PicturePath + crazy8s.player.playerHand.Hand[i].cardNumber +  crazy8s.player.playerHand.Hand[i].cardSuit + ".png");
				Image cards = new Image(file.toURI().toString(), 35, 60, false, true); //create a new image for each card so that each node is unique with the parent node having no duplicates
				ImageView cardsView = new ImageView(cards);
				playerCards[i] = new Button("", cardsView);
				playerCards[i].setOnAction(this);
				playerCards[i].setMinSize(35, 60); //set the sizing of the buttons to be the same size as the image of the cards so that they are flush
				playerCards[i].setMaxSize(35, 60);
			}
					
			//add it to scene
					
			playerHand.getChildren().addAll(playerCards);
		}
	}
	
	public void updateTopCard()
	{
		discardPile.getChildren().remove(0); //removes the old image for the top card
		Image topCard = new Image(new File(PicturePath + crazy8s.discardPile[0].cardNumber + crazy8s.discardPile[0].cardSuit + ".png").toURI().toString(), 50, 80, false, false); //loads the new image
		ImageView topCardView = new ImageView(topCard);
		discardPile.getChildren().add(topCardView); //adds the new top card image
	}

	//@Override
	public void start(Stage crazy8sStage) throws Exception 
	{
		crazy8s = new Crazy8s();
		//This line calls a function to set up the entire scene
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