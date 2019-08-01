package bouncing.balls;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel.
 */
public class BallWorld extends JPanel {
   private static final int UPDATE_RATE = 30;  // Frames per second (fps)
   
   private ContainerBox box;  // The container rectangular box
  
   private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
   
   // Balls
   private static final int MAX_BALLS = 8; // Max number allowed
   private Ball[] balls = new Ball[MAX_BALLS];
  
   /**
    * Constructor to create the UI components and init the game objects.
    * Set the drawing canvas to fill the screen (given its width and height).
    */
   public BallWorld(int width, int height) {
  
      canvasWidth = width;
      canvasHeight = height;
      
      balls[0] = new Ball(100, 410, 25, 3, 34, Color.BLACK);
      balls[1] = new Ball(80, 350, 25, 2, -114, Color.BLACK);
      balls[2] = new Ball(530, 400, 30, 3, 14, Color.BLACK);
      balls[3] = new Ball(400, 400, 30, 3, 14, Color.BLACK);
      balls[4] = new Ball(400, 50, 35, 1, -47, Color.RED);
      balls[5] = new Ball(480, 320, 35, 4, 47, Color.RED);
      balls[6] = new Ball(80, 150, 40, 1, -114, Color.RED);
      balls[7] = new Ball(100, 240, 40, 2, 60, Color.RED);
      
     /* for (int i = 0; i < MAX_BALLS; ++i) {
           
      // Init the ball at a random location, random speed (inside the box) and moveAngle
      Random rand = new Random();
      int radius = 25 + (int)(Math.random() * 90);
      int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
      int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
      int speed = 1 + (int)(Math.random() * 6);
      int angleInDegree = rand.nextInt(360);
      // Java 'Color' class takes 3 floats, from 0 to 1.
      float r = rand.nextFloat();
      float g = rand.nextFloat();
      float b = rand.nextFloat();
      Color randomColor = new Color(r, g, b);
      
      balls[i] = new Ball(x, y, radius, speed, angleInDegree, randomColor,Color.BLACK);
      }*/
     
      // Init the Container Box to fill the screen
      box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.PINK, Color.WHITE);
     
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
            Dimension dim = c.getSize();
            canvasWidth = dim.width;
            canvasHeight = dim.height;
            // Adjust the bounds of the container to fill the window
            box.set(0, 0, canvasWidth, canvasHeight);
         }
      });
  
      // Start the ball bouncing
      gameStart();
   }
   
   /** Start the balls bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
      Thread gameThread = new Thread() {
         public void run() {
            while (true) {
               // Execute one time-step for the game 
               gameUpdate();
               // Refresh the display
               repaint();
               // Delay and give other thread a chance
               try {
                  Thread.sleep(1000 / UPDATE_RATE);
               } catch (InterruptedException ex) {}
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
   }
   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
       // Check collision between two balls
       for (int i = 0; i < MAX_BALLS; ++i) {
          for (int j = 0; j < MAX_BALLS; ++j) {
             if (i < j) {
                balls[i].checkCollision(balls[j]);
             }
          }
       }

    // Check collision between the balls and the box
	   for (int i = 0; i < MAX_BALLS; ++i) {
          balls[i].moveOneStepWithCollisionDetection(box);
      }
   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);    // Paint background
         // Draw the box and the balls
         box.draw(g);
         
         for (int i = 0; i < MAX_BALLS; ++i) {
             balls[i].draw(g);
         }
             // draw center of circle
             g.setColor(Color.red);
             for (int i = 0; i < MAX_BALLS/2; ++i) {
             g.fillOval((int) balls[i].getX() - 5, (int) balls[i].getY() - 5, 10, 10);
             }
             
             g.setColor(Color.black);
             for (int i = MAX_BALLS/2; i <MAX_BALLS; ++i) {
             g.fillOval((int) balls[i].getX() - 5, (int) balls[i].getY() - 5, 10, 10);
             }
             
      }
  
      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }
}