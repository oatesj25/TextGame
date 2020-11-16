package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class LoadGameMenuController {

    @FXML
    private TableView<SavedGame> savedGamesTableView;
    @FXML
    private TableColumn<SavedGame, String> nameCol;
    @FXML
    private TableColumn<SavedGame, Integer> sceneNoCol;
    @FXML
    private TableColumn<SavedGame, String> dateTimeCol;
    @FXML
    private Button loadMenuBackButton;
    @FXML
    private AnchorPane root;

    @FXML void loadMenuBackButtonClick(ActionEvent event) throws IOException {
        Pane titleScreenParent = FXMLLoader.load(getClass().getResource("title_screen_layout.fxml"));
        root.getChildren().setAll(titleScreenParent);
    }

    ObservableList<SavedGame> savedGamesList = FXCollections.observableArrayList(
            new SavedGame("Eric", 4),
            new SavedGame("Jeff", 7),
            new SavedGame("Tina", 13),
            new SavedGame("Demetrius", 12),
            new SavedGame("Amy", 16),
            new SavedGame("Aaron", 3)
    );

    public void initialize() {

        //assign properties of SavedGame class to TableView columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sceneNoCol.setCellValueFactory(new PropertyValueFactory<>("sceneNo"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<>("dateSaved"));
        savedGamesTableView.setItems(savedGamesList);//loads data to TableView
    }
}

