import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AfficheCarteJE extends Panel{
	private Image image;
	private int x;
	private int y;
	
	public AfficheCarteJE(File image, int width, int height) {
		try {
	      this.image = (ImageIO.read(image));
	      int imgHeight = (int) (this.image.getHeight(this)*0.23);
	      int imgWidth = (int) (this.image.getWidth(this)*0.23);
	      System.out.println(imgHeight);
	      System.out.println(imgWidth);
	      this.x = (width - imgWidth)/2;
	      this.image = this.image.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
	    } catch (IOException ie) {
	      ie.printStackTrace();
	    }
	}
	
	public AfficheCarteJE(Image image, int width) {
	      this.image = image;
	      int imgHeight = (int) (this.image.getHeight(this)*0.5);
	      int imgWidth = (int) (this.image.getWidth(this)*0.5);
	      this.x = (width - imgWidth)/2;
	      this.image = this.image.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
	}
	
	public void paint(Graphics g) {
		g.drawImage(this.image, x, -5, this);
	}
}
