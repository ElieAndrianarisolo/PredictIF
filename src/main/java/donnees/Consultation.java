package donnees;
//agathe autier joseph rouquette
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Consultation implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Medium mediumAssocie;
    @ManyToOne
    private Client clientAssocie;
    @ManyToOne
    private Employe employeAssocie;
    private String commentaire;
    @Temporal(TemporalType.DATE) 
    private Date date;

    public Consultation(Medium mediumAssocie, Client clientAssocie, Employe employeAssocie, String commentaire,Date date) {
        this.mediumAssocie = mediumAssocie;
        this.clientAssocie = clientAssocie;
        this.employeAssocie = employeAssocie;
        this.commentaire = commentaire;
        this.date = date;
    }

    public Consultation( Client clientAssocie, Date date) {
        this.clientAssocie = clientAssocie;
        this.date = date;
    }

    
    public Consultation() {
    }

    public void setMediumAssocie(Medium mediumAssocie) {
        this.mediumAssocie = mediumAssocie;
    }
    public void setClientAssocie(Client clientAssocie) {
        this.clientAssocie = clientAssocie;
    }
    public void setEmployeAssocie(Employe employeAssocie) {
        this.employeAssocie = employeAssocie;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public Medium getMediumAssocie() {
        return mediumAssocie;
    }
    public Client getClientAssocie() {
        return clientAssocie;
    }
    public Employe getEmployeAssocie() {
        return employeAssocie;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public Date getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", mediumAssocie=" + mediumAssocie + ", clientAssocie=" + clientAssocie + ", employeAssocie=" + employeAssocie + ", commentaire=" + commentaire +", date=" + date + '}';
    }
    
}
