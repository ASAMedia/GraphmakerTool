  package graphmakertool;
import javafx.scene.shape.Circle;
import java.util.HashMap;
import javafx.scene.input.MouseEvent;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 01.05.2019
 * @author Axel Schorcht -119650
 */

public class Vertice extends Circle {
  
  // Anfang Attribute
  String name="Default";
  int number;
  HashMap<String, String> attributes = new HashMap<String, String>();
  // Ende Attribute
  
  public Vertice(int number) {
    this.number=number;
  }
  public Vertice(int number, String name) {
    this.number=number;
    this.name=name;
  }

  // Anfang Methoden
 
  // Ende Methoden
} // end of Vertice
