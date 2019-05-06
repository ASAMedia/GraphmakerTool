package graphmakertool;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import je.NumberField;
import javafx.scene.text.Text;
import java.util.ArrayList;


/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 02.05.2019
 * @author Axel Schorcht -119650
 */

public class AddVerticeDialog {
  private static String[] login;

  public static String[] display() {
    Pane root = new Pane();
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Add Vertice");
    window.setWidth(650);
    window.setHeight(390);
    window.setResizable(false);
    window.initStyle(StageStyle.UTILITY);
    //Number
    Text label1 = new Text("Number:\t\t\t\t\t\t\t\t\t\t\ti.e.: \t5");
    label1.setX(20);
    label1.setY(30);
    NumberField number=new NumberField();
    number.setLayoutX(120);
    number.setLayoutY(10);
    number.setPrefHeight(20);
    number.setPrefWidth(270);
    //Name
    Text label2 = new Text("Name:\t\t\t\t\t\t\t\t\t\t\ti.e.: \tWeimar");
    label2.setX(20);
    label2.setY(80);
    TextField name=new TextField();
    name.setLayoutX(120);
    name.setLayoutY(60);
    name.setPrefHeight(20);
    name.setPrefWidth(270);
    //Attributes
    Text label3 = new Text("Attributes:\t\t\t\t\t\t\t\t\t\ti.e.: \tSize:90,Population:800");
    label3.setX(20);
    label3.setY(130);
    TextField attr=new TextField();
    attr.setLayoutX(120);
    attr.setLayoutY(110);
    attr.setPrefHeight(120);
    attr.setPrefWidth(270);
    //Connections
    Text label4 = new Text("Connections:\t\t\t\t\t\t\t\t\t\ti.e.: \t1,4,5");
    label4.setX(20);
    label4.setY(260);
    TextField conn=new TextField();
    conn.setLayoutX(120);
    conn.setLayoutY(240);
    conn.setPrefHeight(20);
    conn.setPrefWidth(270);
    Button ok=new Button();
    ok.setLayoutX(120);
    ok.setLayoutY(290);
    ok.setPrefHeight(20);
    ok.setPrefWidth(270);
    ok.setText("OK");
    ok.setOnAction(
    (event) -> {
      
      window.close();
    } 
    );
    root.getChildren().addAll(label1,label2,label3,number,name,attr,label4,conn,ok);
    // Set up the JavaFX button controls and listeners and the text fields
    // for the login info. The button listeners set the login values
    
    window.setScene(new Scene(root, 300, 175));
    window.showAndWait();
    
    return new String[]{number.getText(),name.getText(),attr.getText(),conn.getText()};
  }
} 

