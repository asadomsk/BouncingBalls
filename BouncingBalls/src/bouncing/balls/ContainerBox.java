package bouncing.balls;
import java.awt.*;
/**
 * A rectangular container box, containing the bouncing balls.  
 */
public class ContainerBox {
   private int minX, maxX, minY, maxY;  // Box's bounds 
   private Color colorFilled;   // Box's filled color (background)
   private Color colorBorder;   // Box's border color
   
   /** Constructors */
   public ContainerBox(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
      this.colorFilled = colorFilled;
      this.colorBorder = colorBorder;
   }
   
 
   /** Set or reset the boundaries of the box. */
   public void set(int x, int y, int width, int height) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
   }

   /** Draw itself using the given graphic context. */
   public void draw(Graphics g) {
      g.setColor(colorFilled);
      g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
      g.setColor(colorBorder);
      g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
   }


public int getMinX() {
	return minX;
}


public void setMinX(int minX) {
	this.minX = minX;
}


public int getMaxX() {
	return maxX;
}


public void setMaxX(int maxX) {
	this.maxX = maxX;
}


public int getMinY() {
	return minY;
}


public void setMinY(int minY) {
	this.minY = minY;
}


public int getMaxY() {
	return maxY;
}


public void setMaxY(int maxY) {
	this.maxY = maxY;
}


public Color getColorFilled() {
	return colorFilled;
}


public void setColorFilled(Color colorFilled) {
	this.colorFilled = colorFilled;
}


public Color getColorBorder() {
	return colorBorder;
}


public void setColorBorder(Color colorBorder) {
	this.colorBorder = colorBorder;
}
   
   
}