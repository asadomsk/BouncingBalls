package bouncing.balls;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Formatter;


/**
 * The bouncing ball.
 */

public class Ball {
   private float x, y;           // Ball's center x and y
   private float speedX, speedY; // Ball's speed per step in x and y 
   private float radius;         // Ball's radius
   private Color color;         // Ball's color
  


   /**
    * Constructor: For user friendliness, user specifies velocity in speed and
    * moveAngle in usual Cartesian coordinates. Need to convert to speedX and
    * speedY in Java graphics coordinates for ease of operation.
    */

   public Ball(float x, float y, float radius, float speed, float angleInDegree,
	         Color color)  {
      this.x = x;
      this.y = y;
      this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
      this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
      this.radius = radius;
      this.color = color;
   }

   
   
   /** Draw itself using the given graphics context. */
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
   }
   
   
   /** 
    * Make one move, check for collision and react accordingly if collision occurs.
    * 
    */
   
   public void moveOneStepWithCollisionDetection(ContainerBox box) {
      // Get the ball's bounds
              float ballMinX = box.getMinX() + getRadius();
              float ballMinY = box.getMinY() + getRadius();
              float ballMaxX = box.getMaxX() - getRadius();
              float ballMaxY = box.getMaxY() - getRadius();
   
      // Calculate the ball's new position
      setX(getX()+getSpeedX());
      setY(getY()+getSpeedY());
      // Check if the ball moves over the bounds. If so, adjust the position and speed.
      if (getX() < ballMinX) {
         setSpeedX(-getSpeedX()); // Reflect
         setX(ballMinX);     // Re-position the ball at the edge
      } else if (getX() > ballMaxX) {
         setSpeedX(-getSpeedX());
         setX(ballMaxX);
      }
      // y bounds
      if (getY() < ballMinY) {
         setSpeedY(-getSpeedY());
         setY(ballMinY);
      } else if (getY() > ballMaxY) {
         setSpeedY(-getSpeedY());
         setY(ballMaxY);
      }
   }
   
   /** 
    * Check for collision with another ball and react accordingly if collision occurs.
    * 
    */
   
   public void checkCollision(Ball another) {
	   float deltaX;      
	   float deltaY;
	   float distance;
       deltaX = Math.abs(getX() - another.getX());
       deltaY = Math.abs(getY() - another.getY());
       distance = deltaX * deltaX + deltaY * deltaY;
       
       if (distance < (getRadius() + another.getRadius()) * (getRadius() + another.getRadius())) {

           float newxSpeed1 = (getSpeedX() * (getRadius()/5 - another.getRadius()/5) + (2 * another.getRadius()/5 * another.getSpeedX())) / (getRadius()/5 + another.getRadius()/5);

           float newxSpeed2 = (another.getSpeedX() * (another.getRadius()/5 - getRadius()/5) + (2 * getRadius()/5 * getSpeedX())) / (getRadius()/5 + another.getRadius()/5);

           float newySpeed1 = (getSpeedY() * (getRadius()/5 - another.getRadius()/5) + (2 * another.getRadius()/5 * another.getSpeedY())) / (getRadius()/5 + another.getRadius()/5);

           float newySpeed2 = (another.getSpeedY() * (another.getRadius()/5 - getRadius()/5) + (2 * getRadius()/5 * getSpeedY())) / (getRadius()/5 + another.getRadius()/5);

           another.setSpeedX(newxSpeed2);
           another.setSpeedY(newySpeed2);
           setSpeedX(newxSpeed1);
           setSpeedY(newySpeed1);

       }
   }

public float getX() {
	return x;
}


public void setX(float x) {
	this.x = x;
}


public float getY() {
	return y;
}


public void setY(float y) {
	this.y = y;
}


public float getSpeedX() {
	return speedX;
}


public void setSpeedX(float speedX) {
	this.speedX = speedX;
}


public float getSpeedY() {
	return speedY;
}


public void setSpeedY(float speedY) {
	this.speedY = speedY;
}


public float getRadius() {
	return radius;
}


public void setRadius(float radius) {
	this.radius = radius;
}


public Color getColor() {
	return color;
}


public void setColor(Color color) {
	this.color = color;
}
      
   
}
