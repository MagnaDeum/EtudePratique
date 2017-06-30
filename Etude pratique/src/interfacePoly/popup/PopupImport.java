package interfacePoly.popup;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupImport extends Popup {
	public PopupImport() {
		super("Importer un projet");
	}

	public void start(Stage s) throws Exception {
		super.start(myDialog);
		Text imp = new Text("Choisissez le fichier\nà importer :");
		TextField tf = new TextField();
		tf.setPrefWidth(400);
		Button br = new Button("Parcourir");
		Button val = new Button("Valider");
		HBox h = new HBox(5);
		VBox v = new VBox(10);
		h.setStyle("-fx-alignment:center;");
		v.setStyle("-fx-alignment:center;");
		h.getChildren().addAll(imp,tf,br);
		v.getChildren().addAll(h,val);
		pane1.setStyle("-fx-background-color:rgb(219,198,211);-fx-padding:10px;-fx-alignment:center;");
		pane1.getChildren().addAll(v);
		myDialog.setWidth(tf.getPrefWidth()+imp.getLayoutBounds().getWidth()+100);
		myDialog.setHeight(150);
	}
}
