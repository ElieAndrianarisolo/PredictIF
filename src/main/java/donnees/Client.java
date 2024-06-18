package donnees;
//agathe autier joseph rouquette
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String prenom;
    private String nom;
    @Temporal(TemporalType.DATE) 
    private Date dateNaissance;
    private String adressePostale;
    private String genre;
    private String motDePasse;
    @Column(unique=true, nullable=false)    
    private String mail;
    private String tel;
    @OneToOne
    private ProfilAstral profilAstral;
    

    public Client() {
    }

    public Client(String prenom, String nom, Date dateNaissance, String adressePostale, String genre, String motDePasse, String mail, String tel) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.adressePostale = adressePostale;
        this.genre = genre;
        this.motDePasse = motDePasse;
        this.mail = mail;
        this.tel = tel;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }
    
    public Long getId() {
        return id;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getNom() {
        return nom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public String getAdressePostale() {
        return adressePostale;
    }
    public String getGenre() {
        return genre;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public String getMail() {
        return mail;
    }
    public String getTel() {
        return tel;
    }
    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", dateNaissance=" + dateNaissance + ", adressePostale=" + adressePostale + ", genre=" + genre + ", motDePasse=" + motDePasse + ", mail=" + mail + ", tel=" + tel + '}';
    }

   
}
