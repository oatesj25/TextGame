package app;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
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

    private GameScene[] gameScene = new GameScene[50];//array of GameScenes. We access each scene by its index. Make array longer if needed.
    private int currentSceneNo = 1;//to keep track of where we are in the game
    private ObservableList<ObservableSavedGame> observableSavedGamesList;//TableView requires ObservableList
    private SaveGameReaderWriter saveGameReaderWriter = new SaveGameReaderWriter();

    //instantiating FXML objects
    //main screen components:
    @FXML
    private Pane root;
    @FXML
    private TextFlow choiceAButton, choiceBButton, choiceCButton;
    @FXML
    private Text sceneMessageTextBox, buttonATextBox, buttonBTextBox, buttonCTextBox;
    @FXML
    private Button gameplayMenuButton, saveGameMenuButton;

    // save game menu components:
    @FXML
    private AnchorPane saveGameMenuAnchorPane;
    @FXML
    private Button overwritePreviousGameButton, newSavedGameButton;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private Text saveGameMenuExitButton;
    @FXML
    private TableView<ObservableSavedGame> savedGamesTableView;
    @FXML
    private TableColumn<SavedGame, String> nameCol, dateTimeCol;
    @FXML
    private TableColumn<SavedGame, Integer> sceneNoCol;

    public GameplayController() throws IOException {
    }

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
    private void saveGameMenuButtonClick() throws IOException {
        updateSavedGamesTableView();
        showSaveGameMenu();
    }
    @FXML
    private void onNewSavedGameButtonClick(ActionEvent event) throws IOException {
        createNewSavedGame();
    }
    @FXML
    private void overwritePreviousSavedGameButtonClick(ActionEvent event) {
        overwritePreviousSavedGame();
    }
    @FXML
    private void exitSaveGameMenu(MouseEvent event) {
        hideSaveGameMenu();
    }

    public void initialize() throws IOException {// Called by JavaFX
        loadGameSceneData(); // loads scene data from gameScenes.json
        currentSceneNo = getProperSceneNo();
        loadScene(currentSceneNo); //loads information on which scene to start at
        updateSavedGamesTableView(); //makes saved game data observable for the TableView to display them
    }

    private static int getProperSceneNo() throws IOException {
        LoadGameReaderWriter loadGameReaderWriter = new LoadGameReaderWriter();
        return loadGameReaderWriter.loadGameSession().getSceneNo();
    }

    private void loadGameSceneData() throws IOException {
        //loads GameScene objects from json file
        String json = new String(Files.readAllBytes(Paths.get("src/app/gameScenes.json")), StandardCharsets.UTF_8);
        gameScene = new Gson().fromJson(json, GameScene[].class);
        System.out.println("GameScenes loaded.");
    }

    private void loadScene(int sceneNo) {
        //displays current GameScene info to the GUI
        sceneMessageTextBox.setText(gameScene[sceneNo].getMessage());
        buttonATextBox.setText(gameScene[sceneNo].getChoiceA());
        buttonBTextBox.setText(gameScene[sceneNo].getChoiceB());
        buttonCTextBox.setText(gameScene[sceneNo].getChoiceC());

        //display proper background image
        String currentSceneImagePath = gameScene[currentSceneNo].getImagePath(); //getting background image path if scene has one
        if (currentSceneImagePath.isEmpty()) {
            currentSceneImagePath = "app/images/desert1.png";//default image path
        }
        System.out.println("Loading image: " + currentSceneImagePath);
        BackgroundSize backgroundSize = new BackgroundSize(900, 700,
                true, true, true, true);
        BackgroundImage image = new BackgroundImage(new Image(currentSceneImagePath),  //setting background image
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        root.setBackground(new Background(image));
    }

    private void updateSavedGamesTableView() throws IOException {
        //saved games must be put in an ObservableList for the TableView to display them
        observableSavedGamesList = FXCollections.observableArrayList();
        for ( SavedGame sg: saveGameReaderWriter.loadSavedGamesList()) {
            observableSavedGamesList.add(new ObservableSavedGame(sg));
        } //assigning name, scene, and date properties to appropriate columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("observableName"));
        sceneNoCol.setCellValueFactory(new PropertyValueFactory<>("observableSceneNo"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<>("observableDateSaved"));
        savedGamesTableView.setItems(observableSavedGamesList);//loads data to TableView
    }

    private void overwritePreviousSavedGame() {
        if (savedGamesTableView.getSelectionModel().isEmpty()) {
            overwritePreviousGameButton.setText("Please select a game to overwrite");
        } else {
            ObservableSavedGame userSelection = savedGamesTableView.getSelectionModel().getSelectedItem();
            SavedGame currentUserGame = new SavedGame(userSelection.getObservableName(), currentSceneNo, userSelection.getObservableDateSaved());
            int indexToOverwrite = savedGamesTableView.getSelectionModel().getFocusedIndex();
            saveGameReaderWriter.overwriteSavedGame(currentUserGame, indexToOverwrite);
            hideSaveGameMenu();
            overwritePreviousGameButton.setText("Overwrite previous save point");
        }
    }

    private void createNewSavedGame() {
        if (playerNameTextField.getText() != null) {//user must type in name to save game
            saveGameReaderWriter.saveNewGame(new SavedGame(playerNameTextField.getText(), currentSceneNo));
            hideSaveGameMenu();
        } else {playerNameTextField.setText("Please enter a player name.");}
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

