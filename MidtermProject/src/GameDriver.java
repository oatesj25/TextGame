
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameDriver extends Application {

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}
	
	public void mainWindow() {
		try {
			System.out.println("GameDriver method mainWindow called");
			FXMLLoader loader = new FXMLLoader(GameDriver.class.getResource("textGame.FXML"));
			Pane pane = loader.load();
			
			GameController gameController = loader.getController();
			gameController.setGameDriver(this);
			
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) throws InterruptedException {
		launch(args);
	}
}
