
package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import animatefx.animation.*;
import java.io.IOException;

public class TitleScreenController {

    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button quitGameButton;
    @FXML private AnchorPane root;
    @FXML
    void onLoadGameButtonClick(ActionEvent event) throws IOException {
        loadLayout("resources/load_game_menu_layout.fxml");
    }
    @FXML
    void onNewGameButtonClick(ActionEvent event) throws IOException {
        //Set gameSessionToLoad.json to a new game instance
        SavedGame newGameInstance = new SavedGame("Player", 1, "today");
        LoadGameReaderWriter lgrw = new LoadGameReaderWriter();
        lgrw.updateSessionToLoad(newGameInstance);
        loadLayout("resources/gameplay_layout.fxml");//load GUI
    }
    @FXML
    void onQuitGameButtonClick(ActionEvent event) {
        System.out.println("Quit button clicked");
        System.exit(0);
    }

    public void loadLayout(String layoutFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(layoutFile));
        Stage primaryStage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
}
