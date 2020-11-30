import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.Insets;
import java.awt.Label;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpoonsGUI extends Application implements EventHandler<ActionEvent>{
	
	Spoons spoons;
	
	public HBox playerHand;
	public HBox textBox;
	public VBox format;
	public Text titleText;
	public Button[] playerCards;
	public Image card;
	public String message;
	
	String PicturePath = "C:\\Users\\Brett\\eclipse-workspace\\Ace of Spades\\Cards\\PNG\\";
	
	public SpoonsGUI() 
	{
		spoons = new Spoons();
	}
	
	public Parent createWindow(Stage stage){
		
		//Text on top of scene
		message = "Select a card to discard until you get Four of a Kind";
		//The pane that will be added onto the scene
		Pane root = new Pane();
		root.styleProperty().set("-fx-background-color: #66CD00");
		
		//Box for text
		textBox = new HBox();
		
		//Stores the 4 cards as buttons
		playerCards = new Button[4];
		
		//The box that will contain the buttons
		playerHand = new HBox();
		
		//Used for formatting; will put the two HBoxes in here
		format = new VBox();
		format.setAlignment(Pos.CENTER);
	    format.setSpacing(50);
		
		//Find the card image and add it to each button
		for(int i = 0; i <= 3; i++) {
			File file = new File(PicturePath + spoons.player.playerHand.Hand[i].cardNumber + spoons.player.playerHand.Hand[i].cardSuit + ".png");
			card = new Image(file.toURI().toString(), 35, 60, false, true);
			ImageView view = new ImageView(card);
			playerCards[i] = new Button("", view);
			playerCards[i].setOnAction(this);
			playerCards[i].setMinSize(125, 200);
			playerCards[i].setMaxSize(125, 200);
		}
		
		titleText = new Text("Select a card to discard until you get Four of a Kind");
		titleText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 23.8));	
		
		//Adds everything onto the scene
		//(((titleText --> textBox) + (playerCards --> playerHand)) --> format) --> root
		textBox.getChildren().add(titleText);
		textBox.setAlignment(Pos.TOP_CENTER);
		playerHand.getChildren().addAll(playerCards);
		playerHand.setAlignment(Pos.CENTER);
		//VBox formatting HBoxes
		format.getChildren().addAll(textBox, playerHand);
		root.getChildren().addAll(format);
		
		return root;
	}
    
	//Starts the entire application as a whole
	//Used for testing purposes
    public static void main(String[] args) throws Exception{
    	launch();    	
    	System.exit(0);
    }


	public void start(Stage primaryStage) throws Exception {
		spoons = new Spoons();
		//This line calls a function to set up the entire scene
		primaryStage.setScene(new Scene(createWindow(primaryStage)));
		primaryStage.setWidth(600);
		primaryStage.setHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Spoons");
		primaryStage.show();
	}
	
	@Override
	public void handle(ActionEvent e) {
		/* When a button is clicked, it will call on Rounds() to check for a winning condition and update cards
		 * It will then update the image on the button
		 * Lastly, it will update the text on the screen if win/lose condition is found
		 */
		//If Button 1 is pressed
		if(e.getSource()==playerCards[0]) {
			try {
				spoons.Rounds(1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			updateMessage(0);
			
		//If Button 2 is pressed
		} else if(e.getSource()==playerCards[1]) {
			try {
				spoons.Rounds(2);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			updateMessage(1);

			
		//If Button 3 is pressed
		} else if(e.getSource()==playerCards[2]) {
			try {
				spoons.Rounds(3);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			updateMessage(2);
			
		//If Button 4 is pressed
		} else if(e.getSource()==playerCards[3]) {
			try {
				spoons.Rounds(4);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			updateMessage(3);
		}
	}
	
	//Updates card when pressed and the message if applicable from Spoons
	public void updateMessage(int i) {
		File file = new File(PicturePath + spoons.player.playerHand.Hand[i].cardNumber + spoons.player.playerHand.Hand[i].cardSuit + ".png");
		card = new Image(file.toURI().toString(), 35, 60, false, true);
		ImageView view = new ImageView(card);
		playerCards[i].setGraphic(view);
		message = spoons.win;
		titleText.setText(message);
	}
}