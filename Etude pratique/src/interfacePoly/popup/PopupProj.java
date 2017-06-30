package interfacePoly.popup;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupProj extends Popup{
		// on s'en fout un peu que ce soit dans le start ou en attribut SAUF pour les champs de texte et le bouton valider. 
		// Pour recuperer le texte il faut l'avoir en attribut et faire un getter (cf ci-dessous), idem pour le bouton.
		Text imp;
		TextField tf1;
		Button val;
		HBox h;
		VBox v;
		public PopupProj(){
			super("Ajouter fonction");
			imp = new Text("Chaque variable est représenté par un rang (f(x,y,z) -> x=0, y=1, z=2)\n"
					+ "Taper le rang des variables sur lesquels vous souhaitez projeter:\n");
			tf1 = new TextField();
			val = new Button("Valider");
			h = new HBox(5);
			v = new VBox(10);
		}

		public void start(Stage s) throws Exception {
			super.start(myDialog);
			tf1.setPrefWidth(100);
			h.setStyle("-fx-alignment:center;");
			v.setStyle("-fx-alignment:center;");
			h.getChildren().addAll(imp,tf1);
			v.getChildren().addAll(h,val);
			pane1.setStyle("-fx-background-color:rgb(201,225,206);-fx-padding:10px;-fx-alignment:center;");
			pane1.getChildren().addAll(v);
			myDialog.setWidth(tf1.getPrefWidth()+imp.getLayoutBounds().getWidth()+100);
			myDialog.setHeight(150);
		}
		public Button getButton(){
			return val;
		}
		public TextField getText(){
			return tf1;
		}

}

