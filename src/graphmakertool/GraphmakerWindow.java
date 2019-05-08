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
  private Vertice veticeFirsttoAddLine;
  private Button Add = new Button();
  Scene scene;
  Pane root;
  private Slider slider1 = new Slider(1, 4, 1);
  Edge addEdge=new Edge();
  private Button bRefresh = new Button();
  private Button bLoadG1 = new Button();
  private Button bLoadG2 = new Button();
  private Pane pane1 = new Pane();
  private Boolean editMode=false;
  private Label lZoom = new Label();
  private ToggleButton tbViewmode = new ToggleButton();
  private ToggleButton tbEditmode = new ToggleButton();
  // Ende Attribute
  
  public void start(Stage primaryStage) { 
    root = new Pane();
    scene = new Scene(root, 853, 800);
    tbViewmode.setLayoutX(780);
    tbViewmode.setLayoutY(14);
    tbViewmode.setPrefHeight(25);
    tbViewmode.setPrefWidth(75);
    tbViewmode.setText("View");
    tbViewmode.setSelected(true);
    tbViewmode.setOnAction(
    (event) -> {tbViewmode_Action(event);} 
    );
    root.getChildren().add(tbViewmode);
    tbEditmode.setLayoutX(690);
    tbEditmode.setLayoutY(14);
    tbEditmode.setPrefHeight(25);
    tbEditmode.setPrefWidth(75);
    tbEditmode.setText("Edit");
    tbEditmode.setOnAction(
    (event) -> {tbEditmode_Action(event);} 
    );
    root.getChildren().add(tbEditmode);
    
    LoadDefaultGraph2();
    updateScene();  
    
    Add.setLayoutX(750);
    Add.setLayoutY(760);
    Add.setPrefHeight(25);
    Add.setPrefWidth(75);
    Add.setOnAction(
    (event) -> {Add_Action(event);} 
    );
    Add.setText("Add");
    root.getChildren().add(Add);
    primaryStage.setResizable(false);
    bRefresh.setLayoutX(518);
    bRefresh.setLayoutY(706);
    bRefresh.setPrefHeight(25);
    bRefresh.setPrefWidth(75);
    bRefresh.setVisible(true);
    bRefresh.setOnAction(
    (event) -> {bRefresh_Action(event);} 
    );
    bRefresh.setText("refresh");
    root.getChildren().add(bRefresh);
    
    bLoadG1.setLayoutX(118);
    bLoadG1.setLayoutY(706);
    bLoadG1.setPrefHeight(50);
    bLoadG1.setPrefWidth(120);
    bLoadG1.setVisible(true);
    bLoadG1.setOnAction(
    (event) -> {bLoadG1_Action(event);} 
    );
    bLoadG1.setText("Load Graph 1");
    root.getChildren().add(bLoadG1);
    
    bLoadG2.setLayoutX(248);
    bLoadG2.setLayoutY(706);
    bLoadG2.setPrefHeight(50);
    bLoadG2.setPrefWidth(120);
    bLoadG2.setVisible(true);
    bLoadG2.setOnAction(
    (event) -> {bLoadG2_Action(event);} 
    );
    bLoadG2.setText("Load Graph 2");
    root.getChildren().add(bLoadG2);
    
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
          veticeFirsttoAddLine=v;
          v.setFill(Color.CYAN);
          
        } // end of if
        else {
          firstclick=(true);
          veticeFirsttoAddLine.setFill(Color.WHITE);
          addEdge.startXProperty().bind(veticeFirsttoAddLine.centerXProperty());
          addEdge.startYProperty().bind(veticeFirsttoAddLine.centerYProperty());
          addEdge.endXProperty().bind(v.centerXProperty());
          addEdge.endYProperty().bind(v.centerYProperty());
          
          for (Edge ed: G.edges) {
            Shape intersect1 = Vertice.intersect(veticeFirsttoAddLine, ed);
            Shape intersect2 = Vertice.intersect(v, ed);
            if (intersect1.getBoundsInLocal().getWidth() != -1&&intersect2.getBoundsInLocal().getWidth() != -1) {
              G.edges.remove(ed);
              break;              
            }
          } // end of for
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
          veticefirsttodelete.setFill(Color.RED);
        } // end of if
        else {
          firstclick_delete=true;
          veticefirsttodelete.setFill(Color.WHITE);
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
  void LoadDefaultGraph2(){
    G.addVert(1,new int[]{2,21},"Berlin","Size:3000km,Height:100m,Population:30.000");
    G.addVert(2,new int[]{1,3,4}, "Leipzig");
    G.addVert(3,new int[]{2,4,6}, "Jena");
    G.addVert(4,new int[]{3,2,5},"Weimar");
    G.addVert(5,new int[]{4,12},"Erfurt");
    G.addVert(6,new int[]{3,12,10,7,8},"Nürnberg");
    G.addVert(7,new int[]{6,8,9},"München");
    G.addVert(8,new int[]{6,10,7},"Augsburg");
    G.addVert(9,new int[]{7,10,11},"Freiburg");
    G.addVert(10,new int[]{6,8,9,11},"Stuttgart");
    G.addVert(11,new int[]{10,9,12,13},"Mannheim");
    G.addVert(12,new int[]{6,11,13,5},"Frankfurt");
    G.addVert(13,new int[]{11,12,14},"Wiesbaden");
    G.addVert(14,new int[]{12,13,16,15},"Düsseldorf");
    G.addVert(15,new int[]{14,16},"Köln");
    G.addVert(16,new int[]{14,15,17,18},"Essen");
    G.addVert(17,new int[]{18,16},"Gelsenkirchen");
    G.addVert(18,new int[]{16,17,19,20},"Dortmund");
    G.addVert(19,new int[]{18,20,21},"Hannover");
    G.addVert(20,new int[]{18,19},"Bremen");
    G.addVert(21,new int[]{19,1},"Braunschweig");
  }
  void LoadDefaultGraph1(){
    G.addVert(1,new int[]{2,4,5,3},"Berlin","Size:3000km,Height:100m,Population:30.000");
    G.addVert(2,new int[]{1,4,5}, "Leipzig");
    G.addVert(3,new int[]{1}, "Jena");
    G.addVert(4,new int[]{2,1,5,4},"Weimar");
    G.addVert(5,new int[]{1,2,5,4},"Erfurt");
  }
  
  public static void main(String[] args) {
    launch(args);
  } // end of main
  
  public void Add_Action(Event evt) {
    
    
    if(G.verticles.size()<22){
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
    
      G.forceDirectedGraphDrawing();
   
    G.forceDirectedGraphDrawing();
    updateScene();
    
  } // end of bRefresh_Action
  
  public void bLoadG1_Action(Event evt) {
    G = new Graph();
    LoadDefaultGraph1();
    updateScene();
    
  } // end of bLoadG1_Action
  
  public void bLoadG2_Action(Event evt) {
    G = new Graph();
    LoadDefaultGraph2();
    updateScene();
    
  } // end of bLoadG2_Action
  
  
  
  public void tbEditmode_Action(Event evt) {
    firstclick=true;
    firstclick_delete=true;
    tbViewmode.setSelected(false);
    editMode=true;
    
  } // end of tbEditmode_Action
  
  public void tbViewmode_Action(Event evt) {
    tbEditmode.setSelected(false);
    editMode=false;
    
  } // end of tbViewmode_Action
  
  // Ende Methoden
} // end of class GraphmakerWindow
    
