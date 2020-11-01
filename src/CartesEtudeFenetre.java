import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class CartesEtudeFenetre implements ActionListener{
	private FenetrePrincipale fPrincipale;
	private int nbEquipe;
	private Equipe[] listeEquipe;
	private File[] cartesJE;
	private File[] allCartesEtude;
	
	public CartesEtudeFenetre(FenetrePrincipale fPrincipale, int nbEquipe, File[] cartesJE) {
		this.fPrincipale = fPrincipale;
		this.nbEquipe = nbEquipe;
		this.cartesJE = cartesJE;
		this.allCartesEtude = this.recupCartesEtude();
		this.listeEquipe = new Equipe[nbEquipe];
		
		//Création équipes
		for(int k = 0; k < nbEquipe; ++k) {
			System.out.println(this.cartesJE[k].getName());
			this.listeEquipe[k] = new Equipe(EquipesFenetre.removeExtension(this.cartesJE[k].getName()));
			this.allCartesEtude = this.listeEquipe[k].setCartesEtude(this.allCartesEtude);
		}
		
		int fWidht = (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.1*8);
		int fHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.9);
		this.fPrincipale.setSize( fWidht, fHeight);
		this.fPrincipale.setLocationRelativeTo(null);
		
		JPanel container = new JPanel();
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		
		
		for(int i = 0; i < nbEquipe; ++i) {
			JPanel cartesContainer = new JPanel(new BorderLayout());
			cartesContainer.setPreferredSize(new Dimension((int) (fWidht*0.984), (int) (fHeight*0.91)));
			int nbLigne = 2;
			int nbColonne = 4;
			JPanel centerPan = new JPanel(new GridLayout(nbLigne, nbColonne));
			cartesContainer.add(centerPan, BorderLayout.CENTER);
			File[] carteEtudeCourante = listeEquipe[i].getCartesEtude();
			for(int j = 0; j < carteEtudeCourante.length; ++j) {
				JPanel imageContainer = new JPanel(new BorderLayout());
		    	centerPan.add(imageContainer);
		    	// Ajoute dessin image
		    	int imgAreaHeight = (int) (this.fPrincipale.getHeight()*0.8);
		    	int imgAreaWidth = (int) (this.fPrincipale.getWidth()/4);
		    	AfficheCarteEtude imagePanel = new AfficheCarteEtude(carteEtudeCourante[j], imgAreaWidth, imgAreaHeight);
		    	imageContainer.add(imagePanel, BorderLayout.CENTER);
		    	// Ajoute boutton
		    	JButton buttonSuppr = new JButton("Supprimer");
		    	buttonSuppr.addActionListener(this);
//		    	buttonSuppr.setForeground(Color.black);
//		    	buttonSuppr.setBackground(new Color(180, 0, 0));
		    	JPanel southImgCont = new JPanel();
		    	southImgCont.add(buttonSuppr);
		    	imageContainer.add(southImgCont, BorderLayout.SOUTH);
			}
			onglets.add(listeEquipe[i].getNom(), cartesContainer);
		}		
		
		container.setBackground(Color.white);
	    container.add(onglets);
	    
	    this.fPrincipale.setContainer(container);
	}
	
	public void actionPerformed(ActionEvent arg0) {
			JButton buttonSuppr = (JButton) arg0.getSource();
			JPanel southImgCont = (JPanel) buttonSuppr.getParent();
			JPanel imageContainer = (JPanel) southImgCont.getParent();
			AfficheCarteEtude imagePanel = (AfficheCarteEtude) imageContainer.getComponent(0);
			buttonSuppr.setEnabled(false);
			imagePanel.repaint(imagePanel.getGraphics());
			imagePanel.setSuppr();
	}
	
	public FenetrePrincipale getfPrincipale() {
		return this.fPrincipale;
	}
	
	private File[] recupCartesEtude() {
		File dossier = new File("./CartesEtude");
		File[] cartesEtude = dossier.listFiles();
		return cartesEtude;
	}
}
