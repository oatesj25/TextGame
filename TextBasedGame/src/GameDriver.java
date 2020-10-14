
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameDriver extends Application {

	
	public void start(Stage stage) throws Exception {
		try {
		
		Parent root = FXMLLoader.load(getClass().getResource("textGame.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		stage.setTitle("Text-Based Game");
		stage.setScene(scene);
		stage.show();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		launch(args);
	}
	
}
