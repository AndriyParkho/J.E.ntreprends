import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EquipesFenetre{
	private FenetrePrincipale fPrincipale;
	private int nbEquipe;
	private File[] cartesJE;
	
	public EquipesFenetre(FenetrePrincipale fPrincipale, int nbEquipe) {
		this.fPrincipale = fPrincipale;
		this.nbEquipe = nbEquipe;
		
		int fWidht = (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.1*nbEquipe);
		int fHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.8);
		this.fPrincipale.setSize( fWidht, fHeight);
		this.fPrincipale.setLocationRelativeTo(null);
		
		JPanel container = new JPanel();
		JPanel southPan = new JPanel(new GridBagLayout());
		int nbLigne = 2;
		int nbColonne = nbEquipe/2;
		JPanel centerPan = new JPanel(new GridLayout(nbLigne, nbColonne));
		JButton bPrecedent = new JButton("Précédent");
		JButton bSuivant = new JButton("Suivant");
		bPrecedent.addActionListener(new PrecedentListener());
		bSuivant.addActionListener(new SuivantListener());
		southPan.add(bPrecedent);
		southPan.add(bSuivant);
		container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(centerPan, BorderLayout.CENTER);
	    container.add(southPan, BorderLayout.SOUTH);
	    
	    File[] CartesJE = parcoursCartesJE(this.nbEquipe);
	    for(int i = 0; i < nbEquipe; ++i) {
	    	JPanel imageContainer = new JPanel(new BorderLayout());
	    	centerPan.add(imageContainer);
	    	int imgAreaHeight = (int) (this.fPrincipale.getHeight()*0.8);
	    	int imgAreaWidth = (int) (this.fPrincipale.getWidth()/Math.ceil((double) this.nbEquipe/2));
//	    	if(nbEquipe > 5) {
//	    		imgAreaHeight = (int) (this.fPrincipale.getHeight()*0.8/2);
//		    	imgAreaWidth = (int) (this.fPrincipale.getWidth()/5);
//			}
	    	AfficheCarteJE imagePanel = new AfficheCarteJE(CartesJE[i], imgAreaWidth, imgAreaHeight);
//	    	JLabel imagePanel = new JLabel(new ImageIcon(CartesJE[i].getAbsolutePath()));
	    	imageContainer.add(imagePanel, BorderLayout.CENTER);
	    	JLabel nomImage = new JLabel(removeExtension(CartesJE[i].getName()));
	    	JPanel northImgCont = new JPanel();
	    	northImgCont.add(nomImage);
	    	imageContainer.add(northImgCont, BorderLayout.NORTH);
	    }
	    
	    this.fPrincipale.setContainer(container);
	}
	
	class SuivantListener implements ActionListener{
	    
	    public void actionPerformed(ActionEvent arg0) {
	    	fPrincipale.removePage();
	    	CartesEtudeFenetre fCartesEtude = new CartesEtudeFenetre(fPrincipale, nbEquipe, cartesJE);
	    }
	  }
	      
	class PrecedentListener implements ActionListener{
		
	  public void actionPerformed(ActionEvent e) {
		  fPrincipale.removePage();
		  NbEquipeFenetre fChoixEquipe = new NbEquipeFenetre(fPrincipale);
	  }
	}
	
	public FenetrePrincipale getfPrincipale() {
		return this.fPrincipale;
	}
	
	public static String removeExtension (String str) {
	     if (str == null) return "null";
	     
	     int pos = str.lastIndexOf(".");
	     
	     if (pos == -1) return str;
	     
	     return str.substring(0, pos); 
	    }
	
	private File[] parcoursCartesJE(int nbEquipe) {
		File dossier = new File("./CartesJE");
		File[] cartesJE = dossier.listFiles();
		if (cartesJE != null && cartesJE.length > nbEquipe) {
			List<File> listCarte = new ArrayList<>(Arrays.asList(cartesJE));
            while(listCarte.size() != nbEquipe) {
            	int indexRand = (int)(Math.random() * listCarte.size());
            	listCarte.remove(indexRand);
            }
            cartesJE = listCarte.toArray(new File[listCarte.size()]);
		}
		this.cartesJE = cartesJE;
		return cartesJE;
	}
}
