package interfacePoly.popup;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupSetLim extends Popup{
	// on s'en fout un peu que ce soit dans le start ou en attribut SAUF pour les champs de texte et le bouton valider. 
	// Pour recuperer le texte il faut l'avoir en attribut et faire un getter (cf ci-dessous), idem pour le bouton.
	Text imp;
	Text imp2;
	TextField tf1;
	TextField tf2;
	Button val;
	HBox h;
	HBox h2;
	VBox v;
	public PopupSetLim(){
		super("Ajouter fonction");
		imp = new Text("Nouvelle limite inférieure :\n");
		tf1 = new TextField();
		imp2 = new Text("Nouvelle limite supérieure :\n");
		tf2 = new TextField();
		val = new Button("Valider");
		h = new HBox(5);
		h2 = new HBox(5);
		v = new VBox(10);
	}

	public void start(Stage s) throws Exception {
		super.start(myDialog);
		tf1.setPrefWidth(100);
		tf2.setPrefWidth(100);
		h.setStyle("-fx-alignment:center;");
		h2.setStyle("-fx-alignment:center;");
		v.setStyle("-fx-alignment:center;");
		h.getChildren().addAll(imp,tf1);
		h2.getChildren().addAll(imp2,tf2);
		v.getChildren().addAll(h,h2,val);
		pane1.setStyle("-fx-background-color:rgb(201,225,206);-fx-padding:10px;-fx-alignment:center;");
		pane1.getChildren().addAll(v);
		myDialog.setWidth(tf1.getPrefWidth()+imp.getLayoutBounds().getWidth()+100);
		myDialog.setHeight(150);
	}
	public Button getButton(){
		return val;
	}
	public TextField getText1(){
		return tf1;
	}
	public TextField getText2(){
		return tf2;
	}
}
