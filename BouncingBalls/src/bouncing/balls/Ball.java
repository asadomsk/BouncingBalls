package bouncing.balls;

import java.awt.*;
import java.util.Formatter;

/**
 * The bouncing ball.
 */

public class Ball {
   float x, y;           // Ball's center x and y
   float speedX, speedY; // Ball's speed per step in x and y 
   float radius;         // Ball's radius
   private Color colorFilled;  // Ball's color
   private Color colorBorder; //// Ball's border color
  
  
   /**
    * Constructor: user specifies velocity in speed and
    * moveAngle in usual Cartesian coordinates. Need to convert to speedX and
    * speedY in Java graphics coordinates for ease of operation.
    */
   public Ball(float x, float y, float radius, float speed, float angleInDegree,
         Color colorFilled,Color colorBorder) {
      this.x = x;
      this.y = y;
      this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
      this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
      this.radius = radius;
      this.colorFilled = colorFilled;
      this.colorBorder= colorBorder;
   }

   
   /** Draw itself using the given graphics context. */
   public void draw(Graphics g) {
      g.setColor(colorFilled);
      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
      g.setColor(colorBorder);
      g.drawOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
   }
   
   /** 
    * Make one move, check for collision and react accordingly if collision occurs.
    * 
    */
   
   public void moveOneStepWithCollisionDetection(ContainerBox box) {
      // Get the ball's bounds
      float ballMinX = box.minX + radius;
      float ballMinY = box.minY + radius;
      float ballMaxX = box.maxX - radius;
      float ballMaxY = box.maxY - radius;
   
      // Calculate the ball's new position
      x += speedX;
      y += speedY;
      // Check if the ball moves over the bounds. If so, adjust the position and speed.
      if (x < ballMinX) {
         speedX = -speedX; // Reflect
         x = ballMinX;     // Re-position the ball at the edge
      } else if (x > ballMaxX) {
         speedX = -speedX;
         x = ballMaxX;
      }
      // y bounds
      if (y < ballMinY) {
         speedY = -speedY;
         y = ballMinY;
      } else if (y > ballMaxY) {
         speedY = -speedY;
         y = ballMaxY;
      }
   }
   
}
