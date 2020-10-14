
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FXMLController {

	static Scanner input  = new Scanner(System.in);
	private static int goToSceneNo = 1;
	private static String title = "Welcome to TEXT-BASED GAME!";
	private static String message = "To play, type A, B, or C to choose your next action:";
	private static String choiceA = "Start Game"; 
	private static String choiceB = "See high score list";
	private static String choiceC = "Quit";
	static enum UserChoice {A, B, C}
	static private UserChoice userChoice;
	static boolean quitGame = false;
    
	public String getTitle() {
		return title;
	}
	
	@FXML
    private static Text sceneTitleTextBox;
    @FXML
    private Button choiceAButton;
    @FXML
    private Button choiceBButton;
    @FXML
    private Button choiceCButton;
    @FXML
    private static Text choiceATextBox;
    @FXML
    private static Text choiceBTextBox;
    @FXML
    private static Text choiceCTextBox;
    @FXML
    private static Text sceneMessageTextBox;
    @FXML
    private static ImageView sceneImageView;
    
    @FXML
    private void buttonAClick(ActionEvent event) {
   
		try {
			System.out.println("Button A pressed");
			printScene();
			userChoice = UserChoice.valueOf("A");
			updateSceneInfo();
		}
       catch (Exception e) {
    	   e.printStackTrace();
       }
    }
    
    @FXML
    private void buttonBClick(ActionEvent event) {
		try {
			System.out.println("Button B pressed");
			printScene();
			userChoice = UserChoice.valueOf("B");
			updateSceneInfo();
		}
       catch (Exception e) {
    	   e.printStackTrace();
       }
    
    }
    
    @FXML
    private void buttonCClick(ActionEvent event) {
    	try {
    		System.out.println("Button C pressed");
			printScene();
			userChoice = UserChoice.valueOf("C");
			updateSceneInfo();
		}
       catch (Exception e) {
    	   e.printStackTrace();
       }
    }
    
    
    public void initialize() {
    	//sceneTitleTextBox.setText(title);

        
     }
    

	public static void printScene() throws InterruptedException {
		//sceneTitleTextBox.setText(title);
		sceneMessageTextBox.setText(message);
		choiceATextBox.setText(choiceA);
		choiceBTextBox.setText(choiceB);
		choiceCTextBox.setText(choiceC);
	}
		
	// Navigates gameplay. No objects, just changing what gets run, updated, or printed based on current values of variables sceneNo and userChoice.
	public static void updateSceneInfo() {
		switch (goToSceneNo) {
			case 1: //Opening scene
				switch (userChoice) {// different methods run depending on which was chosen in scene 1
					case A: {//changes attributes for next scene
						message = "You find yourself in a desert. All you have with you is an empty jug.";
						choiceA = "Call out for help";
						choiceB = "Go look for water";
						choiceC = "Wait under shade tree";
						goToSceneNo = 2;//jump to sceneNo 2
						break;
					}
					case B: {
						System.out.printf("HIGH SCORES LIST%n%n");
						message = "Start game?";
						choiceA = "High Score 1";
						choiceB = "High Score 2";
						choiceC = "High Score 3";
						break;
					}
					case C: {
						quitGame = true;
						break;
					} default: return;
				} break;
				
				
			case 2://directs from scene 1
				switch (userChoice) {
					case A: {//directs from sceneNo 1 choiceA
						message = "You hear someone respond!";
						choiceA = "Go towards voice";
						choiceB = "They don't sound so friendly; try to sneak around them";
						choiceC = "Rob them of food and water";
						goToSceneNo = 3;//jumps to scene 3
						break;
					}
					case B: {//directs from sceneNo 1 choiceB
						message = "You look around for water, but don't see any. You think you hear a sound in the distance.";
						choiceA = "Go towards sound";
						choiceB = "Wait for help";
						choiceC = "No water to be found.";
						goToSceneNo = 4;//jumps to scene 4
						break;
					}
					case C: {
						message = "message 2C";
						choiceA = "";
						choiceB = "";
						choiceC = "";
						goToSceneNo = 5;//jumps to scene 5
						break;
					} default: return;
				} break;
				
				
			case 3://directs from scene 2 case A
				switch (userChoice) {
					case A: {
						message = "message case 3A";
						choiceA = "";
						choiceB = "";
						choiceC = "";
						goToSceneNo = 6;
						break;
					}
					case B: {
						message = "message case 3B";
						choiceA = "";
						choiceB = "";
						choiceC = "";
						goToSceneNo = 7;
						break;
					}
					case C: {
						message = "message case 3C";
						choiceA = "";
						choiceB = "";
						choiceC = "";
						goToSceneNo = 8;
						break;
					} default: return;
				} break;
	
		}
	}

    
	
}
