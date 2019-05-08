package graphmakertool;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Tooltip;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.HashMap;
import javafx.geometry.Point2D;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 01.05.2019
 * @author Axel Schorcht - 119650  
 */

public class Graph {
  
  // Anfang Attribute
  ArrayList<Vertice> verticles = new ArrayList<Vertice>();
  ArrayList<Edge> edges = new ArrayList<Edge>();
  // Ende Attribute
  
  public Graph() {
  }
  
  
  // Anfang Methoden
  public void addVert(int number){
    
    Vertice v=new Vertice(number);
    v.setCenterX(40+new Random().nextInt(700));
    v.setCenterY(40+new Random().nextInt(450));
    v.setRadius(40);
    while (checkVerticeIntersection(v)||checkVertLineIntersection(v)) { 
      v.setCenterX(40+new Random().nextInt(800));
      v.setCenterY(40+new Random().nextInt(650));
    } // end of while
    v.setRadius(20);
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.toFront();
    
    verticles.add(v);
    
  }
  public void addVert(int number,int[] connections){
    Vertice v=new Vertice(number);
    v.setCenterX(40+new Random().nextInt(800));
    v.setCenterY(40+new Random().nextInt(650));
    v.setRadius(80);
    while (checkVerticeIntersection(v) ||checkVertLineIntersection(v)) { 
      v.setCenterX(40+new Random().nextInt(800));
      v.setCenterY(40+new Random().nextInt(650));
    } // end of while   
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.setRadius(20);
    
    
    ArrayList<Edge> currentEdges = new ArrayList<Edge>();
    for (int i:connections ) {
      
      Edge l = new Edge();
      Vertice end=v;
      for (Vertice v2 : verticles) {
        if(v2.number==i) {
          l.startXProperty().bind(v.centerXProperty());
          l.startYProperty().bind(v.centerYProperty());
          
          l.endXProperty().bind(v2.centerXProperty());
          l.endYProperty().bind(v2.centerYProperty());
          end=v2;
          break;
        }
        
      } // end of for
      
      for (Vertice ver:verticles ){
        if (checkLineVertIntersection(l,ver,end)) { 
          this.addVert(number,connections);
          return;
        } // end of if 
      }
      
      
      currentEdges.add(l);
    } // end of for    
    for (Edge e:currentEdges) {
      edges.add(e);
      
    } // end of for
    v.toFront();
    verticles.add(v);
    
  }              
  
  public void addVert(int number, String name){
    
    Vertice v=new Vertice(number,name);
    v.setCenterX(40+new Random().nextInt(800));
    v.setCenterY(40+new Random().nextInt(650));
    v.setRadius(40);
    while (checkVerticeIntersection(v)||checkVertLineIntersection(v)) { 
      v.setCenterX(40+new Random().nextInt(800));
      v.setCenterY(40+new Random().nextInt(650));
    } // end of while
    v.setRadius(20);
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.toFront();
    
    verticles.add(v);
    
  }
  public void addVert(int number,int[] connections, String name){
    Vertice v=new Vertice(number, name);
    v.setCenterX(40+new Random().nextInt(800));
    v.setCenterY(40+new Random().nextInt(650));
    v.setRadius(40);
    while (checkVerticeIntersection(v) ||checkVertLineIntersection(v)) { 
      v.setCenterX(40+new Random().nextInt(800));
      v.setCenterY(40+new Random().nextInt(650));
    } // end of while   
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.setRadius(20);
    
    
    ArrayList<Edge> currentEdges = new ArrayList<Edge>();
    for (int i:connections ) {
      
      Edge l = new Edge();
      Vertice end=v;
      for (Vertice v2 : verticles) {
        if(v2.number==i) {
          l.startXProperty().bind(v.centerXProperty());
          l.startYProperty().bind(v.centerYProperty());
          
          l.endXProperty().bind(v2.centerXProperty());
          l.endYProperty().bind(v2.centerYProperty());
          end=v2;
          break;
        }
        
      } // end of for
      
      for (Vertice ver:verticles ){
        if (checkLineVertIntersection(l,ver,end)) { 
          this.addVert(number,connections);
          return;
        } // end of if 
      }
      
      
      currentEdges.add(l);
    } // end of for    
    for (Edge e:currentEdges) {
      edges.add(e);
      
    } // end of for
    v.toFront();
    verticles.add(v);
    
  }
  
  public void addVert(int number, String name, String attr){
    
    Vertice v=new Vertice(number,name);
    v.setCenterX(40+new Random().nextInt(800));
    v.setCenterY(40+new Random().nextInt(650));
    v.setRadius(40);
    while (checkVerticeIntersection(v)||checkVertLineIntersection(v)) { 
      v.setCenterX(40+new Random().nextInt(800));
      v.setCenterY(40+new Random().nextInt(650));
    } // end of while
    v.setRadius(20);
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.toFront();
    for (String s:attr.split(",")) {
      String[] pair=s.split(":");
      v.attributes.put(pair[0],pair[1]);
    } // end of for
    verticles.add(v);
    
  }
  public void addVert(int number,int[] connections, String name, String attr){
    Vertice v=new Vertice(number, name);
    v.setCenterX(40+new Random().nextInt(800));
    v.setCenterY(40+new Random().nextInt(650));
    v.setRadius(40);
    while (checkVerticeIntersection(v) ||checkVertLineIntersection(v)) { 
      v.setCenterX(40+new Random().nextInt(800));
      v.setCenterY(40+new Random().nextInt(650));
    } // end of while   
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.setRadius(20);
    
    
    ArrayList<Edge> currentEdges = new ArrayList<Edge>();
    if(connections.length!=0&&connections!=null){
      
      for (int i:connections ) {
        
        Edge l = new Edge();
        Vertice end=v;
        for (Vertice v2 : verticles) {
          if(v2.number==i) {
            l.startXProperty().bind(v.centerXProperty());
            l.startYProperty().bind(v.centerYProperty());
            
            l.endXProperty().bind(v2.centerXProperty());
            l.endYProperty().bind(v2.centerYProperty());
            end=v2;
            break;
          }
          
        } // end of for
        
        for (Vertice ver:verticles ){
          if (checkLineVertIntersection(l,ver,end)) { 
            this.addVert(number,connections);
            return;
          } // end of if 
        }
        
        
        currentEdges.add(l);
      } // end of for 
    }   
    if(attr!=null&&attr.length()>0){
      
      for (String s:attr.split(",")) {
        String[] pair=s.split(":");
        v.attributes.put(pair[0],pair[1]);
      } // end of for
    }
    for (Edge e:currentEdges) {
      edges.add(e);
      
    } // end of for
    v.toFront();
    verticles.add(v);
    
  }
  
  private boolean checkVerticeIntersection(Vertice vert) {
    for (Vertice static_bloc : verticles) {
      if (static_bloc != vert) {
        Shape intersect = Vertice.intersect(vert, static_bloc);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
          return true;
        }
      }
    }
    return false;
  }                       
  
  private boolean checkVertLineIntersection(Vertice vert) {
    for (Shape static_bloc : edges) {
      if (static_bloc != vert) {
        Shape intersect = Vertice.intersect(vert, static_bloc);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean checkLineVertIntersection(Edge edge, Vertice vert, Vertice v) {
    for (Shape static_bloc : verticles) {
      if (static_bloc != edge&&vert!=static_bloc&&v!=static_bloc) {
        Shape intersect = Vertice.intersect(edge, static_bloc);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void removeVertice(Vertice v){
    ArrayList<Edge> edgesToDelete=new ArrayList<Edge>();
    for (Shape static_bloc : edges) {
      if (static_bloc != v) {
        Shape intersect = Vertice.intersect(v, static_bloc);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
          edgesToDelete.add((Edge)static_bloc);
        }
      }
    }
    edges.removeAll(edgesToDelete);
    verticles.remove(v);
    
  }
  
  public void forceDirectedGraphDrawing(){
    double relaxation=0.0378;
    double accuracy=0.01;
    
    for (int count=0; count<=1;count++ ) {
      Vector sumForce=new Vector();
      Vector tmpForce=new Vector();
      for (Vertice v:verticles) {
        v.layoutVar=new Vector();
      } // end of for
      for (Vertice n:verticles) {
        for (Vertice m:verticles) {
          if (n!=m) {
            sumForce=computeForce(n,m);
            
            for (Edge e : edges) {
              Shape intersect1 = Vertice.intersect(n, e);
              Shape intersect2 = Vertice.intersect(m, e);
              if (intersect1.getBoundsInLocal().getWidth() != -1 && intersect2.getBoundsInLocal().getWidth() != -1) {
                tmpForce = computeForce(n,m,e);
                sumForce = sumForce.add(tmpForce);
                break;
              }
              
            }
            n.layoutVar=n.layoutVar.sub(sumForce);
            m.layoutVar=m.layoutVar.add(sumForce);
          } // end of if
        } // end of for
        n.setCenterX(n.getCenterX()+relaxation*n.layoutVar.getX());
      n.setCenterY(n.getCenterY()+relaxation*n.layoutVar.getY());
      } // end of for

    } // end of for
  }
  
  private Vector computeForce(Vertice s, Vertice t){
    double repulsion=200;
    Vector force=new Vector(t.getCenterX(),t.getCenterY()).sub(new Vector(s.getCenterX(),s.getCenterY()));
    double d=Math.sqrt(Math.pow(force.getX(),2)+Math.pow(force.getY(),2));
    force=(force.Mul(repulsion)).Div(Math.pow(d,3));
    return force;
  }
  
  private Vector computeForce(Vertice s, Vertice t,Edge e){
    double attraction=200;
    Vector force=new Vector(t.getCenterX(),t.getCenterY()).sub(new Vector(s.getCenterX(),s.getCenterY()));
    double d=Math.sqrt(Math.pow(force.getX(),2)+Math.pow(force.getY(),2));
    force=(force.Mul(attraction-d)).Div(d);
    return force;
  }
      // Ende Methoden
} // end of Graph
      
