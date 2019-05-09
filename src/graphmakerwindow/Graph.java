package graphmakerwindow;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Tooltip;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.HashMap;
/**
 *
 * Graph class
 *
 * @version 1.0 vom 01.05.2019
 * @author Axel Schorcht - 119650  
 */

public class Graph {
  
  // Beginn attributes
  public ArrayList<Vertice> verticles = new ArrayList<Vertice>();  //Dynamic list for vertices
  public ArrayList<Edge> edges = new ArrayList<Edge>();            //Dynamic list for edges
  // End attributes
  
  public Graph() {                              //Constructer
  }
  
  
  // Start  of functions
  public void addVert(int number){              //AddVert with number param
    
    Vertice v=new Vertice(number);
    v.setCenterX(450);
    v.setCenterY(450);
    v.setRadius(40);
    v.setRadius(20);
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.toFront();
    
    verticles.add(v);
    
  }
  public void addVert(int number,int[] connections){      //AddVert with number and connection param
    Vertice v=new Vertice(number);
    v.setCenterX(450);
    v.setCenterY(450);
    v.setRadius(80);
    
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.setRadius(20);
    
    
    ArrayList<Edge> currentEdges = new ArrayList<Edge>();
    for (int i:connections ) {                                      //add connecting edges   
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
      currentEdges.add(l);
    } // end of for    
    for (Edge e:currentEdges) {
      edges.add(e);
      
    } // end of for
    v.toFront();
    verticles.add(v);
    
  }              
  
  public void addVert(int number, String name){        //AddVert with number and name param
    
    Vertice v=new Vertice(number,name);
    v.setCenterX(0);
    v.setCenterY(0);
    v.setRadius(40);
    v.setRadius(20);
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.toFront();
    
    verticles.add(v);
    
  }
  public void addVert(int number,int[] connections, String name){     //AddVert with number, connection and name param
    Vertice v=new Vertice(number, name);
    if(!verticles.isEmpty()){
      
      v.setCenterX(450);
    }
    else {
      v.setCenterX(450);
    } // end of if-else
    v.setCenterY(40);//+new Random().nextInt(650));
    v.setRadius(40);
    
    v.setFill(Color.WHITE);
    v.setStroke(Color.BLACK);
    v.setStrokeWidth(3);
    v.setRadius(20);
    
    
    ArrayList<Edge> currentEdges = new ArrayList<Edge>();
    for (int i:connections ) {                                        //add connecting edges
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
      currentEdges.add(l);
    } // end of for    
    for (Edge e:currentEdges) {
      edges.add(e);
    } // end of for
    v.toFront();
    verticles.add(v);
  }
  
  public void addVert(int number, String name, String attr){          //AddVert with number,name and attributes param
    
    Vertice v=new Vertice(number,name);
    v.setCenterX(450);
    v.setCenterY(450);
    v.setRadius(40);
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
  public void addVert(int number,int[] connections, String name, String attr){  //AddVert with number, connections, name and attributes param
    Vertice v=new Vertice(number, name);
    v.setCenterX(450);
    v.setCenterY(450);
    v.setRadius(40);
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
  
  public void removeVertice(Vertice v){                  //remove Vertex func
    ArrayList<Edge> edgesToDelete=new ArrayList<Edge>();
    for (Shape static_bloc : edges) {                   //remove edges conneted with v
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
  
  public void forceDirectedGraphDrawing(){     //func for gravity based drawing source(page 29 and following): Diplomarbeit, Layout- und Filterverfahren zur Graphdarstellung in GroIMP von Birka Fonkeng, 09. Juni 2007
    double relaxation=0.0378;
    double accuracy=0.01;
    
    for (int count=0; count<=100;count++ ) {
      Vector sumForce=new Vector();
      Vector tmpForce=new Vector();
      double maxForce=0;
      for (Vertice v:verticles) {
        v.layoutVar=new Vector();
      } // end of for
      for (Vertice n:verticles) {
        for (Vertice m:verticles) {
          if (n!=m) {
            sumForce=computeForce(n,m);
            
            for (Edge e : edges) {
              Edge ed=new Edge();
              Vector v1=new Vector(n.getCenterX(),n.getCenterY());
              Vector v2=new Vector(m.getCenterX(),m.getCenterY());
              Vector l1=new Vector(e.getStartX(),e.getStartY());
              Vector l2=new Vector(e.getEndX(),e.getEndY());
              if ((v1.equals(l1)||v1.equals(l2))&&(v2.equals(l1)||v2.equals(l2))) {
                if(v1.equals(l1)){
                  tmpForce = computeForce(n,m,e);
                  sumForce = sumForce.add(tmpForce);
                }
                if(v2.equals(l1)){
                  tmpForce = computeForce(n,m,e);
                  sumForce = sumForce.sub(tmpForce);
                }
                tmpForce = computeForce(n,m,e);
                sumForce = sumForce.add(tmpForce);
                break;
              } // end of if
              
            }
            n.layoutVar=n.layoutVar.sub(sumForce);
            m.layoutVar=m.layoutVar.add(sumForce);
          } // end of if
        } // end of for
        
        n.setCenterX(n.getCenterX()+relaxation*n.layoutVar.getX());
        n.setCenterY(n.getCenterY()+relaxation*n.layoutVar.getY());
        
      } // end of for
      
    } // end of for
    centerLayout();
  }
  
  private Vector computeForce(Vertice s, Vertice t){                       //additional func for gravity based drawing repulsion forces
    double repulsion=2000;
    Vector force=new Vector(t.getCenterX(),t.getCenterY()).sub(new Vector(s.getCenterX(),s.getCenterY()));
    double d=Math.sqrt(Math.pow(force.getX(),2)+Math.pow(force.getY(),2));
    
    force=(force.Mul(repulsion)).Div(Math.pow(d,3));
    return force;
    
    
  }
  
  private Vector computeForce(Vertice s, Vertice t,Edge e){               //additional func for gravity based drawing attraction forces
    double attraction=200;
    Vector force=new Vector(t.getCenterX(),t.getCenterY()).sub(new Vector(s.getCenterX(),s.getCenterY()));
    double d=Math.sqrt(Math.pow(force.getX(),2)+Math.pow(force.getY(),2));
    force=(force.Mul(attraction-d)).Div(d);
    return force;
    
  }
  
  private void centerLayout(){             //func for centering Graph in window
    double x = 0;
    double y = 0;
    for (Vertice v:verticles) {
      if(Math.abs(v.getCenterX())>x)
        x=v.getCenterX();
      
      if(Math.abs(v.getCenterY())>y)
        y=v.getCenterY();
    } // end of for
    for (Vertice v:verticles) {   
      v.setCenterX(v.getCenterX()-x+500);
      v.setCenterY(v.getCenterY()-y+500);
    } // end of for
    
  }
  
  public void orderCircleWise(){         //func for drawing Graph circle wise for a better overview
    int layerCount=2;
    double layerDistance=100;
    double nodeDistance=300;
    double maximumHeight=50;
    int numberOfNotesPerLayer=verticles.size()/layerCount;
    if (verticles.size()%layerCount!=0) {
      numberOfNotesPerLayer+=1;
    } // end of if
    double r=numberOfNotesPerLayer*maximumHeight/Math.PI;
    double phi=2*Math.PI/numberOfNotesPerLayer;
    int currentNodeNumber=0;
    for (Vertice v:verticles) {
      currentNodeNumber+=1;
      if (layerCount>1 && currentNodeNumber%(numberOfNotesPerLayer+1)==0) {
        r=r+layerDistance;
        currentNodeNumber=0;
      } // end of if
      v.setCenterX(r*Math.cos((currentNodeNumber-1)*phi)+450);
      v.setCenterY(r*Math.sin((currentNodeNumber-1)*phi)+400);
    } // end of for
  }
  
  public void orderRandom(){                                  //func for drawing Graph randomly
    boolean ongoing=true;
    int i=0;
    while (ongoing&&i<=20) { 
      ongoing=false;
    i++;
    for (Vertice v:verticles) {
      
      v.setRadius(40);
      for (Shape static_bloc : verticles) {
        if (static_bloc != v) {                             
            Shape intersect = Vertice.intersect(v, static_bloc);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
              v.setCenterX(40+new Random().nextInt(800));
              v.setCenterY(40+new Random().nextInt(650));
              ongoing=true;
            } 
        }
      }
      for (Shape static_bloc : edges) {
        if (static_bloc != v) {                            
            Shape intersect = Vertice.intersect(v, static_bloc);
            Edge e=(Edge)static_bloc;
            if (intersect.getBoundsInLocal().getWidth() != -1&&(!(v.getCenterX()==e.getStartX()&&v.getCenterY()==e.getStartY())&&!((v.getCenterX()==e.getEndX()&&v.getCenterY()==e.getEndY())))) {
              v.setCenterX(40+new Random().nextInt(800));
              v.setCenterY(40+new Random().nextInt(650));
             ongoing=true;
            }
          }
      }
      v.setRadius(20);
    }
      } // end of while
  }
        // End of functions
} // end of Graph
          
