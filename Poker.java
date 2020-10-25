import java.util.Scanner;
import java.util.Random;


public class Poker {

    DeckofCards pokerDeck ;
    Card [][] hands = new Card [7][];// holds the players and AIs hands along with the cards that are currently on the table
    int [][] amount = new int [6][3] ;//holds both the amount that the ai and player have to bet and the amount that is currently being bet and the fold status 0 being not folded and 1 being folded
    int totalBet;

    public Poker(){ // initialising all the values

        pokerDeck = new DeckofCards();

        for (int i = 0; i < 6 ; i++){
            hands [i] = new Card [2];
        }

        hands [6] = new Card [5];

        amount [0] [0] = 10000; // row 1 is how much they can bet and part 2 is how much they have already bet and row 3 is fold status
        amount [0] [1] = 0;
        amount [0] [2] = 0;

        totalBet=0;

        for (int i = 1; i < 6 ; i++){
            amount [i] [0] = 1000000; // row 1 is how much they can bet and part 2 is how much they have already bet and row 3 is fold status
            amount [i] [1] = 0;
            amount [i] [2] = 0;
        }
    }
////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////


    public void startGame(){
        Scanner sc=new Scanner(System.in);


        boolean fold = false;
        int temp = 0, winCount=0;

        for(int i=0; i<3;i++) {//loops 3 times so gives the user 3 rounds to play

            System.out.println("Round :"+(i+1));
            this.distributeCards();
            //this.points(this.hands[0]);

            this.playerCards();
            fold = this.round1();
            this.aiTurn();
            if (fold == false){

                this.displayCommunity(3);
                this.playerCards();
                fold = this.round2();
            }

            if (fold == false){

                this.displayCommunity(4);
                this.playerCards();
                fold = this.round3();
            }

            if (fold == false){

                this.displayCommunity(5);
                this.playerCards();
                temp = this.round4();//returns 0 if user wins and 1 if ai wins
            }
            if(temp == 0){
                System.out.println("You have won.");
                amount[0][0]=amount[0][0]+this.totalBet;
                winCount++;
            }else{
                System.out.println("You Lost the round.");
            }
            this.reset();

        }
        System.out.println("You won "+winCount+" times.");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    //the section for cards

    public void reset(){

        for(int i=0;i<6;i++){
            this.amount [i] [1] = 0;
            this.amount [i] [2] = 0;

        }
        this.totalBet=0;

    }

    public void distributeCards(){

        int temp=0;

        for(int i = 0; i<6;i++){//fills up the player and ai hands
            for(int j = 0; j<2;j++){
                this.hands[i][j] = pokerDeck.Deck[temp];
                temp++;
            }
        }

        for(int j = 0; j<5;j++){//fills up the community hands
            this.hands[6][j] = pokerDeck.Deck[temp];
            temp++;
        }

        pokerDeck.ShuffleDeck(pokerDeck.Deck);//shuffles the cards after hands have been delt


    }

    public void displayCards(int j){

        for(int i = 0;i<this.hands[j].length;i++){
            displayCard(this.hands[j][i]);
        }
        System.out.println(" ");
    }

    public void displayCommunity (int j){

        System.out.println("Community Cards: ");

        for(int i = 0;i<j;i++){
            displayCard(this.hands[6][i]);
        }
        System.out.println(" ");
    }

    public void displayCard(Card currentCard){

        System.out.print("Card: ");

        if(currentCard.cardNumber==12){
            System.out.print("Jack ");
        }else if(currentCard.cardNumber == 13){
            System.out.print("Queen ");
        }else if(currentCard.cardNumber == 14){
            System.out.print("King ");
        }else {
            System.out.print(currentCard.cardNumber + " ");
        }

        System.out.println("of " + currentCard.cardSuit);

    }

    public void playerCards(){
        System.out.println("Your cards: ");
        this.displayCards(0);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    //this block is for ai related things

    public void aiTurn(){//decided wether the ai are going to play out the game or just fold


        for(int i=1;i<6;i++){

            Random rand = new Random();
            int coinFlip= rand.nextInt(100) + 1;

            if(coinFlip>50){//the computer will bluff and keep up with the player
                this.amount[i][2]=0;
            }else{
                if(this.hands[i][0].cardNumber<4||this.hands[i][1].cardNumber<4){
                    this.amount[i][2]=1;
                    System.out.println("AI Number "+i+" has folded");
                }else{
                    this.amount[i][2]=0;
                }

            }

        }

    }

    public void aiBet(){

        for(int i=1;i<6;i++){

            if(this.amount[i][2]==0){
                this.amount[i][1]=this.amount[0][1];
                this.amount[i][0]=this.amount[i][0] - this.amount[i][1];
                this.totalBet=this.totalBet+this.amount[0][1];
            }
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    //player interaction


    public void stats(){
        System.out.println("Money: "+ this.amount[0][0]);
        System.out.println("Amount bet: " + this.amount[0][1]);
    }

    public boolean userOptions(){ //controls the interaction with the user

        Scanner sc=new Scanner(System.in);

        int x=0,y=0;


        do{
            this.stats();

            if(amount[0][0]>0) {
                System.out.println("If you would like to fold enter 0, if you would like to bet enter 1, if you would like to check and move into the next round enter any other integer number: ");
                x = sc.nextInt();
            }else{
                System.out.println("If you would like to fold enter 0, if you would like to check and move into the next round enter any other integer number: ");
                x = sc.nextInt();
            }

            if(x == 1){
                System.out.println("Enter how much you would like to bet: ");
                y = amount[0][1];
                do{
                    amount[0][1] = sc.nextInt();
                }while(amount[0][1]>amount[0][0]);

                amount[0][0] = amount[0][0] - amount[0][1];
                aiBet();
                this.totalBet=this.totalBet+amount[0][1];
                amount[0][1] += y;


            }

        }while(x == 1);


        if(x==0){
            return true;
        }
        return false;

    }

    ///////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////


    public boolean round1(){

        Scanner sc=new Scanner(System.in);

        boolean temp;
        int x=0,y=0;

        this.stats();
        System.out.println("If you would like to fold enter false and if you would like to bet enter true: ");
        temp = sc.nextBoolean();
        if(temp == true) {

            System.out.println("Enter how much you would like to bet (minimum 200): ");
            do{

                amount[0][1] = sc.nextInt();

            }while(amount[0][1]<200||amount[0][1]>amount[0][0]);

            amount[0][0]=amount[0][0] - amount[0][1];

            return this.userOptions();

        }else{
            return true;
        }


    }

    public boolean round2(){return this.userOptions();
    }

    public boolean round3(){
        return this.userOptions();
    }

    public int round4(){

        this.userOptions();
        int x = this.points(this.hands[0]);//player hands point

        for(int i=1;i<6;i++) {
            if (this.amount[i][2]==0){
                if (x == points(this.hands[i])) {
                    //put finding who has higher cards here
                    if (highCard(i) == true) {
                        return (1);
                    }
                } else if (x < points(this.hands[i])) {
                    return (1);
                }
        }
        }

        return(0);
    }



    ///////////////////////////////////
    /////////////////////////////////// point system

    public boolean highCard(int i){ //finds out who has the higher card the player or the AI if it is the AI it returns true and if it is the user it returns false

        Card tempPlayer;
        Card tempAI;

        if(this.hands[0][0].cardNumber>this.hands[0][1].cardNumber){
            tempPlayer=this.hands[0][0];
        }else{
            tempPlayer=this.hands[0][1];
        }
        if(this.hands[i][0].cardNumber>this.hands[i][1].cardNumber){
            tempAI=this.hands[i][0];
        }else{
            tempAI=this.hands[i][1];
        }

        if(tempPlayer.cardNumber>tempAI.cardNumber){
            return (false);
        }

        return(true);
    }

    public int points(Card handBeingChecked[]){//returns the user points

        Card mixCard[] = new Card[7];
        Card temp;
        boolean y=false;

        for(int i=0;i<2;i++){
            mixCard[i] = handBeingChecked[i];
        }
        for(int i=0;i<5;i++){
            mixCard[i+2] = this.hands[6][i];
        }

        for (int i = 0; i < 7; i++)
        {//sorts the deck from smalles card to largest card
            for (int j = i + 1; j < 7; j++) {
                if (mixCard[i].cardNumber > mixCard[j].cardNumber)
                {
                    temp = mixCard[i];
                    mixCard[i] = mixCard[j];
                    mixCard[j] = temp;
                }
            }
        }




        //////////////////////
        ////////////////////// the flushes
        if (mixCard[2].cardNumber == 10 && mixCard[3].cardNumber == 11 && mixCard[4].cardNumber == 12 && mixCard[5].cardNumber == 13 && mixCard[6].cardNumber == 14) {//rpyal flush
            if(mixCard[2].cardSuit .equals(mixCard[3].cardSuit) && mixCard[3].cardSuit .equals(mixCard[4].cardSuit) && mixCard[4].cardSuit .equals(mixCard[5].cardSuit) && mixCard[5].cardSuit .equals(mixCard[6].cardSuit) ) {//checks if it is a straight flush
                //System.out.println("10");
                return (10);
            }
        }

        if((mixCard[0].cardNumber == mixCard[1].cardNumber +1 && mixCard[1].cardNumber == mixCard[2].cardNumber +1 && mixCard[2].cardNumber == mixCard[3].cardNumber +1 && mixCard[3].cardNumber == mixCard[4].cardNumber +1 )){//checks if it is a straight flush
            if(mixCard[0].cardSuit .equals(mixCard[1].cardSuit) && mixCard[1].cardSuit .equals(mixCard[2].cardSuit) && mixCard[2].cardSuit .equals(mixCard[3].cardSuit) && mixCard[3].cardSuit .equals(mixCard[4].cardSuit) ) {
                //System.out.println("9");
                return (9);
            }
        }

        if((mixCard[1].cardNumber == mixCard[2].cardNumber +1 && mixCard[2].cardNumber == mixCard[3].cardNumber +1 && mixCard[3].cardNumber == mixCard[4].cardNumber +1 && mixCard[4].cardNumber == mixCard[5].cardNumber +1 )){//checks if it is a straight flush
            if(mixCard[1].cardSuit .equals(mixCard[2].cardSuit) && mixCard[2].cardSuit .equals(mixCard[3].cardSuit) && mixCard[3].cardSuit .equals(mixCard[4].cardSuit) && mixCard[4].cardSuit .equals(mixCard[5].cardSuit) ) {
                //System.out.println("9");
                return (9);
            }

        }

        if((mixCard[2].cardNumber == mixCard[3].cardNumber +1 && mixCard[3].cardNumber == mixCard[4].cardNumber +1 && mixCard[4].cardNumber == mixCard[5].cardNumber +1 && mixCard[5].cardNumber == mixCard[6].cardNumber +1 )) {//checks if it is a straight flush
            if(mixCard[2].cardSuit .equals(mixCard[3].cardSuit) && mixCard[3].cardSuit .equals(mixCard[4].cardSuit) && mixCard[4].cardSuit .equals(mixCard[5].cardSuit) && mixCard[5].cardSuit .equals(mixCard[6].cardSuit) ) {
                //System.out.println("9");
                return (9);
            }
        }

        //////////////////////
        ////////////////////// four of a kind

        for(int i=0;i<4||y==true;i++){
            int x=0;
            for(int j=i;j<i+3;j++){
                if(mixCard[i].cardNumber==mixCard[j+1].cardNumber){
                    x++;
                }
            }
            if(x==3){
                //System.out.println("8");
                return(8);
            }
        }


        //////////////////////
        ////////////////////// full house

        if(mixCard[0].cardNumber == mixCard[1].cardNumber||mixCard[5].cardNumber == mixCard[6].cardNumber){
            if((mixCard[2].cardNumber == mixCard[3].cardNumber&&mixCard[3].cardNumber == mixCard[4].cardNumber&&mixCard[4].cardNumber == mixCard[5].cardNumber&&mixCard[5].cardNumber == mixCard[6].cardNumber)||(mixCard[0].cardNumber == mixCard[1].cardNumber&&mixCard[1].cardNumber == mixCard[2].cardNumber&&mixCard[2].cardNumber == mixCard[3].cardNumber&&mixCard[3].cardNumber == mixCard[4].cardNumber)){
                //System.out.println("7");
                return(7);
            }
        }

        //////////////////////
        ////////////////////// flush

        for(int i=0;i<3;i++){
            int x=0;
            for(int j=i;j<i+4;j++){
                if(mixCard[i].cardSuit.equals(mixCard[j+1].cardSuit)){
                    x++;
                }
            }
            if(x==4){
                //System.out.println("6");
                return (6);
            }

        }


        //////////////////////
        ////////////////////// straight

        for(int i=0;i<3;i++){
            int x=0;
            for(int j=i;j<i+4;j++){
                if(mixCard[i].cardNumber==mixCard[j+1].cardNumber){
                    x++;
                }
            }
            if(x==4){
                //System.out.println("5");
                return (5);
            }

        }





        //////////////////////
        //////////////////////three of a kind

        for(int i=0;i<5;i++){
            int x=0;
            for(int j=i;j<i+2;j++){
                if(mixCard[i].cardNumber==mixCard[j+1].cardNumber){
                    x++;
                }
            }
            if(x==2){
                //System.out.println("4");
                return (4);
            }

        }



        //////////////////////
        //////////////////////two pair

        for(int i=0;i<4;i++){
            if(mixCard[i].cardNumber==mixCard[i+1].cardNumber){
                for(int j=i+2;j<6;j++){
                    if(mixCard[j].cardNumber==mixCard[j+1].cardNumber){
                        //System.out.println("3");
                        return (3);
                    }
                }
            }
        }



        //////////////////////
        //////////////////////pair

        for(int i=0;i<6;i++){
            if(mixCard[i].cardNumber==mixCard[i+1].cardNumber){
                //.out.println("2");
                return (2);
            }
        }

        return 0;

    }


}
