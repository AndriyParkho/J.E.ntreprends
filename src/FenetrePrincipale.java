import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class FenetrePrincipale extends JFrame implements ActionListener{
	private JPanel container;
	private JRadioButton chBoxNarrateur = new JRadioButton("Narrateur");
	private JRadioButton chBoxJoueur = new JRadioButton("Joueur");
	
	public FenetrePrincipale() {
		this.setTitle("J.E.'ntreprends");
		int fWidht = (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2);
		int fHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.2);
		this.setSize( fWidht, fHeight);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("logo.png");
	    this.setIconImage(image.getImage());
		
		JPanel container = new JPanel();
		JPanel centerPan = new JPanel(new GridLayout(1, 0));
		JButton bValider = new JButton("Valider");
		bValider.addActionListener(this);
		container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(centerPan, BorderLayout.CENTER);
	    container.add(bValider, BorderLayout.SOUTH);
	    
	    Border border = BorderFactory.createTitledBorder("Sélection du rôle");
	    centerPan.setBorder(border);
	    ButtonGroup choixRole = new ButtonGroup();
	    this.chBoxNarrateur.setSelected(true);
	    choixRole.add(this.chBoxNarrateur);
	    centerPan.add(this.chBoxNarrateur);
	    choixRole.add(this.chBoxJoueur);
	    centerPan.add(this.chBoxJoueur);
	    
	    this.setContainer(container);
	}
	
	public void actionPerformed(ActionEvent arg0) {
			this.removePage();
			if(this.chBoxNarrateur.isSelected()) {
				NbEquipeFenetre fChoixEquipe = new NbEquipeFenetre(this);
			} else if(this.chBoxJoueur.isSelected()) {
				ChoixEquipeFenetre fJoueurChoix = new ChoixEquipeFenetre(this);
			}
	}
	
	public JPanel getContainer() {
		return this.container;
	}
	
	public void setContainer(JPanel container) {
		this.container = container;
		this.setContentPane(container);
		this.validate();
	}
	
	public void removePage() {
		this.remove(this.container);
	}
	
	public static void main(String[] args) {
		FenetrePrincipale fPrincipale = new FenetrePrincipale();
		fPrincipale.setVisible(true);
	}

}
