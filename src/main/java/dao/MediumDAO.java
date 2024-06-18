package dao;
//agathe autier joseph rouquette
import donnees.Medium;
import java.util.List;
import javax.persistence.TypedQuery;


public class MediumDAO {
    
    public void create (Medium medium){
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
    public Medium find(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    public List<Medium> liste(){
        TypedQuery<Medium> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT m FROM Medium m ORDER BY m.id ASC", Medium.class);
        return query.getResultList();
    }
}
