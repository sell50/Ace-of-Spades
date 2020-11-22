import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GuessNumber {

    String temp1 = " ";
    String temp2 = " ";
    String temp3 = " ";
    String highLow = " ";
    int guess;
    boolean wait = true;
    
    static int correctNum;
    int lives = 3;
    boolean correct = false;


    Scanner keyboard = new Scanner(System.in);
	
	JPanel panel = new JPanel();
	JTextField textField = new JTextField(5);

    /*public static void main(String[] args){
        
        GuessNumber minigame = new GuessNumber();
        minigame.GuessGame();

    }*/

    public boolean GuessGame(){
    	
        Random dice = new Random();
        correctNum = 1 + dice.nextInt(9);

    	panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    	
    	JLabel labelText1 = new JLabel("Another player has grabbed a spoon!");
    	JLabel labelText2 = new JLabel("To move to the next round, successfully guess the correct number.");
    	JLabel labelText3 = new JLabel("Select a value between 1 and 10. Tries Remaining: " + lives);
    	JLabel labelText4 = new JLabel(" ");
  
    	JFrame frame = new JFrame();
    	frame.setTitle("Guess the Number");
    	frame.setSize(500, 150);
    	
    	panel.add(labelText1);
    	panel.add(labelText2);
    	panel.add(labelText3);
    	panel.add(textField);
    	panel.add(labelText4);
    	frame.add(panel);
    	frame.setVisible(true);
    	//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	textField.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			String temp = textField.getText();
    			if(lives == 3) {
    				temp1 = temp;
    			}
    			if(lives == 2) {
    				temp2 = temp;
    			}
    			if(lives == 1) {
    				temp3 = temp;
    			}
    		}
    	});

        while(correct == false && lives > 0){
        	
        	if(lives == 3) {
        		while(wait == true) {
        	    	if(temp1.matches("1|2|3|4|5|6|7|8|9|10")) {
                    	guess = Integer.parseInt(temp1);
            			wait = false;
        			}
        		}
        	}
        	
        	if(lives == 2) {
        		while(wait == true) {
        	    	if(temp2.matches("1|2|3|4|5|6|7|8|9|10")) {
                    	guess = Integer.parseInt(temp2);
            			wait = false;
        			}
        		}
        	}
        	
        	if(lives == 1) {
        		while(wait == true) {
        	    	if(temp3.matches("1|2|3|4|5|6|7|8|9|10")) {
                    	guess = Integer.parseInt(temp3);
            			wait = false;
        			}
        		}
        	}
        	
        	
            if(guess == correctNum){
                correct = true;
            }
            else if(guess < correctNum){
            	labelText4.setText("Not correct. The correct number is larger.");
            }
            else if(guess > correctNum){
            	labelText4.setText("Not correct. The correct number is smaller.");
            }
            
            lives--;
        	labelText3.setText("Select a value between 1 and 10. Tries Remaining: " + lives);
            wait = true;
        }

        if(correct == true){
        	System.out.println("Your guess was correct! You got a spoon!");
        }
        else if(correct == false){
        	System.out.println("Your guess was incorrect. You lose.");
        }
        
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        return correct;
    }
}
