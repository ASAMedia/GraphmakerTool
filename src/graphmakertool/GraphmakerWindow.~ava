package graphmakertool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.*;
import javafx.stage.Stage;
import je.NumberField;
import java.awt.MouseInfo;
import javafx.scene.text.Text;
import java.awt.Panel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 01.05.2019
 * @author Axel Schorcht -119650
 */

public class GraphmakerWindow extends Application {
  // Anfang Attribute
  private Graph G=new Graph();
  private boolean firstclick=true;
  private boolean firstclick_delete=true;
  private Vertice veticefirsttodelete;
  private Button Add = new Button();
  Scene scene;
  Pane root;
  private Slider slider1 = new Slider(1, 4, 1);
  Edge addEdge=new Edge();
  private Button bRefresh = new Button();
  private Pane pane1 = new Pane();
  private Boolean editMode=false;
  private Label lZoom = new Label();
  private ToggleButton tbViewmode = new ToggleButton();
  private ToggleButton tbEditmode = new ToggleButton();
  // Ende Attribute
  
  public void start(Stage primaryStage) { 
    root = new Pane();
    scene = new Scene(root, 753, 535);
    tbViewmode.setLayoutX(694);
    tbViewmode.setLayoutY(14);
    tbViewmode.setPrefHeight(25);
    tbViewmode.setPrefWidth(75);
    tbViewmode.setText("Viewmode");
    tbViewmode.setSelected(true);
    tbViewmode.setOnAction(
    (event) -> {tbViewmode_Action(event);} 
    );
    root.getChildren().add(tbViewmode);
    tbEditmode.setLayoutX(607);
    tbEditmode.setLayoutY(14);
    tbEditmode.setPrefHeight(25);
    tbEditmode.setPrefWidth(75);
    tbEditmode.setText("Editmode");
    tbEditmode.setOnAction(
    (event) -> {tbEditmode_Action(event);} 
    );
    root.getChildren().add(tbEditmode);
    // Ende Komponenten
    // Anfang Komponenten
    LoadDefaultGraph();
    updateScene();  
    
    Add.setLayoutX(399);
    Add.setLayoutY(498);
    Add.setPrefHeight(25);
    Add.setPrefWidth(75);
    Add.setOnAction(
    (event) -> {Add_Action(event);} 
    );
    Add.setText("Add");
    root.getChildren().add(Add);
    primaryStage.setResizable(false);
    bRefresh.setLayoutX(218);
    bRefresh.setLayoutY(506);
    bRefresh.setPrefHeight(25);
    bRefresh.setPrefWidth(75);
    bRefresh.setOnAction(
    (event) -> {bRefresh_Action(event);} 
    );
    bRefresh.setText("refresh");
    root.getChildren().add(bRefresh);
    pane1.setLayoutX(0);
    pane1.setLayoutY(0);
    pane1.setPrefHeight(100);
    pane1.setPrefWidth(100);
    root.getChildren().add(pane1);
    pane1.toBack();
    //ConextMenu for delete action
    final ContextMenu contextMenu = new ContextMenu();
    MenuItem delete = new MenuItem("Delete");
    
    contextMenu.getItems().addAll(delete);
    
    scene.setOnMouseClicked(e -> {;
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.PRIMARY&& !editMode)
      {
        Vertice v=(Vertice)e.getTarget();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(v.name);
        String content="";
        for (HashMap.Entry<String, String> entry : v.attributes.entrySet()) {
          content=content+entry.getKey()+": "+ entry.getValue()+ "\n";
        } // end of for
        alert.setContentText(content);
        
        alert.showAndWait();
      }
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.SECONDARY&&!editMode)
      {
        delete.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            G.removeVertice((Vertice) e.getTarget());
            updateScene();
          }
        });
        contextMenu.show(pane1, e.getScreenX(), e.getScreenY());
      }
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.PRIMARY&&editMode)
      {
        Vertice v=(Vertice)e.getTarget();
        if (firstclick) {
          firstclick=(false);
          
          addEdge.startXProperty().bind(v.centerXProperty());
          addEdge.startYProperty().bind(v.centerYProperty());
        } // end of if
        else {
          firstclick=(true);
          addEdge.endXProperty().bind(v.centerXProperty());
          addEdge.endYProperty().bind(v.centerYProperty());
          G.edges.add(addEdge);
          updateScene();
        } // end of if-else
      }
      
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.SECONDARY&&editMode)
      {
        Vertice v=(Vertice)e.getTarget();
        if (firstclick_delete) {
          firstclick_delete=false;
          veticefirsttodelete=v;
        } // end of if
        else {
          firstclick_delete=true;
          for (Shape static_bloc : G.edges) {
            
            Shape intersect1 = Vertice.intersect(veticefirsttodelete, static_bloc);
            Shape intersect2 = Vertice.intersect(v, static_bloc);
            if (intersect1.getBoundsInLocal().getWidth() != -1&&intersect2.getBoundsInLocal().getWidth() != -1) {
              G.edges.remove(static_bloc);
              updateScene();
              break;
              
            }
          }
          
        } // end of if-else
      }
    });
    scene.setOnMouseDragged(e -> {
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.PRIMARY)
      {
        
        ((Vertice)e.getTarget()).setCenterX(e.getSceneX());
        ((Vertice)e.getTarget()).setCenterY(e.getSceneY());
        
      }
      
    });
    
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    primaryStage.setTitle("GraphmakerWindow");
    primaryStage.setScene(scene);
    primaryStage.show();
  } // end of public GraphmakerWindow
  
  // Anfang Methoden
  void updateScene(){
    addEdge=new Edge();
    pane1.getChildren().clear();
    pane1.getChildren().removeAll(G.edges);   
    for (Edge v: G.edges) {
      pane1.getChildren().add(v);
      
    } // end of for
    
    pane1.getChildren().removeAll(G.verticles);   
    for (Vertice v: G.verticles) {
      
      final Text   text   = new Text(""+v.number+"     "+v.name);
      text.xProperty().bind(v.centerXProperty());
      text.yProperty().bind(v.centerYProperty());
      Tooltip.install(v, new Tooltip(v.name) );
      pane1.getChildren().addAll(v,text);
      text.toFront();
      
    } // end of for
    
    
    
    
  }
  void LoadDefaultGraph(){
    G.addVert(1,new int[]{2,3},"Berlin","Size:3000km,Height:100m,Population:30.000");
    G.addVert(2,new int[]{1,3}, "Leipzig");
    G.addVert(3,new int[]{1,2}, "Hamburg");
    G.addVert(4,new int[]{2});
  }
  
  public static void main(String[] args) {
    launch(args);
  } // end of main
  
  public void Add_Action(Event evt) {
    
    
    if(G.verticles.size()<15){
      String[] values=AddVerticeDialog.display();  
      if(!values[3].equals("")){
        
      String[] con=values[3].split(",");
      int[] connections=new int[con.length];
      for (int i=0; i<connections.length;i++) {
        connections[i]=Integer.parseInt(con[i]);
      } // end of for
        G.addVert(Integer.parseInt(values[0]),connections,values[1],values[2]);
        }
      else {
        G.addVert(Integer.parseInt(values[0]),new int[0],values[1],values[2]);
      } // end of if-else
      
      updateScene();
    }
    else {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("You reached the max amount of vertices");
      alert.setContentText("Unable to add an additional vertice!");
      
      alert.showAndWait();
    } // end of if-else
  } // end of Add_Action
  
  public void bRefresh_Action(Event evt) {
    updateScene();
    
  } // end of bRefresh_Action
  
  
  
  public void tbEditmode_Action(Event evt) {
    tbViewmode.setSelected(false);
    editMode=true;
    
  } // end of tbEditmode_Action
  
  public void tbViewmode_Action(Event evt) {
    tbEditmode.setSelected(false);
    editMode=false;
    
  } // end of tbViewmode_Action
  
  // Ende Methoden
} // end of class GraphmakerWindow
    
