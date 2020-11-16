package app;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GameplayController {

    GameScene[] gameScene = new GameScene[50];//array of GameScenes. We access each scene by its index. Make array longer if needed.
    private int currentSceneNo = 1;//to keep track of where we are in the game
    private String defaultImagePath = "app/images/desert1.png";
    private String currentSceneImagePath;
    private List<SavedGame> savedGamesList = new ArrayList<SavedGame>();
    ObservableList<ObservableSavedGame> observableSavedGamesList;

    //instantiating FXML objects
    //main screen components:
    @FXML
    private Pane root;
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

    // save game menu components:
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
    private TableView<ObservableSavedGame> savedGamesTableView;
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
    private void gameplayMenuButtonClick() throws IOException { //brings you back to the main menu
        try {
            Parent titleScreenParent = FXMLLoader.load(getClass().getResource("title_screen_layout.fxml"));
            root.getChildren().setAll(titleScreenParent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void saveGameMenuButtonClick() {
        updateObservableSavedGames();
        showSaveGameMenu();
    }
    @FXML
    void createNewSavedGame(ActionEvent event) throws IOException {
        if (playerNameTextField.getText() != null) {
            savedGamesList.add(0, new SavedGame(playerNameTextField.getText(), currentSceneNo));
            saveGameData(savedGamesList);
            hideSaveGameMenu();
        } else {playerNameTextField.setText("Please enter a player name.");}
    }
    @FXML
    void overwritePreviousSavedGame(ActionEvent event) {
        // TODO
    }
    @FXML
    void exitSaveGameMenu(MouseEvent event) {
        hideSaveGameMenu();
    }

    public void initialize() throws IOException {
        // Called by JavaFX.
        loadGameSceneData(); // loads scene data from gameScenes.json
        loadScene(currentSceneNo);//scene 1 is loaded upon initialization
        loadSavedGames();// loads saved game data
        updateObservableSavedGames(); //makes saved game data observable for the TableView to display them
    }

    public void loadGameSceneData() throws IOException {
        //loads GameScene objects from json file
        String json = new String(Files.readAllBytes(Paths.get("src/app/gameScenes.json")), StandardCharsets.UTF_8);
        gameScene = new Gson().fromJson(json, GameScene[].class);
        System.out.println("GameScenes loaded.");
    }

    public void loadScene(int sceneNo) {
        //displays current GameScene info to the GUI
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

    public void loadSavedGames() throws IOException {
        //reading saved games from json file
        Type typeToken = new TypeToken<ArrayList<SavedGame>>(){}.getType();
        String json = new String(Files.readAllBytes(Paths.get("src/app/savedGames.json")), StandardCharsets.UTF_8);
        savedGamesList = new Gson().fromJson(json, typeToken);
    }

    public void updateObservableSavedGames() {
        //saved games must be put in an ObservableList for the TableView to display them
        observableSavedGamesList = FXCollections.observableArrayList();
        for ( SavedGame sg: savedGamesList) {
            observableSavedGamesList.add(new ObservableSavedGame(sg));
        } //assigning name, scene, and date properties to appropriate columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("observableName"));
        sceneNoCol.setCellValueFactory(new PropertyValueFactory<>("observableSceneNo"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<>("observableDateSaved"));
        savedGamesTableView.setItems(observableSavedGamesList);//loads data to TableView
    }

    private void saveGameData (List<SavedGame> savedGameList) {
        //writing to json file. saves user progress.
        Gson gson = new Gson();
        String json = gson.toJson(savedGameList);
        try(FileWriter fileWriter = new FileWriter("src/app/savedGames.json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSaveGameMenu() {
        saveGameMenuAnchorPane.setVisible(true);
        saveGameMenuAnchorPane.toFront();
    }

    private void hideSaveGameMenu() {
        saveGameMenuAnchorPane.setVisible(false);
        saveGameMenuAnchorPane.toBack();
    }
}

