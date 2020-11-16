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

public class MainMenu extends Application {
	
	//Button Objects
	Button SpoonsBTN, Crazy8sBTN, PokerBTN, PresidentBTN, ExitBTN;
	
	//Image Objects
	ImageView[] imageViewArray;
	InputStream stream;
	Image image;
	ImageView IV = new ImageView();
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
	Spoons Spoons; Poker Poker; Crazy8s Crazy8s; President President;
	
	//Helper Objects
	DeckofCards DoC;
	DeckofCards deck;
	
	//Stage Objects
	Scene menuScene;
	Pane root;
	
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
	
//	public void HandDisplay() throws Exception
//	{
//		imageViewArray = new ImageView[99];
//		deck = new DeckofCards();
//		
//		//Loop for Each Card in Hand
//		for(int i = 0; i < deck.Deck.length; i++)
//		{
//			IV.setImage(deck.Deck[i].cardFront);
//			IV.setFitWidth(100);
//			IV.setFitHeight(150);
//			IV.setPreserveRatio(true);
//			
//			IV.setX(xBase);
//			IV.setY(yBase);
//			
//			xBase+=5;
//			
//			imageViewArray[i] = IV;
//			
//			System.out.println(deck.Deck[i].cardNumber+deck.Deck[i].cardSuit);
//			
//		}
//		for(int i = 0;i<imageViewArray.length;i++) 
//		{
//			root.getChildren().add(imageViewArray[i]);
//		}
//	}
	
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
	
	
	public void ButtonCreator() throws Exception
	{
		//Initializing the Buttons
		SpoonsBTN = new Button("Spoons");
		Crazy8sBTN = new Button("Crazy8s");
		PokerBTN = new Button("Poker");
		PresidentBTN = new Button("President");
		ExitBTN = new Button("Exit ");
		
		//
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

		
		//Adding Actions
		Crazy8sBTN.setOnAction(e -> 
		{
			try 
			{
				Crazy8s = new Crazy8s();
			} 
			catch (Exception e1) 
			{}
		});
		
		PokerBTN.setOnAction(e -> 
		{
			try 
			{
				Poker = new Poker();
			} 
			catch (Exception e1) 
			{}
			Poker.startGame();
		});
		
		PresidentBTN.setOnAction(e -> 
		{
			try 
			{
				President = new President();
			} 
			catch (Exception e1) 
			{}
		});
		
		SpoonsBTN.setOnAction(e -> 
		{
			try 
			{
				Spoons = new Spoons();
				Spoons.Start();
			} 
			catch (Exception e1) 
			{}
			
		});
		
		ExitBTN.setOnAction(e -> 
		{
			System.exit(0);
		});
	}
	
	public void SceneCreator() throws Exception
	{
		//Creating Components
		ButtonCreator();
		LabelCreator();
		ImageDisplay();
		
		//Setting Up Layout
		root = new Pane();
		
		//HandDisplay();
		
		//
		root.getChildren().addAll(Crazy8sBTN,PokerBTN,PresidentBTN,SpoonsBTN,ExitBTN, TitleLabel, IV1, IV2, IV3, IV4);
		
		root.styleProperty().set("-fx-background-color: #18181E");
		
		menuScene = new Scene(root,600,600);
			
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}

}
