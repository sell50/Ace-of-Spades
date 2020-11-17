import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Application 
{
	
	//Button Objects
	Button SpoonsBTN, Crazy8sBTN, PokerBTN, PresidentBTN, ExitBTN;
	
	//Image Objects
	InputStream stream;
	Image image;
	ImageView IV1 = new ImageView();
	ImageView IV2 = new ImageView();
	ImageView IV3 = new ImageView();
	ImageView IV4 = new ImageView();
	String PicturePath = "C:\\Users\\Brett\\eclipse-workspace\\Ace of Spades\\Cards\\PNG\\";
	
	//Image Variables
	int xBase = 10;
	int yBase = 10;
	int xTranslation = 485;
	int yTranslation = 430;
	
	//Label Objects
	Label TitleLabel;
	Font TitleFont;
	
	//Game Objects
	//Spoons Spoons; Poker Poker; Crazy8s Crazy8s; President President;
	
	//Deck Object For Display
	DeckofCards DoC;
	DeckofCards deck;
	
	//Stage Objects
	Scene menuScene;
	Pane root;
	
	/*
	 * Name: Start Function
	 * Type: Void
	 * Description: This function creates the primaryStage object
	 * 				and sets the menuScene to display 
	 * */
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			SceneCreator();
			primaryStage.setScene(menuScene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Name: Image Display Function
	 * Type: Void
	 * Description: This function creates a DeckofCards object then takes four 
	 * 				random cards and creates a ImageView objects to hold the card images
	 * 				then displays them in the corners of the screen
	 * */
	public void ImageDisplay() throws Exception
	{
		DoC = new DeckofCards();
		
		for(int i = 0;i<4;i++)
		{	
			if(xBase == 10 && yBase == 10)
			{
				IV1.setImage(DoC.Deck[i].cardFront);
				
				IV1.setFitWidth(100);
				IV1.setFitHeight(150);
				IV1.setPreserveRatio(true);
				
				IV1.setX(xBase);
				IV1.setY(yBase);
				
				xBase+=xTranslation;
			}
			else if(xBase>10 && yBase == 10)
			{
				IV2.setImage(DoC.Deck[i].cardFront);
				
				IV2.setFitWidth(100);
				IV2.setFitHeight(150);
				IV2.setPreserveRatio(true);
				
				IV2.setX(xBase);
				IV2.setY(yBase);
				
				yBase+=yTranslation;
			}
			else if(xBase>10 && yBase>10)
			{
				IV3.setImage(DoC.Deck[i].cardFront);
				
				IV3.setFitWidth(100);
				IV3.setFitHeight(150);
				IV3.setPreserveRatio(true);
				
				IV3.setX(xBase);
				IV3.setY(yBase);
				
				xBase-=xTranslation;
			}
			else if(xBase==10 && yBase>10)
			{
				IV4.setImage(DoC.Deck[i].cardFront);
				
				IV4.setFitWidth(100);
				IV4.setFitHeight(150);
				IV4.setPreserveRatio(true);
				
				IV4.setX(xBase);
				IV4.setY(yBase);
				
				yBase-=yTranslation;
			}
		}
	}
	
	/*
	 * Name: Label Creator Function
	 * Type: Void
	 * Description: This function creates a label object, set the size, color, 
	 * 				position of the label
	 * */
	public void LabelCreator()
	{
		//Initializing the Label
		TitleLabel = new Label("Ace of Spades");
		
		//Setting Font of Label
		TitleFont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50);
		TitleLabel.setFont(TitleFont);
		TitleLabel.setTextFill(Color.web("#F9A602"));
		
		//Setting Size of Label
		TitleLabel.setPrefSize(400, 35);
		
		//Setting Position of Label
		TitleLabel.setTranslateX(125);
		TitleLabel.setTranslateY(60);
	}
	
	/*
	 * Name: Button Creator Function
	 * Type: Void
	 * Description: This function creates button objects, sets the size, color, 
	 * 				position, and actions of the buttons
	 * */
	public void ButtonCreator() throws Exception
	{
		//Initializing the Buttons
		SpoonsBTN = new Button("Spoons");
		Crazy8sBTN = new Button("Crazy8s");
		PokerBTN = new Button("Poker");
		PresidentBTN = new Button("President");
		ExitBTN = new Button("Exit ");
		
		//Setting the Button Colors
		SpoonsBTN.styleProperty().set("-fx-background-color: #7DA2A9");
		Crazy8sBTN.styleProperty().set("-fx-background-color: #7DA2A9");
		PresidentBTN.styleProperty().set("-fx-background-color: #7DA2A9");
		PokerBTN.styleProperty().set("-fx-background-color: #7DA2A9");
		ExitBTN.styleProperty().set("-fx-background-color: #7DA2A9");
		
		//Setting Button Size
		SpoonsBTN.setPrefSize(100, 20);
		PresidentBTN.setPrefSize(100, 20);
		Crazy8sBTN.setPrefSize(100, 20);
		PokerBTN.setPrefSize(100, 20);
		ExitBTN.setPrefSize(100, 20);
		
		//Setting the Position
		SpoonsBTN.setLayoutX(250);
		SpoonsBTN.setLayoutY(150);
		
		PokerBTN.setLayoutX(250);
		PokerBTN.setLayoutY(200);
		
		PresidentBTN.setLayoutX(250);
		PresidentBTN.setLayoutY(250);
		
		Crazy8sBTN.setLayoutX(250);
		Crazy8sBTN.setLayoutY(300);
		
		ExitBTN.setLayoutX(250);
		ExitBTN.setLayoutY(350);

		//Adding Actions to the Crazy8s Button
		Crazy8sBTN.setOnAction(e -> 
		{
			try 
			{
				//Start Crazy8s Game and Make the GUI Appear
				//Crazy8s = new Crazy8s();
			} 
			catch (Exception e1) 
			{}
		});
		
		//Adding Actions to the Poker Button
		PokerBTN.setOnAction(e -> 
		{
			try 
			{
				//Start Poker Game and Make the GUI Appear
				//Poker = new Poker();
				//Poker.startGame();
			} 
			catch (Exception e1) 
			{}
		});
		
		//Adding Actions to the President Button
		PresidentBTN.setOnAction(e -> 
		{
			try 
			{
				//Start President Game and Make the GUI Appear
				//President = new President();
			} 
			catch (Exception e1) 
			{}
		});
		
		//Adding Actions to the Spoons Button
		SpoonsBTN.setOnAction(e -> 
		{
			try 
			{
				//Start Spoons Game and Make the GUI Appear
				//Spoons = new Spoons();
				//Spoons.Start();
			} 
			catch (Exception e1) 
			{}
			
		});
		
		//Adding Actions to the Exit Button
		ExitBTN.setOnAction(e -> 
		{
			//Shutting Down the Program
			System.exit(0);
		});
	}
	
	/*
	 * Name: Scene Creator Function
	 * Type: Void
	 * Description: Runs functions to create components then initializes 
	 * 				a pane and adds the "Children" to it then sets that pane
	 * 				to the menuScene variable
	 * */
	public void SceneCreator() throws Exception
	{
		//Creating Components
		ButtonCreator();
		LabelCreator();
		ImageDisplay();
		
		//Setting Up Layout
		root = new Pane();
		
		//Adding all the "Children" components 
		root.getChildren().addAll(Crazy8sBTN,PokerBTN,PresidentBTN,SpoonsBTN,ExitBTN, TitleLabel, IV1, IV2, IV3, IV4);
		
		//Setting Color
		root.styleProperty().set("-fx-background-color: #18181E");
		
		//Creating the menuScene size 600x600
		menuScene = new Scene(root,600,600);	
	}
	
	/*
	 * Name: Main Function
	 * Type: Void
	 * Description: N/A
	 * */
	public static void main(String[] args) 
	{
		launch(args);
	}

}
