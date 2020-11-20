package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class LoadGameMenuController {

    private ObservableList<ObservableSavedGame> observableSavedGamesList;
    private SaveGameReaderWriter saveGameReader = new SaveGameReaderWriter();
    private LoadGameReaderWriter loadGameReaderWriter = new LoadGameReaderWriter();

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<ObservableSavedGame> savedGamesTableView;
    @FXML
    private TableColumn<SavedGame, String> nameCol, dateTimeCol;
    @FXML
    private TableColumn<SavedGame, Integer> sceneNoCol;
    @FXML
    private Button loadMenuBackButton, loadGameButton;

    public LoadGameMenuController() throws IOException {
    }

    @FXML void loadMenuBackButtonClick(ActionEvent event) throws IOException {
        loadLayout("resources/title_screen_layout.fxml");
    }
    @FXML void loadGameButtonClick(ActionEvent event) throws IOException {
        if (savedGamesTableView.getSelectionModel().isEmpty()) {
            loadGameButton.setText("Please select a game to load");
        } else {
            loadUserSelectedGame();
            loadLayout("resources/gameplay_layout.fxml");
        }
    }

    public void initialize() throws IOException {
        updateObservableSavedGames();
    }

    private void loadUserSelectedGame() {
        ObservableSavedGame userSelection = savedGamesTableView.getSelectionModel().getSelectedItem();
        SavedGame currentUserGame = new SavedGame(userSelection.getObservableName(), userSelection.getObservableSceneNo(), userSelection.getObservableDateSaved());
        loadGameReaderWriter.updateSessionToLoad(currentUserGame);
    }

    public void loadLayout(String layoutFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(layoutFile));
        Stage primaryStage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }

    private void updateObservableSavedGames() throws IOException {
        //saved games must be put in an ObservableList for the TableView to display them
        observableSavedGamesList = FXCollections.observableArrayList();
        for (SavedGame sg: saveGameReader.loadSavedGamesList()) {
            observableSavedGamesList.add(new ObservableSavedGame(sg));
        } //assigning name, scene, and date properties to appropriate columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("observableName"));
        sceneNoCol.setCellValueFactory(new PropertyValueFactory<>("observableSceneNo"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<>("observableDateSaved"));
        savedGamesTableView.setItems(observableSavedGamesList);//loads data to TableView
    }
}

