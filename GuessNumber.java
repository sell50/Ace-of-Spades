import java.util.Scanner;
import java.util.Random;

public class GuessNumber {


    public static void main(String[] args){
        
        GuessNumber minigame = new GuessNumber();

    }

    public boolean GuessGame(){

        //Variables
        int correctNum;
        int guess;
        int lives = 1;
        boolean correct = false;

        Scanner keyboard = new Scanner(System.in);
        Random dice = new Random();

        correctNum = 1 + dice.nextInt(9);

        System.out.println("*******************************************************************");
        System.out.println("Another player has grabbed a spoon!");
        System.out.println("To move to the next round, successfully guess the correct number.");
        System.out.println("You have 3 guesses to select the correct number.");
        System.out.println("");

        while(correct == false && lives <= 3){
            System.out.print("Select a number between 1 and 10: ");
            guess = keyboard.nextInt();
            if(guess == correctNum){
                correct = true;
            }
            else if(guess < correctNum){
                System.out.println("Not correct. The correct number is larger.");
            }
            else if(guess > correctNum){
                System.out.println("Not correct. The correct number is smaller.");
            }
            lives++;
        }

        if(correct == true){
            System.out.println("");
            System.out.println("Your guess was correct! You got a spoon!");
            System.out.println("*******************************************************************");
        }
        else if(correct == false){
            System.out.println("");
            System.out.println("Your guess was incorrect! You lose!");
            System.out.println("*******************************************************************");
        }

        return correct;

    }






}
