import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AfficheCarteEtude extends Panel{
	private Image image;
	private int x;
	private int y;
	private boolean suppr = false;
	
	public AfficheCarteEtude(File image, int width, int height) {
		try {
	      this.image = (ImageIO.read(image));
	      int imgHeight = (int) (this.image.getHeight(this)*0.23);
	      int imgWidth = (int) (this.image.getWidth(this)*0.23);
	      this.x = (width - imgWidth)/2;
	      this.image = this.image.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
	    } catch (IOException ie) {
	      ie.printStackTrace();
	    }
	}
	// En réalité affiche les cartes avenants
	public AfficheCarteEtude(File image, int width) {
	      try {
		  this.image = (ImageIO.read(image));
	      int imgHeight = (int) (this.image.getHeight(this)*0.4);
	      int imgWidth = (int) (this.image.getWidth(this)*0.4);
	      this.x = (width - imgWidth)/2;
	      this.image = this.image.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);

		  } catch (IOException ie) {
		      ie.printStackTrace();
		  }
	}
	
	public void setSuppr() {
		this.suppr = true;
	}
	
	public void paint(Graphics g) {
		if(this.suppr) {
			g.drawImage(this.image, x, -5, this);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.red);
			g2.drawLine(0, 0, this.getWidth(), this.getHeight());
		    g.drawLine(0, this.getHeight(), this.getWidth(), 0);
		} else {
			g.drawImage(this.image, x, -5, this);
		}
	}
	
	public void repaint(Graphics g) {
		g.drawImage(this.image, x, -5, this);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g2.setColor(Color.red);
		g2.drawLine(0, 0, this.getWidth(), this.getHeight());
	    g.drawLine(0, this.getHeight(), this.getWidth(), 0);
	}
}
