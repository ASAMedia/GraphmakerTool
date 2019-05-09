package graphmakertool;
import java.util.ArrayList;
import java.util.HashMap;
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
import graphmakertool.GraphmakerWindow;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.event.Event;

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

  Scene scene;
  Pane root;
  private Pane pane1 = new Pane();
  private Boolean editMode=false;
  private Button Add = new Button();
  
  private Label lZoom = new Label();
  private ToggleButton tbViewmode = new ToggleButton();
  private ToggleButton tbEditmode = new ToggleButton();
  private Timeline timeline;
  
  private Edge addEdge=new Edge();
  private Button bStart = new Button();
  private Button bStopp = new Button();
  private Button bLoadG2 = new Button();
  private Button bLoadG3 = new Button();
  
  private Text tLoadG1=new Text("Load Graph 1");
  private Button bLoadG1Random = new Button("Random");
  private Button bLoadG1Circle = new Button("Circle");
  private BorderPane bPG1 = new BorderPane(null,tLoadG1,bLoadG1Circle,null,bLoadG1Random);
  
  private Text tLoadG2=new Text("Load Graph 2");
  private Button bLoadG2Random = new Button("Random");
  private Button bLoadG2Circle = new Button("Circle");
  private BorderPane bPG2 = new BorderPane(null,tLoadG2,bLoadG2Circle,null,bLoadG2Random);
  
  private Text tLoadG3=new Text("Load Graph 3");
  private Button bLoadG3Random = new Button("Random");
  private Button bLoadG3Circle = new Button("Circle");
  private BorderPane bPG3 = new BorderPane(null,tLoadG3,bLoadG3Circle,null,bLoadG3Random);
  
  private BorderPane bPG = new BorderPane(bPG2,null,bPG3,null,bPG1);
  
  private BorderPane bPmain = new BorderPane(new Rectangle(10,740,Color.TRANSPARENT),null,null,bPG,null);
  
  // Ende Attribute
  
  public void start(Stage primaryStage) { 
    root = new Pane();
    scene = new Scene(root, 853, 800);
    primaryStage.setResizable(false);
    // Start components
    //Add button viewmode
    tbViewmode.setLayoutX(780);
    tbViewmode.setLayoutY(14);
    tbViewmode.setPrefHeight(25);
    tbViewmode.setPrefWidth(75);
    tbViewmode.setText("View");
    tbViewmode.setSelected(true);
    tbViewmode.setOnAction(
    (event) -> {tbViewmode_Action(event);});
    root.getChildren().add(tbViewmode);
    //Add button editmode
    tbEditmode.setLayoutX(690);
    tbEditmode.setLayoutY(14);
    tbEditmode.setPrefHeight(25);
    tbEditmode.setPrefWidth(75);
    tbEditmode.setText("Edit");
    tbEditmode.setOnAction(
    (event) -> {tbEditmode_Action(event);});
    root.getChildren().add(tbEditmode);
    //Add add button
    Add.setLayoutX(720);
    Add.setLayoutY(735);
    Add.setPrefHeight(60);
    Add.setPrefWidth(100);
    Add.setOnAction(
    (event) -> {Add_Action(event);});
    Add.setText("Add");
    root.getChildren().add(Add);
    //Add start button for animation
    bStart.setLayoutX(518);
    bStart.setLayoutY(730);
    bStart.setPrefHeight(25);
    bStart.setPrefWidth(160);
    bStart.setOnAction(
    (event) -> {bStart_Action(event);});
    bStart.setText("start auto sorting");
    root.getChildren().add(bStart);
    //Add stopp button for animation
    bStopp.setLayoutX(518);
    bStopp.setLayoutY(770);
    bStopp.setPrefHeight(25);
    bStopp.setPrefWidth(160);
    bStopp.setOnAction(
    (event) -> {bStopp_Action(event);});
    bStopp.setText("stopp auto sorting");
    root.getChildren().add(bStopp);
    //add timer for sortanimation
    timeline = new Timeline(new KeyFrame(
    Duration.millis(500),
    ae -> orderGraph()));
    timeline.setCycleCount(Animation.INDEFINITE);
    //add listener to load buttons
    bLoadG1Random.setOnAction((event) -> {bLoadG1Random_Action(event);});
    bLoadG1Circle.setOnAction((event) -> {bLoadG1Circle_Action(event);});
    bLoadG2Random.setOnAction((event) -> {bLoadG2Random_Action(event);});
    bLoadG2Circle.setOnAction((event) -> {bLoadG2Circle_Action(event);});
    bLoadG3Random.setOnAction((event) -> {bLoadG3Random_Action(event);});
    bLoadG3Circle.setOnAction((event) -> {bLoadG3Circle_Action(event);});
    
    //Buid Interface bottom
    tLoadG1.setFont(Font.font("Arial", 18));
    bPG1.setMargin(tLoadG1,new Insets(0, 10, 10, 5));
    bPG1.setMargin(bLoadG1Circle,new Insets(0, 10, 10, 10));
    bPG1.setAlignment(tLoadG1,Pos.TOP_CENTER);
    
    tLoadG2.setFont(Font.font("Arial", 18));
    bPG2.setMargin(tLoadG2,new Insets(0, 10, 10, 5));
    bPG2.setMargin(bLoadG2Circle,new Insets(0, 10, 10, 10));
    bPG2.setAlignment(tLoadG2,Pos.TOP_CENTER);
    
    tLoadG3.setFont(Font.font("Arial", 18));
    bPG3.setMargin(tLoadG3,new Insets(0, 10, 10, 5));
    bPG3.setMargin(bLoadG3Circle,new Insets(0, 10, 10, 10));
    bPG3.setAlignment(tLoadG3,Pos.TOP_CENTER);
    
    bPG.setMargin(bPG2,new Insets(0, 10, 10, 10));
    
    root.getChildren().add(bPmain);
        
    pane1.setLayoutX(0);
    pane1.setLayoutY(0);
    root.getChildren().add(pane1);
    //ConextMenu for delete action
    final ContextMenu contextMenu = new ContextMenu();
    MenuItem delete = new MenuItem("Delete");
    contextMenu.getItems().addAll(delete);
    //define what happens on mouse click in scene
    scene.setOnMouseClicked(e -> {;
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.PRIMARY&& !editMode)//action for left click on vertex without editmode enabled
      {//Show info box with node name and attributes
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
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.SECONDARY&&!editMode)//action for right click on vertex without editmode enabled
      {//show context menu with deltet option
        delete.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            G.removeVertice((Vertice) e.getTarget());
            updateScene();
          }
        });
        contextMenu.show(pane1, e.getScreenX(), e.getScreenY());
      }
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.PRIMARY&&editMode)//action for left click on vertex with editmode enabled
      {//add edge between vertices selected
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
      
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.SECONDARY&&editMode)//action for left click on vertex with editmode enabled
      {//delete Edge between verticles selected
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
    scene.setOnMouseDragged(e -> {//Mouse drag action defined 
      if(e.getTarget() instanceof Vertice&& e.getButton()==MouseButton.PRIMARY) //move vertex on leftclick and drag
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
  
  //Beginn functions
  void updateScene(){                                                          //update/redraw Scene func
    addEdge=new Edge();
    pane1.getChildren().clear();
    pane1.getChildren().removeAll(G.edges); 
    pane1.getChildren().removeAll(G.verticles);
    
    for (Vertice v: G.verticles) {                                             //redraw verticles
      
      final Text   text   = new Text(""+v.number+"     "+v.name);
      text.xProperty().bind(v.centerXProperty());
      text.yProperty().bind(v.centerYProperty());
      Tooltip.install(v, new Tooltip(v.name) );
      pane1.getChildren().addAll(v,text);
      text.toFront();
      
    } // end of for  
    for (Edge v: G.edges) {                                                    //redraw edges 
      pane1.getChildren().add(v);
      
    } // end of for  
  }
  
  void LoadDefaultGraph3(){                                                    //func for creating PreDefaultGraph 3
    G.addVert(1,new int[]{},"Berlin","Size:3000km,Height:100m,Population:30.000");
    G.addVert(2,new int[]{1}, "Leipzig");
    G.addVert(3,new int[]{2}, "Jena");
    G.addVert(4,new int[]{3,2},"Weimar");
    G.addVert(5,new int[]{4},"Erfurt");
    G.addVert(6,new int[]{3},"Nürnberg");
    G.addVert(7,new int[]{6},"München");
    G.addVert(8,new int[]{6,7},"Augsburg");
    G.addVert(9,new int[]{7},"Freiburg");
    G.addVert(10,new int[]{6,8,9},"Stuttgart");
    G.addVert(11,new int[]{10,9},"Mannheim");
    G.addVert(12,new int[]{6,11,5},"Frankfurt");
    G.addVert(13,new int[]{11,12},"Wiesbaden");
    G.addVert(14,new int[]{12,13},"Düsseldorf");
    G.addVert(15,new int[]{14},"Köln");
    G.addVert(16,new int[]{14,15},"Essen");
    G.addVert(17,new int[]{16},"Gelsenkirchen");
    G.addVert(18,new int[]{16,17},"Dortmund");
    G.addVert(19,new int[]{18},"Hannover");
    G.addVert(20,new int[]{18,19},"Bremen");
    G.addVert(21,new int[]{19,1},"Braunschweig");
  }
  
  void LoadDefaultGraph2(){                                                       //func for creating PreDefaultGraph 2
    G.addVert(1,new int[]{},"Knoten 1");
    G.addVert(2,new int[]{1}, "Knoten 2");
    G.addVert(3,new int[]{2}, "Knoten 3");
    G.addVert(4,new int[]{3},"Knoten 4");
    G.addVert(5,new int[]{4},"Knoten 5");
    G.addVert(6,new int[]{5},"Knoten 6");
    G.addVert(7,new int[]{6},"Knoten 7");
    G.addVert(8,new int[]{7},"Knoten 8");
    G.addVert(9,new int[]{8},"Knoten 9");
    G.addVert(10,new int[]{9,1},"Knoten 10");
    
  }
  
  void LoadDefaultGraph1(){                                                       //func for creating PreDefaultGraph 1
    G.addVert(1,new int[]{},"Berlin","Size:3000km,Height:100m,Population:30.000");
    G.addVert(2,new int[]{1}, "Leipzig");
    G.addVert(3,new int[]{1}, "Jena");
    G.addVert(4,new int[]{2,1,4},"Weimar");
    G.addVert(5,new int[]{1,2,4},"Erfurt");
  }
  
  public static void main(String[] args) {
    launch(args);
  } // end of main
  
  private void Reset_Action(Event evt){
    G=new Graph();
    updateScene();
  }
  
  public void Add_Action(Event evt) {                                             //AddBtton function
    if(G.verticles.size()<22){                                                    //check if vert limit is reached else display warning
      String[] values=AddVerticeDialog.display();                                 //Open AddVerticeDialog and return input params 
      if(!values[3].equals("")){                                                  //check for given connections and process them
        
        String[] con=values[3].split(",");
        int[] connections=new int[con.length];
        for (int i=0; i<connections.length;i++) {
          connections[i]=Integer.parseInt(con[i]);
        } // end of for
        G.addVert(Integer.parseInt(values[0]),connections,values[1],values[2]);   //AddVert for with returned params
      }
      else {
        G.addVert(Integer.parseInt(values[0]),new int[0],values[1],values[2]);    //AddVert for with returned params without connections 
      } // end of if-else
      
      updateScene();                                                              //Update Scene
    }
    else {                                                                        //Show warning when maximum vertices number is reached
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("You reached the max amount of vertices");
      alert.setContentText("Unable to add an additional vertice!");
      
      alert.showAndWait();
    } // end of if-else
  } // end of Add_Action
  
  public void bStart_Action(Event evt) {
    
    timeline.play();
    
  } // end of bStart_Action
  
  public void bStopp_Action(Event evt) {
    
    timeline.stop();
    
  } // end of bStopp_Action
  
  public void bLoadG1Random_Action(Event evt) {
    G=new Graph();
    LoadDefaultGraph1();
    G.orderRandom();
    updateScene();
    
  } // end of bLoadG1Random_Action
  
  public void bLoadG1Circle_Action(Event evt) {
    G=new Graph();
    LoadDefaultGraph1();
    G.orderCircleWise();
    updateScene();
    
  } // end of bLoadG1Circle_Action
  
  public void bLoadG2Random_Action(Event evt) {
    G=new Graph();
    LoadDefaultGraph2();
    G.orderRandom();
    updateScene();
    
  } // end of bLoadG2Random_Action
  
  public void bLoadG2Circle_Action(Event evt) {
    G=new Graph();
    LoadDefaultGraph2();
    G.orderCircleWise();
    updateScene();
    
  } // end of bLoadG2Circle_Action
  
  public void bLoadG3Random_Action(Event evt) {
    G=new Graph();
    LoadDefaultGraph3();
    G.orderRandom();
    updateScene();
    
  } // end of bLoadG3Random_Action
  
  public void bLoadG3Circle_Action(Event evt) {
    G=new Graph();
    LoadDefaultGraph3();
    G.orderCircleWise();
    updateScene();
    
  } // end of bLoadG3Circle_Action
  
  
  
  public void tbEditmode_Action(Event evt) {            //switch to editMode func
    firstclick=true;
    firstclick_delete=true;
    tbViewmode.setSelected(false);
    editMode=true;
    
  } // end of tbEditmode_Action
  
  public void tbViewmode_Action(Event evt) {            //switch to viewMode func
    tbEditmode.setSelected(false);
    editMode=false;
    
  } // end of tbViewmode_Action
  
  private void orderGraph(){                            //call forceDirectedGraphDrawing 
    G.forceDirectedGraphDrawing();
    updateScene();
  }
  // Ende Methoden
  // End functions
} // end of class GraphmakerWindow
    
