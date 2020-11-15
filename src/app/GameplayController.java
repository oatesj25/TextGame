package app;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import javax.xml.bind.JAXB;
import javax.xml.transform.Source;


public class GameplayController {

    GameScene[] gameScene = new GameScene[101];//array of GameScenes. We access each scene by its index.
    private int currentSceneNo = 1;//to keep track of where we are in the game
    private String defaultImagePath = "app/images/desert1.png";
    private String currentSceneImagePath;
    private String userName;

    //sample saved games data
    ObservableList<SavedGame> savedGamesList = FXCollections.observableArrayList(
            new SavedGame("Eric", 4),
            new SavedGame("Jeff", 7),
            new SavedGame("Tina", 13),
            new SavedGame("Demetrius", 12),
            new SavedGame("Amy", 16),
            new SavedGame("Aaron", 3)
    );


    //instantiating FXML objects
    @FXML
    private Pane root;
    @FXML
    private AnchorPane saveGameMenuAnchorPane;
    @FXML
    private Button overwritePreviousGameButton;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private Button newSavedGameButton;
    @FXML
    private Text saveGameMenuExitButton;
    @FXML
    private Text sceneMessageTextBox;
    @FXML
    private TextFlow choiceAButton;
    @FXML
    private Text buttonATextBox;
    @FXML
    private TextFlow choiceBButton;
    @FXML
    private Text buttonBTextBox;
    @FXML
    private TextFlow choiceCButton;
    @FXML
    private Text buttonCTextBox;
    @FXML
    private Button gameplayMenuButton;
    @FXML
    private Button saveGameMenuButton;
    @FXML
    private TableView<SavedGame> savedGamesTableView;
    @FXML
    private TableColumn<SavedGame, String> nameCol;
    @FXML
    private TableColumn<SavedGame, Integer> sceneNoCol;
    @FXML
    private TableColumn<SavedGame, String> dateTimeCol;

    //action methods for 3 main buttons
    @FXML
    private void buttonAClick() throws IOException {
        try {
            currentSceneNo = gameScene[currentSceneNo].getAJumpsTo();
            loadScene(currentSceneNo);
            System.out.printf("Button A pressed, scene %s loaded%n%n", currentSceneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonBClick() throws IOException {
        try {
            currentSceneNo = gameScene[currentSceneNo].getBJumpsTo();
            loadScene(currentSceneNo);
            System.out.printf("Button B pressed, scene %s loaded%n%n", currentSceneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonCClick() throws IOException {
        try {
            currentSceneNo = gameScene[currentSceneNo].getCJumpsTo();
            loadScene(currentSceneNo);
            System.out.printf("Button C pressed, scene %s loaded%n%n", currentSceneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveGameMenuButtonClick() throws IOException {//TODO save game to XML
        saveGameMenuAnchorPane.setVisible(true);
        saveGameMenuAnchorPane.toFront();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sceneNoCol.setCellValueFactory(new PropertyValueFactory<>("sceneNo"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<>("dateSaved"));
        savedGamesTableView.setItems(savedGamesList);//loads data to TableView

    }


    @FXML
    void createNewSavedGame(ActionEvent event) {

    }

    @FXML
    void overwritePreviousSavedGame(ActionEvent event) {

    }


    @FXML
    void exitSaveGameMenu(MouseEvent event) {
        saveGameMenuAnchorPane.setVisible(false);
        saveGameMenuAnchorPane.toBack();
    }

    @FXML
    private void gameplayMenuButtonClick() throws IOException {
        try {
                Parent titleScreenParent = FXMLLoader.load(getClass().getResource("title_screen_layout.fxml"));
                root.getChildren().setAll(titleScreenParent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //displays scene info to the GUI
    public void loadScene(int sceneNo) {
        sceneMessageTextBox.setText(gameScene[sceneNo].getMessage());
        buttonATextBox.setText(gameScene[sceneNo].getChoiceA());
        buttonBTextBox.setText(gameScene[sceneNo].getChoiceB());
        buttonCTextBox.setText(gameScene[sceneNo].getChoiceC());

        //display proper background image
        if (! gameScene[sceneNo].getImagePath().isEmpty()) { //getting background image path if scene has one
            currentSceneImagePath = gameScene[currentSceneNo].getImagePath();
        } else {
           currentSceneImagePath = defaultImagePath;
        } System.out.println("Loading image: " + currentSceneImagePath);
        BackgroundSize backgroundSize = new BackgroundSize(900, 700,
                true, true, true, true);
        BackgroundImage image = new BackgroundImage(new Image(currentSceneImagePath),  //setting background image
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        root.setBackground(new Background(image));
    }

    public void initialize() {//TODO refactor so it loads from XML files instead of taking up space here
        //initializing GameScenes
        /*GameScene contructor takes following parameters:
            main message, option A text, optionB text, option C text,
            scene number option A goes to, scene number option B goes to,
            scene number option C goes to, and background image file path */
        gameScene[1] = new GameScene("You wake suddenly. A gasp of dry air travels quickly into your mouth. "
                + "There are awful, aching pains coursing through your body in several areas. You're clothed, but your feet are bare."
                + " As you look around to assess your surroundings, "
                + "you realise you're in the middle of a barren, bone-dry wasteland. Alone. Where exactly are you?",
                "Who's asking?", "I have no idea.", "I'm in the middle of nowhere, with no people, shelter, or food and water in sight. ",
                2, 3, 4, "app/images/desert1.png");

        gameScene[2] = new GameScene("Your conscience. Your inner self. Perhaps your saviour. Now, answer the question. Where are you?",
                "I have no idea.", "I'm in the middle of nowhere, with no people, shelter, or food and water in sight.",
                "I don't really care where I am. I'm so tired... I just want to sleep. [Go to sleep.]", 3, 4, 5, "app/images/desert1.png");

        gameScene[3] = new GameScene("Well, you better figure it out. You're in terrible shape and need to do something soon. "
                + "I'll ask again. Where are you?", "Again, I have no idea.", "I'm in the middle of nowhere, with no people, shelter, or food and water in sight.",
                "I don't really care where I am. I'm so tired... I just want to sleep. [Go to sleep.]", 3, 4, 5, "app/images/desert1.png");

        gameScene[4] = new GameScene("Correct! Now, within this expanse of nothing-but-sand, how are you going to survive? "
                + "You can't see anything within a five-mile radius. Perhaps you're not looking hard enough?",
                "[Shield your eyes with your hand and look afar for people or shelter.]",
                "[Dig into the sand around you to look for potential resources.]",
                "I just have a feeling... [Start walking in the direction of the sun.]", 6, 7, 8, "app/images/desert2.png");

        gameScene[5] = new GameScene("After over an hour of shifting around in a coarse blanket of sand, you hazily drift off into the black."
                + "You hallucinate about your recent past for a few fleeting moments until all goes dark. Game over.",
                "(Start new game?)", "(Load previous save?)", "(Quit?)", 1, 99, 100, "app/images/darkness1.png");

        gameScene[6] = new GameScene("You squint and notice a few palm trees and a small hut in the distance. You start to approach and as you do, you begin to "
                + "devastatingly realise that it's simply a mirage, fading away more and more after each step you take. What are you going to do now?",
                "[Go back to where you came from and decide your next move upon arrival.]",
                "I just have a feeling... [Start walking in the direction of the sun.]",
                "I can't believe this... I give up. I'm going to sleep. [Go to sleep.]", 19, 8, 5, "app/images/palmTrees.png");

        gameScene[7] = new GameScene("You dig and dig and dig until the skin on your hands is burning from the scorching sand. "
                + "You nearly give up, but your hand catches something by accident. It's a pair of sandals! "
                + "Well... sort of. There are two mismatched sandals. You assume one of them is yours, "
                + "but who the other belongs to is a mystery you'll have to solve. After acquiring footwear, what will you do next?",
                "Now I can head out without burning my feet! [Put on the sandals and start walking in the direction of the sun.]",
                "No, wait... I think it's a better idea to go this way. [Put on the sandals and start walking in the direction opposite the sun.]",
                "I know there's more. There has to be. [Keep digging.]", 8, 9, 10, "app/images/sandals.png");

        gameScene[8] = new GameScene("You've been walking for ages; so long that the sun is setting. "
                + "You happen upon a small village. Wanna know the most exciting part?",
                "Yes!", "Uh... yeah, I guess.", "No, I don't.", 11, 11, 12, "app/images/darkness2.png");

        gameScene[9] = new GameScene("For a while, nothing comes into sight. You become weary, searching for a sign of something, anything. "
                + "But eventually, you come across a traveller on the back of a camel. They carry food, water, and other supplies. What do you do?",
                "[Approach them cautiously but kindly.]", "[Attempt to ambush them and steal their camel and supplies.]",
                "They may be a threat to someone in my condition. [Avoid them.]", 13, 14, 15, "app/images/camel2.png");

        gameScene[10] = new GameScene("There's nothing. Now what?", "Well, now I can head out without burning my feet! "
                + "[Put on the sandals and start walking in the direction of the sun.]",
                "No, wait... I think it's a better idea to go this way. [Put on the sandals and start walking in the direction opposite the sun.]",
                "Is there really nothing? Nothing at all?,", 8, 9, 10, "app/images/desert2.png");

        gameScene[11] = new GameScene("They're having a feast! The whole village! You know what that means, right? You finally get to eat!",
                "I don't care what there is, I'm eating it. [Dig in!]",
                "Maybe I should inspect the food first. [Inspect the food.]",
                "I'll ask one of the villagers about this feast first. [Ask a villager.]", 16, 17, 18);

        gameScene[12] = new GameScene("Yes, you do. They're having a feast! The whole village! You know what that means, right? You finally get to eat!",
                "I don't care what there is, I'm eating it. [Dig in!]",
                "Maybe I should inspect the food first. [Inspect the food.]",
                "I'll ask one of the villagers about this feast first. [Ask a villager.]", 16, 17, 18);

        gameScene[13] = new GameScene("The traveller spots you approaching them. They slow to a halt to ask you what you need. "
                + "They seem inconvenienced, but clearly not enough to just ignore you.",
                "Could I have some water, please? I don't know how long it's been since I've had any.",
                "Do you think you could give me a ride to the nearest city? Or at least point me in the right direction? I have no idea where I am right now.",
                "I don't know if I trust them... [Go back to where you came from and decide your next move upon arrival.]", 20, 21, 19);

        gameScene[14] = new GameScene("The traveller spots you planning to attack. Their camel quickly starts running, "
                + "moving a lot faster than you could. They're gone in a short span of time. Nice going, genius. Now what?",
                "[Go back to where you came from and decide your next move upon arrival.]",
                "I'm going to catch them! [Chase after them.]",
                "[Keep walking in the direction you were headed.]", 19, 22, 23);

        gameScene[15] = new GameScene("The traveller doesn't spot you. You have managed to successfully avoid them. "
                + "But... now what? They may have been your only chance of survival.",
                "Go back to where you came from and decide your next move upon arrival.]",
                "I've changed my mind; I'm going to catch them! [Chase after them.]",
                "[Keep walking in the direction you were headed.]", 19, 22, 23);

        gameScene[16] = new GameScene("You bite into an unfamiliar-tasting chunk of meat. "
                + "What is this? It's not bad, but... it's unlike anything you've ever had before. "
                + "You should probably ask someone about this, right?",
                "Yeah, probably... [Ask a villager.]",
                "My hunger knows no bounds. [Eat the rest of the meat.]",
                "I don't have a good feeling about this. [Throw the meat on the ground.]", 18, 24, 25);

        gameScene[17] = new GameScene("You inspect the food, looking into its small details and smelling its fresh, protein-packed scent simultaneously. "
                + "It looks and smells like a normal piece of meat. What should you do?",
                "It seems fine. It feels like it's been so long since I had some food. [Take a bite.]",
                "I'm still sceptical. I'll ask a villager about it. [Ask a villager.]",
                "I don't have a good feeling about this. [Throw the meat on the ground.]", 16, 18, 25);

        gameScene[18] = new GameScene("Your eyes wander around the open village area in search of a villager."
                + "You find someone leaning against a building to the left of you: an older man, bald, with lengthy facial hair, perhaps to compensate."
                + "He is relatively expressionless with an indifferent demeanor. You approach him and ask: ",
                "Excuse me, sir. What food is being served in this village feast?",
                "Is this food safe to eat?",
                "Actually, never mind. I'm sure it's fine. Im just going to eat it. [Take a bite.]", 26, 27, 16);

        gameScene[19] = new GameScene("You make it nearly halfway back to where you started from before you collapse due to heat exhaustion, "
                + "dehydration, and likely other factors too. The hot sand feels comforting as you gradually lose your vision. "
                + "After some time, all goes dark. Game over." ,
                "(Start new game?)", "...", "...", 1, 1, 1);

        //some scenes currently under construction:
        gameScene[20] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 20, 20);
        gameScene[21] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 21, 21);
        gameScene[22] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 22, 22);
        gameScene[23] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 23, 23);
        gameScene[24] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 24, 24);
        gameScene[25] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 25, 25);
        gameScene[26] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 26, 26);
        gameScene[27] = new GameScene("Scene currently under construction", "Go to menu", "", "", 1, 27, 27);

        loadScene(currentSceneNo);//scene 1 is loaded upon initialization
    }

}
