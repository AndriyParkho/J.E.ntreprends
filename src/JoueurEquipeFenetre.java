import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JoueurEquipeFenetre {
	private FenetrePrincipale fPrincipale;
	private int numEquipe;
	private Image carteEquipe;
	
	public JoueurEquipeFenetre(FenetrePrincipale fPrincipale, int numEquipe) {
		this.fPrincipale = fPrincipale;
		this.numEquipe = numEquipe;
		this.carteEquipe = this.getCarteJE(numEquipe);
		
		int fWidht = (int) (this.carteEquipe.getWidth(this.fPrincipale)*0.6);
		int fHeight = (int) (this.carteEquipe.getHeight(this.fPrincipale)*0.55);
		this.fPrincipale.setSize( fWidht, fHeight);
		this.fPrincipale.setLocationRelativeTo(null);
		
		JPanel container = new JPanel();
		JPanel southPan = new JPanel(new GridBagLayout());
		JPanel centerPan = new JPanel(new BorderLayout());
		JButton bPrecedent = new JButton("Précédent");
		JButton bSuivant = new JButton("Suivant");
		
		bPrecedent.addActionListener(new PrecedentListener());
		bSuivant.addActionListener(new SuivantListener());
		southPan.add(bPrecedent);
		southPan.add(bSuivant);
		
		AfficheCarteJE cartePanel = new AfficheCarteJE(this.carteEquipe, fWidht);
		centerPan.add(cartePanel, BorderLayout.CENTER);
		JLabel nomCarte = new JLabel("JE" + this.numEquipe);
		nomCarte.setFont(new Font(nomCarte.getFont().getName(), Font.PLAIN, 20));
		JPanel northImgCont = new JPanel();
    	northImgCont.add(nomCarte);
    	centerPan.add(northImgCont, BorderLayout.NORTH);
		
		container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(centerPan, BorderLayout.CENTER);
	    container.add(southPan, BorderLayout.SOUTH);

	    this.fPrincipale.setContainer(container);
	}
	
	class SuivantListener implements ActionListener{
	    
	    public void actionPerformed(ActionEvent arg0) {
	    	JPanel ecranPrec = fPrincipale.getContainer();
	    	fPrincipale.removePage();
	    	AvenantsFenetre fAvenants = new AvenantsFenetre(fPrincipale, ecranPrec, numEquipe);
	    }
	  }
	      
	class PrecedentListener implements ActionListener{
		
	  public void actionPerformed(ActionEvent e) {
		  fPrincipale.removePage();
		  ChoixEquipeFenetre fChoixEquipe = new ChoixEquipeFenetre(fPrincipale);
	  }
	}
	
	public FenetrePrincipale getfPrincipale() {
		return this.fPrincipale;
	}
	
	private Image getCarteJE(int numEquipe) {
		File dossier = new File("./CartesJE");
		File[] cartesJE = dossier.listFiles();
		String nomCarte = "JE" + numEquipe + ".png";
		for(File carte : cartesJE) {
			if(nomCarte.equals(carte.getName())) {
				try {
					Image carteTrouve = ImageIO.read(carte);
					return carteTrouve;
				} catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}
