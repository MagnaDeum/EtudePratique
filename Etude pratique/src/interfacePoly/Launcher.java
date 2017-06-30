package interfacePoly;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * Class that starts the application, contains the primary stage.
 *
 */
public class Launcher extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view/interface.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("PolyExp");
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
	
	
	public static void main (String[] args) {
		launch(args);
	}
}
