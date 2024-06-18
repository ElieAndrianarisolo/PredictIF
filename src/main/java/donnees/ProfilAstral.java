package donnees;
//agathe autier joseph rouquette
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfilAstral implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    private String signeZ;
    private String couleur;
    private String animalT;
    private String signeC;
    
    public ProfilAstral(String signeZ, String signeC, String couleur, String animalT) {
     
        this.signeZ = signeZ;
        this.couleur = couleur;
        this.animalT = animalT;
        this.signeC = signeC;
    }

    public ProfilAstral() {
    }

    public void setSigneZ(String signeZ) {
        this.signeZ = signeZ;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    public void setAnimalT(String animalT) {
        this.animalT = animalT;
    }
    public void setSigneC(String signeC) {
        this.signeC = signeC;
    }
    public String getSigneZ() {
        return signeZ;
    }
    public String getCouleur() {
        return couleur;
    }

    public String getAnimalT() {
        return animalT;
    }

    public String getSigneC() {
        return signeC;
    }


    @Override
    public String toString() {
        return "ProfilAstral{" + " signeZ=" + signeZ + ", couleur=" + couleur + ", animalT=" + animalT + ", signeC=" + signeC + '}';
    }

    

    
    

    
    
    
}
