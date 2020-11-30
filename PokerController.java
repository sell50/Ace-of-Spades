import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PokerController implements Initializable {

    @FXML private Label RoundWinStatement;
    @FXML private Label roundCount;
    @FXML private Label CorrectionText;
    public Poker poker = new Poker();
    private int count;
    private int winCount;

    @FXML private Label Money;
    @FXML private Label TotalBet;
    @FXML private Label CurrentBet;
    @FXML private Label AiNumber;


    @FXML private ImageView BackGround;

    @FXML private ImageView CommunityCard1;
    @FXML private ImageView CommunityCard2;
    @FXML private ImageView CommunityCard3;
    @FXML private ImageView CommunityCard4;
    @FXML private ImageView CommunityCard5;

    @FXML private ImageView PlayerCard1;
    @FXML private ImageView PlayerCard2;

    @FXML private Label player;
    @FXML private Button fold;
    @FXML private Button check;
    @FXML private TextField bet;
    
    String PicturePath = "C:\\Users\\Brett\\eclipse-workspace\\Ace of Spades\\Cards\\PNG\\";

    private int i=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image x = new Image(PicturePath+"CardBack.png");
        count=1;//counts the number of rounds that have occured
        RoundWinStatement.setText("Number of Rounds Won: " + winCount);

        poker.distributeCards();

        CorrectionText.setText("Minimum Bet 200");
        CommunityCard1.setImage((x));
        CommunityCard2.setImage((x));
        CommunityCard3.setImage((x));
        CommunityCard4.setImage((x));
        CommunityCard5.setImage((x));

        PlayerCard1.setImage((poker.hands[0][0].cardFront));
        PlayerCard2.setImage((poker.hands[0][1].cardFront));

        winCount=0;//counts the number of wins

        Money.setText("Money: $"+poker.amount[0][0]);
        poker.aiTurn();

    }

    public void reset(){
        if(count<3 && poker.amount[0][0] > 200) {
            //winCount++;
            poker.reset();

            CorrectionText.setText("Minimum Bet 200");
            RoundWinStatement.setText("Number of Rounds Won: " + winCount);
            TotalBet.setText("Total Bet: $0");//this.amount[0][1]
            CurrentBet.setText("Current Bet: $0");
            AiNumber.setText("Number of AI: 5");

            count++;
            poker.distributeCards();


            Image x = new Image(PicturePath+"CardBack.png");
            roundCount.setText("Round " + count +":");
            CommunityCard1.setImage((x));
            CommunityCard2.setImage((x));
            CommunityCard3.setImage((x));
            CommunityCard4.setImage((x));
            CommunityCard5.setImage((x));

            PlayerCard1.setImage((poker.hands[0][0].cardFront));
            PlayerCard2.setImage((poker.hands[0][1].cardFront));

            poker.aiTurn();
            i = 0;

        }
        else{
            System.out.println("Thanks for playing");
            System.exit(0);
        }

    }

    public void betting(){

        String x=bet.getText();
        int y=Integer.parseInt(x);//takes the amount the user bets and stores it in this

        if(i==0 && y>=200 && poker.amount[0][0] > y ){
            poker.amount[0][1]=y+poker.amount[0][1];
            poker.amount[0][0]=poker.amount[0][0] - poker.amount[0][1];
            poker.aiBet();
            i++;
            CorrectionText.setText("");
            AiNumber.setText("Number of AI: "+poker.aiCounter);
        }

        else if(i==0 && y<200){
            CorrectionText.setText("Minimum Bet 200");

        }

        else if(poker.amount[0][0] > 0 && y <= poker.amount[0][0]){//they have money to bet and they are betting less thaan what they have
            poker.amount[0][1]=y+poker.amount[0][1];
            poker.amount[0][0]=poker.amount[0][0] - y;
            poker.aiBet();
        }

        else{
            CorrectionText.setText("Enter the correct amount");
        }
        TotalBet.setText("Total Bet: $"+poker.totalBet);
        Money.setText("Money: $"+poker.amount[0][0]);
        CurrentBet.setText("Current Bet: "+poker.amount[0][1]);



        //System.out.println(y);

    }

    public void foldButtonClick(){
        reset();
    }
    public void checkButtonClick(){
        Image x = new Image("./JPEG/KH.jpg");
        //System.out.println("d");
        if(i==0){
            CorrectionText.setText("Minimum Bet 200");

        }

        if(i==1) {
            //System.out.println(poker.hands[6][0].cardNumber);
            CommunityCard1.setImage((poker.hands[6][0].cardFront));
            CommunityCard2.setImage((poker.hands[6][1].cardFront));
            CommunityCard3.setImage((poker.hands[6][2].cardFront));
            i++;
            CorrectionText.setText("");
        }
        else if(i==2){
            CommunityCard4.setImage((poker.hands[6][3].cardFront));
            i++;
            CorrectionText.setText("");
        }
        else if(i==3){
            CommunityCard5.setImage((poker.hands[6][4].cardFront));
            i++;
            CorrectionText.setText("");
        }
        else if(i==4){
            int temp = poker.round4();
            CorrectionText.setText("");
            if(temp==0){
                winCount++;
                poker.amount[0][0]=poker.amount[0][0]+poker.totalBet;
                reset();
            }else if(temp==1) {
                reset();
            }
        }
    }
}