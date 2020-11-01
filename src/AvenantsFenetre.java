import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class AvenantsFenetre {
	private FenetrePrincipale fPrincipale;
	private JPanel ecranPrec;
	private File[] cartesAvenant;
	private int numEquipe;
	
	public AvenantsFenetre(FenetrePrincipale fPrincipale, JPanel ecranPrec, int numEquipe) {
		this.fPrincipale = fPrincipale;
		this.ecranPrec = ecranPrec;
		this.cartesAvenant = this.getCartesAvenant();
		this.numEquipe = numEquipe;
		
		try {
			Image exCarte = ImageIO.read(this.cartesAvenant[0]);
			int fWidht = (int) (exCarte.getWidth(this.fPrincipale)*0.6*3);
			int fHeight = (int) (exCarte.getHeight(this.fPrincipale)*0.55);
			this.fPrincipale.setSize( fWidht, fHeight);
		} catch(IOException e) {
			int fWidht = (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.3*3);
			int fHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.9);
			this.fPrincipale.setSize( fWidht, fHeight);
			e.printStackTrace();
		}
		
		this.fPrincipale.setLocationRelativeTo(null);
		
		JPanel container = new JPanel();
		JPanel southPan = new JPanel(new GridBagLayout());
		JPanel northPan = new JPanel(new GridBagLayout());
		JPanel centerPan = new JPanel(new GridLayout(1, 3));
		JButton bPrecedent = new JButton("Précédent");
		JLabel titre = new JLabel("Avenants");
		titre.setFont(new Font(titre.getFont().getName(), Font.PLAIN, 60));
		
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		centerPan.setBorder(border);
		northPan.add(titre);
		bPrecedent.addActionListener(new PrecedentListener());
		southPan.add(bPrecedent);
		
		for(int i = 0; i < 3; ++i) {
	    	JPanel imageContainer = new JPanel(new BorderLayout());
	    	centerPan.add(imageContainer);
	    	int imgAreaWidth = (int) (this.fPrincipale.getWidth()/3);
	    	AfficheCarteEtude imagePanel = new AfficheCarteEtude(cartesAvenant[i], imgAreaWidth);
	    	imageContainer.add(imagePanel, BorderLayout.CENTER);
	    	
	    	JButton buttonSuppr = new JButton("Supprimer");
	    	buttonSuppr.addActionListener(new SupprimerListener());
//	    	buttonSuppr.setForeground(Color.black);
//	    	buttonSuppr.setBackground(new Color(180, 0, 0));
	    	JPanel southImgCont = new JPanel();
	    	southImgCont.add(buttonSuppr);
	    	imageContainer.add(southImgCont, BorderLayout.SOUTH);
	    }
		
		container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(centerPan, BorderLayout.CENTER);
	    container.add(southPan, BorderLayout.SOUTH);
	    container.add(northPan, BorderLayout.NORTH);
	    
	    this.fPrincipale.setContainer(container);
	}
	
	class SupprimerListener implements ActionListener{
	    
	    public void actionPerformed(ActionEvent arg0) {
	    	JButton buttonSuppr = (JButton) arg0.getSource();
			JPanel southImgCont = (JPanel) buttonSuppr.getParent();
			JPanel imageContainer = (JPanel) southImgCont.getParent();
			AfficheCarteEtude imagePanel = (AfficheCarteEtude) imageContainer.getComponent(0);
			buttonSuppr.setEnabled(false);
			imagePanel.repaint(imagePanel.getGraphics());
			imagePanel.setSuppr();
	    }
	  }
	      
	class PrecedentListener implements ActionListener{
		
	  public void actionPerformed(ActionEvent e) {
		  fPrincipale.removePage();
		  JoueurEquipeFenetre fEquipe = new JoueurEquipeFenetre(fPrincipale, numEquipe);
	  }
	}
	
	public FenetrePrincipale getfPrincipale() {
		return this.fPrincipale;
	}
	
	private File[] getCartesAvenant() {
		File dossier = new File("./CartesAvenants");
		File[] cartesAvenant = dossier.listFiles();
		return cartesAvenant;
	}

}
