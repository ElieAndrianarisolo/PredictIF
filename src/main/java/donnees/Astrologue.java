package donnees;
//agathe autier joseph rouquette
import javax.persistence.Entity;

@Entity
public class Astrologue extends Medium {
    
    private String formation;
    private String promotion;

    public Astrologue(String formation, String promotion, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    public Astrologue() {
    }
    public String getFormation() {
        return formation;
    }
    public String getPromotion() {
        return promotion;
    }
    public void setFormation(String formation) {
        this.formation = formation;
    }
    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return '\n' + "- Medium{" + "id=" + id + ", denomination=" + denomination + ", genre=" + genre + ", presentation=" + presentation + ", Astrologue" + "formation=" + formation + ", promotion=" + promotion + '}';
    }



    
}
