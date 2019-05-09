 package graphmakerwindow;
/**
 *
 * Vector2D representing class
 *
 * @version 1.0 vom 01.05.2019
 * @author Axel Schorcht -119650
 */

public class Vector
{
  private double x,y;
  public Vector(){
    setX(0);
    setY(0);
  }
  public Vector(double x,double y){
    this.setX(x);
    this.setY(y);
  }
  public Vector add(Vector v){
    return new Vector(x+v.x , y+v.y);
  }
  public Vector sub(Vector v){
    return new Vector(x-v.x , y-v.y);
  }
  public Vector Mul(double m){
    return new Vector(x*m , y*m);
  }
  public Vector Mul(Vector m){
    return new Vector(x*m.x , y*m.y);
  }
  public Vector Div(double m){
    return new Vector(x/m , y/m);
  }
  
  public double getY(){
    return y;
  }
  public void setY(double y){
    this.y = y;
  }
  public double getX(){
    return x;
  }
  public void setX(double x){
    this.x = x;
  }
  
  public boolean equals(Vector v){
    if (this.x==v.x&&this.y==v.y) {
      return true;
    } // end of if
    return false;
  }
  
}
