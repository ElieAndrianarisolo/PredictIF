package donnees;
//agathe autier joseph rouquette
import javax.persistence.Entity;

@Entity
public class Spirite extends Medium{
    
    private String support;

    public Spirite(String support, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.support = support;
    }

    public Spirite() {
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }


    @Override
    public String toString() {
        return '\n' + "- Medium{" + "id=" + id + ", denomination=" + denomination + ", genre=" + genre + ", presentation=" + presentation + ", Spirite" + "support=" + support + '}';
    }
    
    
    
}
