package dao;
//agathe autier joseph rouquette
import donnees.Employe;
import java.util.List;
import javax.persistence.TypedQuery;

public class EmployeDAO {
    
    public void create (Employe employe){
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
    public void maj (Employe employe){
        JpaUtil.obtenirContextePersistance().merge(employe);     
    }
    
    public Employe find(Long id){
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);  
    }
    
    public List<Employe> liste(){
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Employe e ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        return query.getResultList();
    }
    
    //permet de trouver un employé grâce à son mail
    public Employe findThanksToMail(String mail) {
        Employe employe = null;
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);
        query.setParameter("mail", mail);
        try {
            employe = query.getSingleResult();
        } catch (Exception e) {
        }
        return employe;
    } 

    //permet de trouver l'employé adapté pour une consultation, càd un employé en ligne, de même genre que le médium et ayant le moins de consultations parmis les employés respectant les deux premiers critères
    public Employe findAdaptedEmployee(String genre){
        
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Employe e WHERE e.ONLINE = true AND e.genre = :genre ORDER BY e.nbConsultations ASC", Employe.class);
        query.setParameter("genre", genre);
        Employe employe = null;
        
        if(!query.getResultList().isEmpty()){
            employe = query.getResultList().get(0);
        }
        return employe;
    }
 
}
