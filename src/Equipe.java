import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equipe {
	private String nom;
	private File[] cartesEtude = new File[8];
	
	public Equipe(String nom) {
		this.nom = nom;
	}
	
	public File[] setCartesEtude(File[] allCartesEtude) {
		if (allCartesEtude != null && allCartesEtude.length > 8) {
			List<File> listAllCarte = new ArrayList<>(Arrays.asList(allCartesEtude));
            for(int i = 0; i < 8; ++i) {
            	int indexRand = (int)(Math.random() * listAllCarte.size());
            	this.cartesEtude[i] = listAllCarte.get(indexRand);
            	listAllCarte.remove(indexRand);
            }
            allCartesEtude = listAllCarte.toArray(new File[listAllCarte.size()]);
		}
		return allCartesEtude;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public File[] getCartesEtude() {
		return this.cartesEtude;
	}
}
