package dao;
//agathe autier joseph rouquette
import donnees.ProfilAstral;

public class ProfilAstralDAO {
    
    public void create (ProfilAstral pa){
        JpaUtil.obtenirContextePersistance().persist(pa);
    }
    
}
