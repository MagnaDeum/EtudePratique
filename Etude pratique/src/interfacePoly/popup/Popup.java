package interfacePoly.popup;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class Popup extends Application{
		final Stage myDialog;
		String title;
		FlowPane pane1;
		
		public Popup (String name){
			title=name;
			myDialog= new Stage();
			
		}
	
		@Override
		public void start(Stage myDialog) throws Exception {
			myDialog.setAlwaysOnTop(true);
			pane1=new FlowPane();
			pane1.setStyle("-fx-background-color:blue;-fx-padding:10px;");
			Scene scene = new Scene(pane1,500,500);
			myDialog.setScene(scene);
	        myDialog.setTitle(title);
	        myDialog.show();
		}
		
}
