package interfacePoly.controller;
import interfacePoly.main.*;
import interfacePoly.popup.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.junit.internal.builders.NullBuilder;

import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * Following MVC format, ControllerInterface controls the behaviour of the interface 
 * i.e what happens when you press a button.
 *
 */
@SuppressWarnings("unused")
public class ControllerInterface implements Initializable{
	@FXML
	private BorderPane background;
	@FXML
	private BorderPane table_container;
	@FXML
	private Pane pane_left;
	@FXML
	private TableView<LignePoly> table;
	@FXML
	private TableColumn<LignePoly,String> nom;
	@FXML
	private TableColumn<LignePoly,Boolean> distrib;
	@FXML
	private TableColumn<LignePoly,String> limites;
	@FXML
	private TableColumn<LignePoly,Double> proba;
	@FXML
	private TableColumn<LignePoly,Integer> dimensions;
	@FXML
	private HBox hbox_top;
	@FXML
	private HBox table_hbox;
	@FXML
	private HBox hbox_bot;
	@FXML
	private Button add_button;
	@FXML
	private Button import_button;
	@FXML
	private Button export_button;
	@FXML
	private Button save_button;
	@FXML
	private Button makedis_button;
	@FXML
	private Button setlim_button;
	@FXML
	private Button mul_button;
	@FXML
	private Button proj_button;
	@FXML
	private Button restri_button;
	@FXML
	private Button graph_button;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * Makes it possible to select multiple lines of the table.
		 */
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		/*
		 * Defines the background color of the elements.
		 */
		background.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
		hbox_top.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		hbox_bot.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,null,null)));

		/*
		 * Sets the table's size to match the size of the whole window.
		 */
		table_container.prefWidthProperty().bind(pane_left.widthProperty()); 
		table_container.prefHeightProperty().bind(pane_left.heightProperty()); 

		/*
		 * Define colunm names and types.
		 */
		nom.setCellValueFactory(new PropertyValueFactory<LignePoly, String>("name"));
		distrib.setCellValueFactory(new PropertyValueFactory<LignePoly, Boolean>("distrib"));
		limites.setCellValueFactory(new PropertyValueFactory<LignePoly, String>("limites"));
		proba.setCellValueFactory(new PropertyValueFactory<LignePoly, Double>("proba"));
		dimensions.setCellValueFactory(new PropertyValueFactory<LignePoly, Integer>("dimension"));
		/*
		 * Fetch user input TODO
		 */

		/*
		 * Input of user data in the table. 
		 */
		ObservableList<LignePoly> data = FXCollections.observableArrayList(new LignePoly(),new LignePoly());
		table.setItems(data);

		/*
		 * Reaction of the "Add a function" button on mouse click. Used to add function(s) to the table manually.
		 */
		add_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				PopupAddFct add= new PopupAddFct();
				try {
					add.start(new Stage());
				} catch (Exception e) {
					System.out.println("Add popup error");
					e.printStackTrace();
				}
			}
		});

		/*
		 * Reaction of the "Import a function" button on mouse click. Used to add function(s) to the table by importing a file.
		 */
		import_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				PopupImport add= new PopupImport();
				try {
					add.start(new Stage());
				} catch (Exception e) {
					System.out.println("Import popup error");
					e.printStackTrace();
				}
			}
		});

		/*
		 * Reaction of the "Export a function" button on mouse click. Used to export function(s) to a file. 
		 */
		export_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				PopupExport add= new PopupExport();
				try {
					add.start(new Stage());
				} catch (Exception e) {
					System.out.println("Export popup error");
					e.printStackTrace();
				}
			}
		});

		/*
		 * Reaction of the "Save project" button on mouse click. Used to save the entire current project to a file.
		 */
		save_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				PopupSave add= new PopupSave();
				try {
					add.start(new Stage());
				} catch (Exception e) {
					System.out.println("Export popup error");
					e.printStackTrace();
				}
			}
		});

		/*
		 * Reaction of the "Make distribution" button on mouse click. Used to transform the selected function(s) into a distribution. 
		 */
		makedis_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//renvoi une erreur s'il y a plus d'une ligne de selectionnee dans le tableau
				if(table.getSelectionModel().getSelectedItems().size()==1){
					table.getSelectionModel().getSelectedItem().getFunc().faireDistrib(); //fait la distrib
					table.getSelectionModel().getSelectedItem().setDistrib(table.getSelectionModel().getSelectedItem().getFunc().verifDistrib()); //verifie bien que ca a marché
					table.refresh();
				}
				else{
					//PopupError add= new PopupError();
					try {
						//add.start(new Stage());
					} catch (Exception e) {
						System.out.println("PopupError exception");
						e.printStackTrace();
					}
				}
			}
		});

		/*
		 * Reaction of the "Set limits" button on mouse click. Used to manually set the range of a function. 
		 */
		setlim_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//renvoi une erreur s'il y a plus d'une ligne de selectionnee dans le tableau
				if(table.getSelectionModel().getSelectedItems().size()==1){
					PopupSetLim add= new PopupSetLim();
					try {
						add.start(new Stage());
						add.getButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
							@Override
							public void handle(MouseEvent arg0) {
								//recupere les donnees entrees dans les deux textbox, les parse en double, puis set les limites
								table.getSelectionModel().getSelectedItem().setLimites(
										Double.parseDouble(add.getText1().getCharacters().toString()), 
										Double.parseDouble(add.getText2().getCharacters().toString())
										);
								table.getSelectionModel().getSelectedItem().setDistrib(table.getSelectionModel().getSelectedItem().getFunc().verifDistrib());
								table.refresh();
								
								//facon temporaire pour fermer une popup, a voir si on peut faire plus propre
								Node  source = (Node)  arg0.getSource(); 
							    Stage stage  = (Stage) source.getScene().getWindow();
							    stage.close();
							}
							
						});
					} catch (Exception e) {
						System.out.println("Set limit error");
						e.printStackTrace();
					}
				}else{
					//PopupError add= new PopupError();
					try {
						//add.start(new Stage());
					} catch (Exception e) {
						System.out.println("PopupError exception");
						e.printStackTrace();
					}
				}
			}
		});

		/*
		 * Reaction of the "Multiply" button on mouse click. Used to multiply two or more functions to get joint probabilities. 
		 */
		mul_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//renvoi une erreur s'il y a moins d'une ligne de selectionnee dans le tableau
				if(!(table.getSelectionModel().getSelectedItems().size()<2)){	
					System.out.println(table.getSelectionModel().getSelectedItems().get(0)); //Seul moyen que j'ai trouvé pour empecher un null pointer exception...				
					LignePoly mul = table.getSelectionModel().getSelectedItems().get(0).multiplier(table.getSelectionModel().getSelectedItems().get(1).getFunc());
					data.add(mul);	
					//j'ai eu la flemme de le faire pour plus de deux lignes en meme temps...je verrais TODO
					
				}else{/*PopupError add= new PopupError();*/
				try {
					//add.start(new Stage());
				} catch (Exception e) {
					System.out.println("PopupError exception");
					e.printStackTrace();
				}
				}
			}
		});

		/*
		 * Reaction of the "Projection" button on mouse click. Used to project the selected function. 
		 */
		proj_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//renvoi une erreur s'il y a plus d'une ligne de selectionnee dans le tableau
				if(table.getSelectionModel().getSelectedItems().size()==1){
					PopupProj add= new PopupProj();
					try {
						add.start(new Stage());
						add.getButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
							@Override
							public void handle(MouseEvent arg0) {
								//recupere le rang des variables a projeter
								Scanner scanner = new Scanner(add.getText().getCharacters().toString());
								List<Integer> choix = new ArrayList<Integer>();
								//stock ca dans une liste
								while (scanner.hasNextInt()) {
								    choix.add(scanner.nextInt());
								}
								scanner.close();
								System.out.println(table.getSelectionModel().getSelectedItems().get(0)); //Seul moyen que j'ai trouvé pour empecher un null pointer exception...	
								//projette
								LignePoly proj = table.getSelectionModel().getSelectedItems().get(0).projection(choix);
								data.add(proj);
								
								//facon temporaire pour fermer une popup, a voir si on peut faire plus propre
								Node  source = (Node)  arg0.getSource(); 
							    Stage stage  = (Stage) source.getScene().getWindow();
							    stage.close();
							}
							
						});
					} catch (Exception e) {
						System.out.println("Projection error");
						e.printStackTrace();
					}
				}else{
					//PopupError add= new PopupError();
					System.out.println("Too many selected");
					try {
						//add.start(new Stage());
					} catch (Exception e) {
						System.out.println("PopupError exception");
						e.printStackTrace();
					}
				}
			}
		});

		/*
		 * Reaction of the "Restriction" button on mouse click. Used to transform the selected function(s) into the same one(s)
		 * with different restrictions on their ranges. Used for conditional probabilities. 
		 */
		restri_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//renvoi une erreur s'il y a plus d'une ligne de selectionnee dans le tableau
				if(table.getSelectionModel().getSelectedItems().size()==1){
					//TODO
				}
			}
		});



		/*
		 * Reaction of the "Graphic" button on mouse click. Used to visualize the selected function. 
		 */
		graph_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				//un switch case serait probablement plus propre
				if(table.getSelectionModel().getSelectedItems().size()!=1){
					System.out.println("Only one function can be visualized. To visualize joint functions, please multiply.");					
					//Add popup error
				}else
				if (table.getSelectionModel().getSelectedItem()==null){
					System.out.println("Please select a function");
				}else
				if (table.getSelectionModel().getSelectedItem().getDimension()>2){
					System.out.println("Dimension too high");
				}else
				if (table.getSelectionModel().getSelectedItem().getDimension()==1){
					try {
						//graph dim 1 normal, a voir si remplacable par jz3d
						new PlotLauncher(table.getSelectionModel().getSelectedItem().getFunc()).start(new Stage()); 
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Erreur graphe dim 1");
					}
				}else
				if (table.getSelectionModel().getSelectedItem().getDimension()==2){
					try {
						new SurfaceDemo(table.getSelectionModel().getSelectedItem().getFunc()).start(new Stage());
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Erreur dim 2");
					}
				}

			}
		});

	}

}
