package dao;
//agathe autier joseph rouquette
import donnees.Consultation;
import donnees.Employe;
import donnees.Medium;
import java.util.List;
import javax.persistence.TypedQuery;

public class ConsultationDAO {
    
    public void create (Consultation consultation){
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    public void maj (Consultation consultation){
        JpaUtil.obtenirContextePersistance().merge(consultation);     
    }

    //donne la liste des consultations d'un client donné
    public List<Consultation> listeConsultationsClient(Long id) {
        TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.clientAssocie.id = :id ORDER BY c.date ASC", Consultation.class);
        query.setParameter("id", id);
        return query.getResultList();  
    }
    
    //donne la liste des consultations d'un employé donné
    public List<Consultation> listeConsultationsEmploye(Long id) {
        TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.employeAssocie.id = :id ORDER BY c.date ASC", Consultation.class);
        query.setParameter("id", id);
        return query.getResultList();  
    }
    
    //permet de trouver la consultation associé à un client et à un employé
    public Consultation findConsultation(Long idClient, Long idEmploye){
        Consultation consultation = null;
        TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.clientAssocie.id = :idClient AND c.employeAssocie.id = :idEmploye", Consultation.class);
        query.setParameter("idClient", idClient);
        query.setParameter("idEmploye", idEmploye);
        try {
            consultation = query.getSingleResult();
        } catch (Exception e) {
        }
        return consultation;
    }
    
    //permet de trouver la consultation en cours d'un employé
    public Consultation findConsultationRuning(Long idEmploye){
        Consultation consultation = null;
        TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.employeAssocie.id = :idEmploye ORDER BY c.date ASC", Consultation.class);
        query.setParameter("idEmploye", idEmploye);
        try {
            List<Consultation> consultations = query.getResultList();
            if (!consultations.isEmpty()) {
                consultation = consultations.get(0);
            }
        } catch (Exception e) {
        }
        return consultation;
    }
    
    //permet d'avoir les 5 médiums ayant le plus de consultations
    public List<Medium> top5() {
        TypedQuery<Medium> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c.mediumAssocie FROM Consultation c GROUP BY c.mediumAssocie ORDER BY COUNT(c) DESC", Medium.class);
        query.setMaxResults(5);
        return query.getResultList();
    }
    
    //permet d'avoir la répartition des consultations par médium
    public List<Object[]> repartitionM() {
        TypedQuery<Object[]> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c.mediumAssocie, COUNT(c) FROM Consultation c GROUP BY c.mediumAssocie ORDER BY COUNT(c) DESC", Object[].class);
        return query.getResultList();
    }

    //permet d'avoir la répartition des consultations par employé
    public List<Employe> repartitionE() {
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Employe e ORDER BY e.nbConsultations DESC", Employe.class);
        return query.getResultList();
    }


}
