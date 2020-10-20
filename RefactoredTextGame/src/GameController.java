
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class GameController {

	private String title = "Saharan Delirium";//game title
	private GameDriver gameDriver = new GameDriver();
	GameScene[] gameScene = new GameScene[101];//array of GameScenes. We access each scene by its index.
	private int currentSceneNo = 0;//to keep track of where we are in the game
	
	//instantiating FXML objects
	@FXML 
	private Pane mainWindowPane;
	@FXML
    private Text sceneTitleTextBox;
    @FXML
    private TextFlow choiceAButton;
    @FXML
    private TextFlow choiceBButton;
    @FXML
    private TextFlow choiceCButton;
    @FXML
    private Text sceneMessageTextBox;
    @FXML
    private Text buttonATextBox;
    @FXML
    private Text buttonBTextBox;
    @FXML
    private Text buttonCTextBox;
    @FXML
    private ImageView sceneImageView;
    
    
    //some CSS styling for buttons
    @FXML
    private void onMouseEnterButtonA() throws IOException {
    	  try {
    		choiceAButton.setCursor(Cursor.HAND);
      		choiceAButton.setStyle("-fx-background-color: #964d1a; -fx-background-radius: 5;");	
    	  }
    	  catch (Exception e) {
      	      e.printStackTrace();
          }
    }
    @FXML
    private void onMouseExitButtonA() throws IOException {
    	  try {
    		  choiceAButton.setCursor(Cursor.DEFAULT);
      		  choiceAButton.setStyle("-fx-background-color: #b34a00; -fx-background-radius: 5;");
    	  }
    	  catch (Exception e) {
      	      e.printStackTrace();
          }
    }
    @FXML
    private void onMouseEnterButtonB() throws IOException {
    	  try {
    		  choiceBButton.setCursor(Cursor.HAND);
      		  choiceBButton.setStyle("-fx-background-color: #964d1a; -fx-background-radius: 5;");	
    	  }
    	  catch (Exception e) {
      	      e.printStackTrace();
          }
    }
    @FXML
    private void onMouseExitButtonB() throws IOException {
    	  try {
    		  choiceBButton.setCursor(Cursor.DEFAULT);
      		  choiceBButton.setStyle("-fx-background-color: #b34a00; -fx-background-radius: 5;");
    	  }
    	  catch (Exception e) {
      	      e.printStackTrace();
          }
    }
    @FXML
    private void onMouseEnterButtonC() throws IOException {
    	  try {
    		  choiceCButton.setCursor(Cursor.HAND);
      		  choiceCButton.setStyle("-fx-background-color: #964d1a; -fx-background-radius: 5;");	
    	  }
    	  catch (Exception e) {
      	      e.printStackTrace();
          }
    }
    @FXML
    private void onMouseExitButtonC() throws IOException {
    	  try {
    		  choiceCButton.setCursor(Cursor.DEFAULT);
      		  choiceCButton.setStyle("-fx-background-color: #b34a00; -fx-background-radius: 5;");
    	  }
    	  catch (Exception e) {
      	      e.printStackTrace();
          }
    }
    
    //action methods for 3 main buttons
    @FXML
    private void buttonAClick() throws IOException {
		try {
			currentSceneNo = gameScene[currentSceneNo].getAJumpsTo();
			loadScene(currentSceneNo);
			System.out.printf("Button A pressed, scene %s loaded%n%n", currentSceneNo);
		}
       catch (Exception e) {
    	   e.printStackTrace();
       }
    }
    @FXML
    private void buttonBClick() throws IOException {
		try {
			currentSceneNo = gameScene[currentSceneNo].getBJumpsTo();
			loadScene(currentSceneNo);
			System.out.printf("Button B pressed, scene %s loaded%n%n", currentSceneNo);
		}
       catch (Exception e) {
    	   e.printStackTrace();
       }
    }
    @FXML
    private void buttonCClick() throws IOException {
        try {
    		currentSceneNo = gameScene[currentSceneNo].getCJumpsTo();
    		loadScene(currentSceneNo);
    		System.out.printf("Button C pressed, scene %s loaded%n%n", currentSceneNo);
		}
       catch (Exception e) {
    	   e.printStackTrace();
       }
    }
    
	public void setGameDriver(GameDriver gameDriver) {//called by GameDriver class
		this.gameDriver = gameDriver;
	}
    
	//displays scene info to the GUI
	public void loadScene(int sceneNo) {
    	sceneTitleTextBox.setText(title);
    	sceneMessageTextBox.setText(gameScene[sceneNo].getMessage());
		buttonATextBox.setText(gameScene[sceneNo].getChoiceA());
    	buttonBTextBox.setText(gameScene[sceneNo].getChoiceB());
    	buttonCTextBox.setText(gameScene[sceneNo].getChoiceC());
	}
	
	public void initialize() {//initializes GameScene objects. called by JavaFX/Application.
		//GameScene contructor takes following parameters: main message, option A text, optionB text, option C text, scene number option A goes to, scene number option B goes to, scene number option C goes to
		gameScene[0] = new GameScene("Welcome to the game!", "Start Game", "Load Game", "Quit", 1, 99, 100);//scene 99 means load game, scene 100 means quit
		
		gameScene[1] = new GameScene("You wake suddenly. A gasp of dry air travels quickly into your mouth. "
				+ "There are awful, aching pains coursing through your body in several areas. You’re clothed, but your feet are bare."
				+ " As you look around to assess your surroundings, "
				+ "you realise you’re in the middle of a barren, bone-dry wasteland. Alone. Where exactly are you?", 
				"Who’s asking?", "I have no idea.", "I’m in the middle of nowhere, with no people, shelter, or food and water in sight. ", 2, 3, 4);
		
		gameScene[2] = new GameScene("Your conscience. Your inner self. Perhaps your saviour. Now, answer the question. Where are you?", 
				"I have no idea.", "I’m in the middle of nowhere, with no people, shelter, or food and water in sight.",
				"I don’t really care where I am. I’m so tired... I just want to sleep. [Go to sleep.]", 3, 4, 5);
		
		gameScene[3] = new GameScene("Well, you better figure it out. You’re in terrible shape and need to do something soon. "
				+ "I’ll ask again. Where are you?", "Again, I have no idea.", "I’m in the middle of nowhere, with no people, shelter, or food and water in sight.",
				"I don’t really care where I am. I’m so tired... I just want to sleep. [Go to sleep.]", 3, 4, 5);
		
		gameScene[4] = new GameScene("Correct! Now, within this expanse of nothing-but-sand, how are you going to survive? "
				+ "You can’t see anything within a five-mile radius. Perhaps you’re not looking hard enough?",
				"[Shield your eyes with your hand and look afar for people or shelter.]", 
				"[Dig into the sand around you to look for potential resources.]", 
				"I just have a feeling... [Start walking in the direction of the sun.]", 6, 7, 8);
	
		gameScene[5] = new GameScene("After over an hour of shifting around in a coarse blanket of sand, you hazily drift off into the black."
				+ "You hallucinate about your recent past for a few fleeting moments until all goes dark. Game over.", 
				"(Play again?)", "(...)", "(Quit?)", 0, 1, -2);//TODO: second location???
		
		gameScene[6] = new GameScene("You squint and notice a few palm trees and a small hut in the distance. As you approach, you begin to "
				+ "realise that it’s simply a mirage, fading away more and more after each step you take. What are you going to do now?",
				"[Go back to where you came from and decide your next move upon arrival.]", 
				"I just have a feeling... [Start walking in the direction of the sun.]",
				"I can’t believe this... I give up. I’m going to sleep. [Go to sleep.]", 19, 8, 5);
		
		gameScene[7] = new GameScene("You dig and dig and dig until the skin on your hands is burning from the scorching sand. "
				+ "You nearly give up, but your hand catches something by accident. It’s a pair of sandals! "
				+ "Well... sort of. There are two mismatched sandals. You assume one of them is yours, "
				+ "but who the other belongs to is a mystery you’ll have to solve. After acquiring footwear, what will you do next?", 
				"Now I can head out without burning my feet! [Put on the sandals and start walking in the direction of the sun.]", 
				"No, wait... I think it’s a better idea to go this way. [Put on the sandals and start walking in the direction opposite the sun.]",
				"I know there’s more. There has to be. [Keep digging.]", 8, 9, 10);
		
		gameScene[8] = new GameScene("You’ve been walking for ages; so long that the sun is setting. "
				+ "You happen upon a small village. Wanna know the most exciting part?", 
				"Yes!", "Uh... yeah, I guess.", "No, I don’t.", 11, 11, 12);
		
		gameScene[9] = new GameScene("For a while, nothing comes into sight. You become weary, searching for a sign of something, anything. "
				+ "But eventually, you come across a traveller on the back of a camel. They carry food, water, and other supplies. What do you do?",
				"[Approach them cautiously but kindly.]", "[Attempt to ambush them and steal their camel and supplies.]",
				"They may be a threat to someone in my condition. [Avoid them.]", 13, 14, 15);
		
		gameScene[10] = new GameScene("There’s nothing. Now what?", "Well, now I can head out without burning my feet! "
				+ "[Put on the sandals and start walking in the direction of the sun.]",
				"No, wait... I think it’s a better idea to go this way. [Put on the sandals and start walking in the direction opposite the sun.]",
				"Is there really nothing? Nothing at all?,", 8, 9, 10);
		
		gameScene[11] = new GameScene("They’re having a feast! The whole village! You know what that means, right? You finally get to eat!",
				"I don’t care what there is, I’m eating it. [Dig in!]",
				"Maybe I should inspect the food first. [Inspect the food.]",
				"I’ll ask one of the villagers about this feast first. [Ask a villager.]", 16, 17, 18);
		
		gameScene[12] = new GameScene("Yes, you do. They’re having a feast! The whole village! You know what that means, right? You finally get to eat!",
				"I don’t care what there is, I’m eating it. [Dig in!]",
				"Maybe I should inspect the food first. [Inspect the food.]",
				"I’ll ask one of the villagers about this feast first. [Ask a villager.]", 16, 17, 18);
		
		gameScene[13] = new GameScene("The traveller spots you approaching them. They slow to a halt to ask you what you need. "
				+ "They seem inconvenienced, but clearly not enough to just ignore you.",
				"Could I have some water, please? I don’t know how long it’s been since I’ve had any.",
				"Do you think you could give me a ride to the nearest city? Or at least point me in the right direction? I have no idea where I am right now.",
				"I don’t know if I trust them... [Go back to where you came from and decide your next move upon arrival.]", 20, 21, 19);
		
		gameScene[14] = new GameScene("The traveller spots you planning to attack. Their camel quickly starts running, "
				+ "moving a lot faster than you could. They’re gone in a short span of time. Nice going, genius. Now what?",
				"[Go back to where you came from and decide your next move upon arrival.]",
				"I’m going to catch them! [Chase after them.]",
				"[Keep walking in the direction you were headed.]", 19, 22, 23);
		
		gameScene[15] = new GameScene("The traveller doesn’t spot you. You have managed to successfully avoid them. "
				+ "But... now what? They may have been your only chance of survival.", 
				"Go back to where you came from and decide your next move upon arrival.]",
				"I’ve changed my mind; I’m going to catch them! [Chase after them.]",
				"[Keep walking in the direction you were headed.]", 19, 22, 23);
		
		gameScene[16] = new GameScene("You bite into an odd and unfamiliar-tasting chunk of meat. "
				+ "What is this? It’s not bad, but... it’s unlike anything you’ve ever had before. "
				+ "You should probably ask someone about this, right?", 
				"Yeah, probably... [Ask a villager.]",
				"My hunger knows no bounds. [Eat the rest of the meat.]",
				"I don’t have a good feeling about this. [Throw the meat on the ground.]", 18, 24, 25);
		
		//TODO
		gameScene[17] = new GameScene("", "", "", "", 1, 1, 1);
		gameScene[18] = new GameScene("", "", "", "", 1, 1, 1);
		
		gameScene[19] = new GameScene("You make it nearly halfway back to where you started from before you collapse due to heat exhaustion, "
				+ "dehydration, and likely other factors too. The hot sand feels comforting as you gradually lose your vision. "
				+ "After some time, all goes dark. Game over." ,
				"(Start again?)", "(...)", "(Quit?)", 1, 0, 100);
		
		
		loadScene(currentSceneNo);//scene 0 is loaded upon initialization
	}

}
