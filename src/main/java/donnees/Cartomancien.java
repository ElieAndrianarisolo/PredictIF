package donnees;
//agathe autier joseph rouquette
import javax.persistence.Entity;

@Entity
public class Cartomancien extends Medium{

    public Cartomancien(String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
    }

    public Cartomancien() {
    }

    @Override
    public String toString() {
        return '\n' + "- Medium{" + "id=" + id + ", denomination=" + denomination + ", genre=" + genre + ", presentation=" + presentation + ", Cartomancien" + '}';
    }
    
    
}
