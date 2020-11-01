import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoixEquipeFenetre implements ActionListener{
	private FenetrePrincipale fPrincipale;
	private JComboBox nbEquipeCombo = new JComboBox();
	private int nbEquipeMax = 15;
	
	public ChoixEquipeFenetre(FenetrePrincipale fPrincipale) {
		this.fPrincipale = fPrincipale;
		int fWidht = (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2);
		int fHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.2);
		this.fPrincipale.setSize( fWidht, fHeight);
		this.fPrincipale.setLocationRelativeTo(null);
		
		JPanel container = new JPanel();
		JPanel centerPan = new JPanel(new GridBagLayout());
		JButton bValider = new JButton("Valider");
		bValider.addActionListener(this);
		container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(centerPan, BorderLayout.CENTER);
	    container.add(bValider, BorderLayout.SOUTH);
	    
	    JLabel comboLabel = new JLabel("N° équipe : JE");
	    String[] choixNbEquipe = new String[this.nbEquipeMax];
	    for(int i = 0; i < this.nbEquipeMax ; ++i) {
	    	choixNbEquipe[i] = Integer.toString(i + 1);
	    }
	    this.nbEquipeCombo = new JComboBox(choixNbEquipe);
	    this.nbEquipeCombo.setPreferredSize(new Dimension(50, 20));
	    
	    centerPan.add(comboLabel);
	    centerPan.add(this.nbEquipeCombo);
	    
	    this.fPrincipale.setContainer(container);
	}
	
	public void actionPerformed(ActionEvent arg0) {
			int numEquipe = Integer.parseInt((String) this.nbEquipeCombo.getSelectedItem());
			this.fPrincipale.removePage();
			JoueurEquipeFenetre fEquipes = new JoueurEquipeFenetre(this.fPrincipale, numEquipe);
	}
	
	public FenetrePrincipale getfPrincipale() {
		return this.fPrincipale;
	}


}
