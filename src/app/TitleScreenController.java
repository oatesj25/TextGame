
package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class TitleScreenController {
    private FXMLLoader fxmlLoader;

    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button quitGameButton;
    @FXML private AnchorPane root;
    @FXML
    void onLoadGameButtonClick(ActionEvent event) throws IOException {
        System.out.println("Load game button clicked");
        loadLayout("load_game_menu_layout.fxml");
    }
    @FXML
    void onNewGameButtonClick(ActionEvent event) throws IOException {
        System.out.println("New game button clicked");
        loadLayout("gameplay_layout.fxml");
    }
    @FXML
    void onQuitGameButtonClick(ActionEvent event) {
        System.out.println("Quit button clicked");
        System.exit(0);
    }

    public void loadLayout(String layoutFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(layoutFile));
        root.getChildren().setAll(parent);
    }
}
