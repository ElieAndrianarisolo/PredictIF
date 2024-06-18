package metier;
//agathe autier joseph rouquette
import dao.ClientDAO;
import dao.ConsultationDAO;
import donnees.Employe;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.ProfilAstralDAO;
import donnees.Astrologue;
import donnees.Cartomancien;
import donnees.Client;
import donnees.Consultation;
import donnees.Medium;
import donnees.ProfilAstral;
import donnees.Spirite;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import util.AstroNetApi;
import util.Message;


public class Service {
    public boolean DEBUG = false;
    
    
    //SERVICES RELATIFS A LA VUE EMPLOYE 
    
    //initialiserEmployes : permet de créer dans la base de données 15 employés
    // paramètres d'entrées : aucun
    // paramètres de sortie : aucun
    public void initialiserEmployes(){

        Employe E1 = new Employe("FAVRO", "Samuel", "0642049305","sfavro@free.fr","mdp1",true, "H");
        Employe E2 = new Employe("DONODIO GALVIS", "Florine", "0671150503", "florine.donodio-galvis@gmail.com","mdp2",true, "F");
        Employe E3 = new Employe("DEKEW", "Simon", "0713200950","sdekew4845@yahoo.com","mdp3",true, "H");
        Employe E4 = new Employe("LOU", "Flavien", "0437340532", "flavien.lou@yahoo.com","mdp4",true, "H");
        Employe E5 = new Employe("GUOGUEN", "Gabriela", "0719843316", "gguoguen2418@hotmail.com","mdp5",true, "F");
        Employe E6 = new Employe("HERNENDEZ", "Vincent", "0441564193", "vhernendez@yahoo.com","mdp6",true, "H");
        Employe E7 = new Employe("OBADII", "Yaelle", "0647380386", "yaelle.obadii@hotmail.com","mdp7",true, "F");
        Employe E8 = new Employe("CHONI-MENG-HIME", "Emmanuel", "0557745829", "echoni-meng-hime4744@hotmail.com","mdp8",false, "H");
        Employe E9 = new Employe("HUONG", "Adrian Alejandro", "0298347465", "adrian-alejandro.huong@yahoo.com","mdp9",false, "H");
        Employe E10 = new Employe("LITT", "Raphaël", "0755527086", "rlitt6371@hotmail.com","mdp10",false, "H");
        Employe E11 = new Employe("BEUZID", "Gaëtan", "0914112906", "gbeuzid4218@outlook.com","mdp11",false, "H");
        Employe E12 = new Employe("ELILJ", "Amine", "0426628448", "amine.elilj@gmail.com","mdp12",true, "H");
        Employe E13 = new Employe("PAOI", "Othmane", "0549477261", "opaoi@laposte.net","mdp13",false, "H");
        Employe E14 = new Employe("MITOGET", "Ziqing", "0587754642", "ziqing.mitoget@free.fr","mdp14",true, "F");
        Employe E15 = new Employe("LACIINTRE", "Servane", "0373259593", "servane.laciintre@outlook.com","mdp15",false, "F");


        EmployeDAO employeDAO = new EmployeDAO();
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            employeDAO.create(E1);
            employeDAO.create(E2);
            employeDAO.create(E3);
            employeDAO.create(E4);
            employeDAO.create(E5);
            employeDAO.create(E6);
            employeDAO.create(E7);
            employeDAO.create(E8);
            employeDAO.create(E9);
            employeDAO.create(E10);
            employeDAO.create(E11);
            employeDAO.create(E12);
            employeDAO.create(E13);
            employeDAO.create(E14);
            employeDAO.create(E15); 
            JpaUtil.validerTransaction();
            if(DEBUG){System.out.println("Succès d'initialiserEmployes");}
        }catch(Exception ex){
            JpaUtil.annulerTransaction();
            if(DEBUG){System.out.println("Echec d'initialiserEmployes");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
    }
    
    
    //trouverEmployeParId : permet grâce à son identifiant de trouver un employé
    // paramètres d'entrées : long id
    // paramètres de sortie : l'employé associé à l'id, renvoie null si aucun employé n'est associé à cet id
    public Employe trouverEmployeParId(Long idToFind){
        Employe E1 = null;
        EmployeDAO employeDAO = new EmployeDAO();
        try{
            JpaUtil.creerContextePersistance();
            E1 = employeDAO.find(idToFind);
            if(DEBUG){System.out.println("Succès de trouverEmployeParId");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de trouverEmployeParId");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return E1;
    }

    
    //listerTousEmployes : permet de lister tous les employés présents dans la base de données par ordre alphabétique des prénoms
    // paramètres d'entrées : aucun
    // paramètres de sortie : String correspondant à la liste des employés
    public String listerTousEmployes(){

        EmployeDAO employeDAO = new EmployeDAO();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            result = "";
            Employe e =null;
            List <Employe> liste = employeDAO.liste();
            for(int i=0; i<liste.size(); i++){
                e = liste.get(i);
                result += "- "+e.getId()+", "+e.getPrenom()+", "+e.getNom()+", "+e.getMail()+", "+e.getGenre()+".\n";
            }
            if(DEBUG){System.out.println("Succès de listerTousEmployes");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de listerTousEmployes");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }

    
    //authentifierEmploye : permet grâce à un mail et à un mot de passe d'authentifier l'employé concerné
    // paramètres d'entrées : un String pour le mail et un String pour le mot de passe
    // paramètres de sortie : l'employé associé au mail et au mot de passe, renvoie null si aucun employé n'existe
    public Employe authentifierEmploye(String mail, String motdepasse){

        EmployeDAO employeDAO = new EmployeDAO();
        Employe resultat = null;
        try{
            JpaUtil.creerContextePersistance();
            resultat = employeDAO.findThanksToMail(mail);
            if(!resultat.getMotDePasse().equals(motdepasse)){
              resultat = null;
            }
            if(DEBUG){System.out.println("Succès d'authentifierEmploye");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec d'authentifierEmploye");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    //consulterHistoriqueE : permet de consulter toutes les consultations d'un employé donnée à un instant t, de la plus récente à la plus ancienne
    // paramètres d'entrées : l'employé dont on veut connaître l'historique
    // paramètres de sortie : un String contenant l'historique, pour chaque consultation on donne la date, la dénomination du médium, le nom, prénom du client et le commentaire associé
    public String consulterHistoriqueE(Employe employeActuel){

        ConsultationDAO consultationDAO = new ConsultationDAO();
        Long id = employeActuel.getId();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            List <Consultation> liste = consultationDAO.listeConsultationsEmploye(id);
            result = "";
            Consultation c;
            for(int i = 0; i<liste.size(); i++){
                c= liste.get(i);
                result += "- consultation du "+c.getDate()+" avec "+c.getMediumAssocie().getDenomination()+", avec le client "+c.getClientAssocie().getNom()+" "+c.getClientAssocie().getPrenom()+", votre commentaire : "+c.getCommentaire()+".\n";
            }
            if(DEBUG){System.out.println("Succès de consulterHistoriqueE");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de consulterHistoriqueE");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
    
    
    //consulterHistoriqueCpourE : permet à un employé de consulter l'historique d'un client
    // paramètres d'entrées : le client choisi
    // paramètres de sortie : un String contenant l'historique du client, pour chaque consultation, on donne la date et le médium associé, le commentaire et l'employé associé
    public String consulterHistoriqueCpourE(Client clientActuel){

        ConsultationDAO consultationDAO = new ConsultationDAO();
        Long id = clientActuel.getId();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            List <Consultation> liste = consultationDAO.listeConsultationsClient(id);
            result = "";
            Consultation c;
            for(int i=0; i<liste.size();i++){
                c = liste.get(i);
                result += "- consultation du "+c.getDate()+" avec "+c.getMediumAssocie().getDenomination()+" incarné par "+c.getEmployeAssocie().getNom()+" "+c.getEmployeAssocie().getPrenom()+", commentaire :"+c.getCommentaire()+". \n";
            }
            if(DEBUG){System.out.println("Succès de consulterHistorique");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de consulterHistorique");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
    
    //commenterConsultation : permet à un employé, une fois la consultation terminée de la commenter
    // paramètres d'entrées : un String contenant le commentaire et la consultation qui vient de se terminer
    // paramètres de sortie : aucun
    public void commenterConsultation(String com, Consultation c){
        ConsultationDAO consultationDAO = new ConsultationDAO();
        EmployeDAO employeDAO = new EmployeDAO();
        try{
            JpaUtil.creerContextePersistance();
            if(c != null){
               JpaUtil.ouvrirTransaction();
               c.setCommentaire(com);
               consultationDAO.maj(c);
               c.getEmployeAssocie().setOnline(true);
               employeDAO.maj(c.getEmployeAssocie());
               JpaUtil.validerTransaction();
            }
            if(DEBUG){System.out.println("Succès de commenterConsultation");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de commenterConsultation");}
            JpaUtil.annulerTransaction();
        }finally{
            JpaUtil.fermerContextePersistance();
        }
    }
    
    
    //trouverConsultationCourante : permet pour un employé donnée de trouver la consultation en cours
    // paramètres d'entrées : l'employé concerné
    // paramètres de sortie : la consultation en cours
    public Consultation trouverConsultationCourante(Employe employe){
        ConsultationDAO consultationDAO = new ConsultationDAO();
        Consultation c = null;
        try{
            JpaUtil.creerContextePersistance();
            c = consultationDAO.findConsultationRuning(employe.getId());
            if(c!=null && c.getCommentaire() == null){
                Client client = c.getClientAssocie();
                Message.envoyerNotification(client.getTel(), "Bonjour "+client.getPrenom()+". J'ai bien reçu votre demande de consultation du "+ c.getDate()+" \nVous pouvez dès à présent me contacter au "+employe.getTelephonePro()+". A tout de suite ! Mediumiquement votre, "+c.getMediumAssocie().getDenomination());
            }else if(c!=null && c.getCommentaire() != null){
                c = null;
                //si une consultation a déjà été commentée, on la considère terminée
            }
            if(DEBUG){ System.out.println("Succès de trouverConsultation");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de trouverConsultation");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
     
    
    //obtenirPrediction : permet pour un client donné et pour des notes dans 3 catégories, d'obtenir une prédiction pour chaque catégorie
    // paramètres d'entrées : les notes des 3 catégories et le client concerné
    // paramètres de sortie : un String contenant la prédicition
    public String obtenirPrediction(String nA, String nS, String nT, Client client) throws IOException{
        String result =null;

        try{
            AstroNetApi astroApi = new AstroNetApi();
            int niveauAmour = Integer.parseInt(nA);
            int niveauSante = Integer.parseInt(nS);
            int niveauTravail = Integer.parseInt(nT);
            List<String> predictions = astroApi.getPredictions(client.getProfilAstral().getCouleur(), client.getProfilAstral().getAnimalT(), niveauAmour, niveauSante, niveauTravail);
            String predictionAmour = predictions.get(0);
            String predictionSante = predictions.get(1);
            String predictionTravail = predictions.get(2);
            result = "~<[ Prédictions ]>~)\n";
            result += "[ Amour ] " + predictionAmour+"\n";
            result += "[ Santé ] " + predictionSante+"\n";
            result += "[Travail] " + predictionTravail+"\n";
            if(DEBUG){System.out.println("Succès de obtenirPrediction");}            
        }catch(Exception ex){
            ex.printStackTrace();
            if(DEBUG){System.out.println("Echec de obtenirPrediction");}
        }
        
        return result;
    }
    
    
    //trouverClientEnCours : permet d'obtenir le client associé à la consultation en cours d'un employé
    // paramètres d'entrées : l'id de l'employé faisant la demande
    // paramètres de sortie : le client associé, renvoie null si l'employé n'a aucune consultation en cours
    public Client trouverClientEnCours(Long idEmploye){
        ConsultationDAO consultationDAO = new ConsultationDAO();
        Consultation c;
        Client client = null;
        try{
            JpaUtil.creerContextePersistance();
            c = consultationDAO.findConsultationRuning(idEmploye);
            if(c!=null){
                client = c.getClientAssocie();
            }

            if(DEBUG){System.out.println("Succès de trouverClientEnCours");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de trouverClientEnCours");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }      
        return client;
    }
    
      
    //obtenirStat1 : permet d'accéder au top5 des médiums
    // paramètres d'entrées : aucun
    // paramètres de sortie : un String contenant les statistiques
    public String obtenirStat1(){
        ConsultationDAO consultationDAO = new ConsultationDAO();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            List <Medium> liste = consultationDAO.top5();
            result =">Voici le top 5 des médiums : \n";
            Medium m;
            int score = 0;
            for(int i=0; i< liste.size();i++){
                m = liste.get(i);
                score = i+1;
                result += "- "+score+": "+m.getDenomination()+"\n";
            }
            if(DEBUG){System.out.println("Succès de obtenirStat1");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de obtenirStat1");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }      
        return result;
    }
   
   
    //obtenirStat2 : permet d'accéder à la répartition des consultations par médiums
    // paramètres d'entrées : aucun
    // paramètres de sortie : un String contenant les statistiques
    public String obtenirStat2(){
        ConsultationDAO consultationDAO = new ConsultationDAO();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            List<Object[]> resultList = consultationDAO.repartitionM();
            Medium m;
            result = ">Voici la répartition des consultations par médiums : \n";
            for (Object[] resultat : resultList) {
                m = (Medium) resultat[0];
                Long count = (Long) resultat[1];
                result += m.getDenomination() + " : " + count+"\n";
            }
            if(DEBUG){System.out.println("Succès de obtenirStat2");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de obtenirStat2");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
        
    
    //obtenirStat3 : permet d'accéder à la répartition des consultations par employé
    // paramètres d'entrées : aucun
    // paramètres de sortie : un String contenant les statistiques
    public String obtenirStat3(){
        ConsultationDAO consultationDAO = new ConsultationDAO();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            List<Employe> resultList = consultationDAO.repartitionE();
            result = ">Voici la répartition des consultations par employé : \n";
            for (Employe employe : resultList) {
                int count = employe.getNbConsultations();
                result += employe.getNom() + " : " + count+"\n";
            }
            if(DEBUG){System.out.println("Succès de obtenirStat3");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de obtenirStat3");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }  
        return result;
    }       
    

    
    //SERVICES RELATIFS A LA VUE CLIENT
       
    
    //inscriptionClient : permet de persister un client donné
    // paramètres d'entrées : le client
    // paramètres de sortie : aucun
    public void inscriptionClient(Client C1) throws IOException{
        
        AstroNetApi astroApi = new AstroNetApi();
        String prenom = C1.getPrenom();
        Date dateNaissance = C1.getDateNaissance();

        List<String> profil = astroApi.getProfil(prenom, dateNaissance);
        String signeZodiaque = profil.get(0);
        String signeChinois = profil.get(1);
        String couleur = profil.get(2);
        String animal = profil.get(3);

        ProfilAstral profilAstral = new ProfilAstral(signeZodiaque, signeChinois, couleur,animal );
        C1.setProfilAstral(profilAstral);
        ClientDAO clientDAO = new ClientDAO();
        ProfilAstralDAO paDAO = new ProfilAstralDAO();
        
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDAO.create(C1);
            paDAO.create(profilAstral);
            JpaUtil.validerTransaction();
            Message.envoyerMail("contact@predict.if", C1.getMail(), "Bienvenue chez Predict'IF", "Bonjour "+C1.getPrenom()+", nous vous confirmons votre inscription au service Predict'IF. \nRendez vous sur notre site pour consulter votre profil astrologique et profitez des incroyables dons de nos médiums.");
            if(DEBUG){System.out.println("Succès d'inscriptionClient et création de son profil astral");}
        }catch(Exception ex){
            Message.envoyerMail("contact@predict.if", C1.getMail(), "Echec de l'inscription chez Predict'IF", "Bonjour "+C1.getPrenom()+", votre inscription au service Predict'IF a malencontreusement échoué. \nMerci de recommencer ultérieurement.");
            JpaUtil.annulerTransaction();
            if(DEBUG){System.out.println("Echec d'inscriptionClient ou du profil astral");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
    }
    
    
    //trouverClientParId : permet de trouver le client associé à un Id
    // paramètres d'entrées : long id
    // paramètres de sortie : le client associé à l'Id, renvoie nul si aucun client n'est associé à cet Id
    public Client trouverClientParId(Long idToFind){
        Client C1 = null;
        ClientDAO clientDAO = new ClientDAO();
        try{
            JpaUtil.creerContextePersistance();
            C1 = clientDAO.find(idToFind);
            if(DEBUG){System.out.println("Succès de trouverClientParId");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de trouverClientParId");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return C1;
    }
    
    
    //listerTousClients : permet de lister tous les clients présents dans la base de données, triés par ordre alphabétique
    // paramètres d'entrées : aucun
    // paramètres de sortie : un String contenant la liste de tous les clients
    public String listerTousClients(){

        ClientDAO clientDAO = new ClientDAO();
        String result = null;

        try{
            JpaUtil.creerContextePersistance();
            Client c = null;
            List <Client> liste = clientDAO.liste();
            result ="";
            for(int i= 0; i<liste.size();i++){
                c = liste.get(i);
                result += "- client n°"+c.getId()+", "+c.getPrenom()+", "+c.getNom()+", "+c.getGenre()+".\n";
            }
            if(DEBUG){System.out.println("Succès de listerTousClients");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de listerTousClients");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
 
    
    //authentifierClient : permet d'authentifier un client donné grâce à son mail et à son mot de passe
    // paramètres d'entrées : un String mail et un String mot de passe
    // paramètres de sortie : le client associé, renvoie null si aucun client n'est associé
    public Client authentifierClient(String mail, String motdepasse){

        ClientDAO clientDAO = new ClientDAO();
        Client resultat = null;
        try{
            JpaUtil.creerContextePersistance();
            resultat = clientDAO.findThanksToMail(mail);
            if(!resultat.getMotDePasse().equals(motdepasse)){
              resultat = null;
            }
            if(DEBUG){System.out.println("Succès d'authentifierClient");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec d'authentifierClient");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }   
    
    
    //demanderConsultation : permet à client de demander une consultation avec un médium en particulier
    // paramètres d'entrées : le client demandant et l'id du médium associé
    // paramètres de sortie : la consultation, renvoie null si aucun employé adéquat n'est disponible pour incarner le médium oui si le médium n'existe pas 
    public Consultation demanderConsultation(Client client, String id){

        ConsultationDAO consultationDAO = new ConsultationDAO();
        MediumDAO mediumDAO = new MediumDAO();
        EmployeDAO employeDAO = new EmployeDAO();
        Consultation c = null;
        
        try{
            JpaUtil.creerContextePersistance();   
            Medium MA = mediumDAO.find(Long.parseLong(id));

            if(MA!=null){
                String genre = MA.getGenre();
                String appelation;
                if(genre.equals("H")){ appelation = "Mr";
                }else{ appelation = "Mme"; }
  
                Employe employe = employeDAO.findAdaptedEmployee(genre);
                if(DEBUG){System.out.println("Employe adéquat trouvé "+employe);}
                
                if(employe !=null){
                    Date date = new Date();
                    c = new Consultation (client, date);
                    
                    JpaUtil.ouvrirTransaction();
                    c.setEmployeAssocie(employe);
                    c.setMediumAssocie(MA);
                    consultationDAO.create(c);
                    employe.setNbConsultations(employe.getNbConsultations()+1);
                    employe.setOnline(false);
                    employeDAO.maj(employe);
                    JpaUtil.validerTransaction();                     
                    Message.envoyerNotification(employe.getTelephonePro(), "Bonjour "+ employe.getPrenom()+".Consultation requise pour "+appelation+" "+client.getPrenom()+" "+client.getNom()+". Medium à incarner :"+ MA.getDenomination());
              
                }else{
                    c = null;
                }                
            }else{
                c = null;
            }
            if(DEBUG){System.out.println("Succès de demanderConsultation");}
        }catch(Exception ex){
            JpaUtil.annulerTransaction();
            ex.printStackTrace();
            if(DEBUG){System.out.println("Echec de demanderConsultation");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }

        return c;
    }
    
    
    //consulterHistorique : permet à un client de consulter l'historique de ses consultations à un instant, de la plus récente à la plus ancienne
    // paramètres d'entrées : le client actuel
    // paramètres de sortie : un String contenant l'historique du client, pour chaque consultation, on donne la date et le médium associé
    public String consulterHistorique(Client clientActuel){

        ConsultationDAO consultationDAO = new ConsultationDAO();
        Long id = clientActuel.getId();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            List <Consultation> liste = consultationDAO.listeConsultationsClient(id);
            result = "";
            Consultation c;
            for(int i=0; i<liste.size();i++){
                c = liste.get(i);
                result += "- consultation du "+c.getDate()+" avec "+c.getMediumAssocie().getDenomination()+". \n";
            }
            if(DEBUG){System.out.println("Succès de consulterHistorique");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de consulterHistorique");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
 
     
    // AUTRES SERVICES
    
    
    //initialiserMediums :  permet de créer dans la base de données 20 M2DIUMS
    // paramètres d'entrées : aucun
    // paramètres de sortie : aucun
    public void initialiserMediums(){
        
        Cartomancien M1 = new Cartomancien("Cartoman", "H", "bonjour, je suis Cartoman");
        Cartomancien M2 = new Cartomancien("Cartowoman", "F", "bonjour, je suis Cartowoman");
        Cartomancien M3 = new Cartomancien("Cartobro", "H", "bonjour, je suis Cartobro");
        Cartomancien M4 = new Cartomancien("Cartosis", "F", "bonjour, je suis Cartosis");
        Cartomancien M5 = new Cartomancien("Cartodad", "H", "bonjour, je suis Cartodad");
        Cartomancien M6 = new Cartomancien("Cartomom", "F", "bonjour, je suis Cartomom");
        Cartomancien M7 = new Cartomancien("Cartogirl", "F", "bonjour, je suis Cartogirl");
        Astrologue M8 = new Astrologue("bac+5", "promo 64", "Astroman", "H","bonjour, je suis Astroman");
        Astrologue M9 = new Astrologue("bac+5", "promo 64", "Astrowoman", "F","bonjour, je suis Astrowoman");
        Astrologue M10 = new Astrologue("bac+5", "promo 64", "Astrobro", "H","bonjour, je suis Astrobro");
        Astrologue M11 = new Astrologue("bac+5", "promo 64", "Astrosis", "F","bonjour, je suis Astrosis");
        Astrologue M12 = new Astrologue("bac+5", "promo 64", "Astrodad", "H","bonjour, je suis Astrodad");
        Astrologue M13 = new Astrologue("bac+5", "promo 64", "Astromom", "F","bonjour, je suis Astromom");
        Astrologue M14 =  new Astrologue("bac+5", "promo 64", "Astrogirl", "F","bonjour, je suis Astrogirl");
        Spirite M15 = new Spirite("boule de cristal", "Spiriteman", "H","bonjour, je suis Spririteman");
        Spirite M16 = new Spirite("boule de cristal", "Spiritewoman", "F","bonjour, je suis Spriritewoman");
        Spirite M17 = new Spirite("boule de cristal", "Spiritedad", "H","bonjour, je suis Spiritedad");
        Spirite M18 = new Spirite("boule de cristal", "Spiritemom", "F","bonjour, je suis Spiritemom");
        Spirite M19 = new Spirite("boule de cristal", "Spiritebro", "H","bonjour, je suis Spiritebro");
        Spirite M20 = new Spirite("boule de cristal", "Spiritesis", "F","bonjour, je suis Spiritesis");
        Spirite M21 = new Spirite("boule de cristal", "Spiritegirl", "F","bonjour, je suis Spiritegirl");
   
        MediumDAO mediumDAO = new MediumDAO();
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            mediumDAO.create(M1);
            mediumDAO.create(M2);
            mediumDAO.create(M3);
            mediumDAO.create(M4);
            mediumDAO.create(M5);
            mediumDAO.create(M6);
            mediumDAO.create(M7);
            mediumDAO.create(M8);
            mediumDAO.create(M9);
            mediumDAO.create(M10);
            mediumDAO.create(M11);
            mediumDAO.create(M12);
            mediumDAO.create(M13);
            mediumDAO.create(M14);
            mediumDAO.create(M15);
            mediumDAO.create(M16);
            mediumDAO.create(M17);
            mediumDAO.create(M18);
            mediumDAO.create(M19);
            mediumDAO.create(M20);
            mediumDAO.create(M21);
            JpaUtil.validerTransaction();
            if(DEBUG){System.out.println("Succès d'initialiserMediums");}
        }catch(Exception ex){
            JpaUtil.annulerTransaction();
            if(DEBUG){System.out.println("Echec d'initialiserMediums");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
    }  
    
    
    //trouverMediumsParId :  permet à partir d'un identifiant de trouver le médium associé
    // paramètres d'entrées : long Id
    // paramètres de sortie : le médium associé, si aucun médium n'est associé à l'Id, renvoie null
    public Medium trouverMediumParId(Long idToFind){
        Medium C1 = null;
        MediumDAO mediumDAO = new MediumDAO();
        try{
            C1 = mediumDAO.find(idToFind);
            if(DEBUG){System.out.println("Succès de trouverMediumParId");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de trouverMediumParId");}
        }finally{
        }
        return C1;
    }
    
    
    //listerTousMediums :  permet de lister tous les médiums de la base de données, triés par id croissants
    // paramètres d'entrées : aucun
    // paramètres de sortie : un String contenant la liste des médiums
    public String listerTousMediums(){

        MediumDAO mediumDAO = new MediumDAO();
        String result = null;
        try{
            JpaUtil.creerContextePersistance();
            result = "";
            Medium m =null;
            List <Medium> liste = mediumDAO.liste();
            for(int i=0; i<liste.size(); i++){
                m = liste.get(i);
                result += "- "+m.getId()+", "+m.getDenomination()+", "+m.getPresentation()+"\n";
            }
            if(DEBUG){System.out.println("Succès de listerTousMediums");}
        }catch(Exception ex){
            if(DEBUG){System.out.println("Echec de listerTousMediums");}
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }


}
