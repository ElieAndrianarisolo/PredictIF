package donnees;
//agathe autier joseph rouquette
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employe implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String telephonePro;
    @Column(unique=true, nullable=false)
    private String mail;
    private String motDePasse;
    private Boolean ONLINE;
    private String genre;
    private int nbConsultations;


    public Employe() {
    }

    public Employe(String nom, String prenom, String telephonePro, String mail, String motDePasse, Boolean online, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephonePro = telephonePro;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.ONLINE = online;
        this.genre = genre;
        this.nbConsultations = 0;
    }
    
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setTelephonePro(String telephonePro) {
        this.telephonePro = telephonePro;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public void setOnline(Boolean online) {
        this.ONLINE = online;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setNbConsultations(int nbConsultations){
        this.nbConsultations = nbConsultations;
    }
    
    public long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getTelephonePro() {
        return telephonePro;
    }
    public String getMail() {
        return mail;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public Boolean getOnline() {
        return ONLINE;
    }
    public String getGenre() {
        return genre;
    }
    public int getNbConsultations(){
        return nbConsultations;
    }
    
    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephonePro=" + telephonePro + ", mail=" + mail + ", motDePasse=" + motDePasse + ", online=" + ONLINE + ", genre=" + genre + ", nbConsultations" + nbConsultations + '}';
    }




}