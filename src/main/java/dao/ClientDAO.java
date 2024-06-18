package dao;
//agathe autier joseph rouquette
import donnees.Client;
import java.util.List;
import javax.persistence.TypedQuery;

public class ClientDAO {

    public void create(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }

    public Client find(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    
    public List<Client> liste(){
        TypedQuery<Client> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Client c ORDER BY c.nom ASC, c.prenom ASC", Client.class);
        return query.getResultList();
    }
    
    //permet de trouver un client à partir de son mail
    public Client findThanksToMail(String mail) {
        Client client = null;
        TypedQuery<Client> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Client c WHERE c.mail = :mail", Client.class);
        query.setParameter("mail", mail);
        client = query.getSingleResult();
        return client;
    }
    

    

}
