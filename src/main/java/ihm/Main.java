package ihm;
//agathe autier joseph rouquette
import donnees.Employe;
import metier.Service;
import dao.JpaUtil;
import donnees.Client;
import donnees.Consultation;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import util.Saisie;

public class Main {
    static Service se = new Service();
    
    public static void main(String[] args) throws IOException, ParseException {
        
        JpaUtil.creerFabriquePersistance();
        InitialiserEmployesMediums();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n>Bonjour, bienvenue sur PredictIF.");
        String scenario = "(1) tests fonctionnels (2) interface en ligne de commande";
        scenario = Saisie.lireChaine(scenario);
        if(scenario.equals("1")){
            testsFonctionnels();
        }else if(scenario.equals("2")){
            LigneDeCommande();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n>Merci de votre utilisation, à bientôt sur PredictIF");
        JpaUtil.fermerFabriquePersistance();
    }
        
        
    public static void LigneDeCommande() throws IOException{
        String invite = "initialisation";

        while(!invite.equals("3")){
            invite = ">Êtes vous un employé (1) ou un client (2) ? Appuyez sur (3) pour quitter l'application";
            invite = Saisie.lireChaine(invite);

            if (invite.equals("1")){
                Employe employeActuel = IHMAuthentifierEmploye();
                if(employeActuel == null){
                    System.out.println(">Désolée, nous n'avons pas pu vous authentifier.");
                }else{
                    System.out.println(">Bienvenue sur le menu employé.");
                    System.out.println(">Bonjour " +employeActuel.getPrenom()+", vous souhaitez :");
                    String actionE = ">(1) consulter les infos du client (2) consulter votre historique (3) consulter les statistiques (4) lancer la consultation, (5) quitter ";
                    while(!actionE.equals("5")){
                        actionE = ">(1) consulter les infos du client (2) consulter votre historique (3) consulter les statistiques (4) lancer la consultation, (5) quitter ";
                        actionE = Saisie.lireChaine(actionE);
                        if(actionE.equals("1")){
                            IHMobtenirInfosClient(employeActuel.getId());
                        }else if (actionE.equals("2")){
                            IHMconsulterHistoriqueE(employeActuel);
                        }else if (actionE.equals("3")){
                            IHMobtenirStatistiques();
                        }else if (actionE.equals("4")){
                            IHMlancerConsultation(employeActuel);
                        }else if (!actionE.equals("5")){
                            System.out.println(">Vous n'avez pas rentré de valeur acceptée.");
                        }                    
                    }
                }

            }else if (invite.equals("2")){
                Client clientActuel = null;
                boolean valeurAcceptee = false;
                System.out.println(">Bienvenue sur le menu client.");
                String menuclient = ">Souhaitez vous vous (1) inscrire, (2) vous authentifier (3) quitter.";
                menuclient = Saisie.lireChaine(menuclient);

                while (!menuclient.equals("3")) {

                    while (!valeurAcceptee && !menuclient.equals("3")){
                        if (menuclient.equals("1")) {
                            IHMInscriptionClient();
                        } else if (menuclient.equals("2")) {
                            clientActuel = IHMAuthentifierClient();
                            if(clientActuel != null){ 
                                valeurAcceptee = true;
                            }else{
                                System.out.println(">Désolée, nous ne pouvons vous connecter à votre compte.");
                            }
                        } else if (!menuclient.equals("3")){
                            System.out.println(">Vous n'avez pas rentré de valeur acceptée.");
                        }
                        if(!valeurAcceptee && !menuclient.equals("3") ){
                            menuclient = ">Souhaitez vous vous (1) inscrire, (2) vous authentifier (3) quitter.";
                            menuclient = Saisie.lireChaine(menuclient);
                        }
                    }
                    valeurAcceptee = false;

                    if(clientActuel == null && !menuclient.equals("3")){
                        if(menuclient.equals("2")){
                            System.out.println(">Désolée, nous ne pouvons vous connecter à votre compte.");
                        }
                    }else if ( !menuclient.equals("3")){
                        System.out.println(">Bonjour " + clientActuel.getPrenom());
                        String actionC = "initialisation";
                        while(!actionC.equals("4")){
                            actionC = ">(1) consulter votre profil astral (2) demander une consultation (3) consulter votre historique (4) quitter ";
                            actionC = Saisie.lireChaine(actionC);
                            if(actionC.equals("1")){
                                System.out.println(">Voici, votre profil astral :");
                                System.out.println(clientActuel.getProfilAstral());
                            }else if (actionC.equals("2")){
                                IHMdemanderConsultation(clientActuel);
                            }else if (actionC.equals("3")){
                                IHMconsulterHistoriqueC(clientActuel);
                            }else if (!actionC.equals("4")){
                                System.out.println(">Vous n'avez pas rentré de valeur acceptée.");
                            }                    
                        }
                    menuclient = ">Souhaitez vous vous (1) inscrire, (2) vous authentifier (3) quitter.";
                    menuclient = Saisie.lireChaine(menuclient);
                    }
                }
            }else if(!invite.equals("3")){
                System.out.println(">Vous n'avez pas rentré de valeur acceptée.");
            }
        }   
    }
    
    public static void testsFonctionnels() throws IOException{
        String test = ">(1) inscription client dysfonctionnelle \n>(2) authentification client dysfonctionnelle\n>(3) authentification employe dysfonctionnelle\n>(4) test du service permettant de trouver la consultation courante\n>(5) déroulement normal de bout en bout\n>(6) plus d'employés disponibles et statistiques \n>(7) quitter.";
        test = Saisie.lireChaine(test);
        if(test.equals("1")){
            testFonctionnel1();
        }else if (test.equals("2")){
            testFonctionnel2();
        }else if (test.equals("3")){
            testFonctionnel3();
        }else if (test.equals("4")){
            testFonctionnel4();
        }else if (test.equals("5")){
            testFonctionnel5();
        }else if (test.equals("6")){
            testFonctionnel6();
        }else{
            
        }
        
    }
    
    public static void testFonctionnel1() throws IOException{
        //on essaye d'inscrire 2 clients avec la même adresse mail pour constater que cela ne marche pas et que le deuxième a un mail d'erreur
        System.out.println(">test fonctionnel 1 : inscription client dysfonctionnelle\n");
        Date date1 = new Date(2002-10-15);
        Date date2 = new Date(2002-11-17);
        Client C = new Client("agathe","autier",date1,"3 clos", "F","mdp","agathe@gmail.com","06 16 20 10 47");
        Client D = new Client("marie","mendribil",date2,"4 clos", "F","dmp","agathe@gmail.com","06 17 21 11 48");
        System.out.println(">Inscription du client");
        se.inscriptionClient(C);
        System.out.println(">Inscription du client");
        se.inscriptionClient(D);
    }
    
    public static void testFonctionnel2() throws IOException{
        //on essaye d'authentifier un client avec :
        // 1 un mail et un mot de passe qui n'existent pas dans la base de données 
        // 2 un mail qui existe avec un mot de passe qui n'existe pas
        // 3 un mail qui n'existe pas et un mot de passe qui existe dans la base de données
        // 4 un mail et un mot de passe qui existent mais qui se sont pas associés au même client dans la base de données 
        
        System.out.println(">test fonctionnel 2 : authentification client dysfonctionnelle\n");
        Date date1 = new Date(2002-10-15);
        Client C = new Client("agathe","autier",date1,"3 clos", "F","mdp","agathe@gmail.com","06 16 20 10 47");
        Client D = new Client("victor","autier",date1,"4 clos", "H","password","victor@gmail.com","07 17 21 11 48");
        System.out.println(">Inscription du client");
        se.inscriptionClient(C);
        System.out.println(">Inscription du client");
        se.inscriptionClient(D);
        
        if(se.authentifierClient("marie@gmail.com", "motdep") != null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 1 n'a pas fonctionné.");
        }
        
        if(se.authentifierClient("agathe@gmail.com","motdep")!= null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 2 n'a pas fonctionné.");
        }
        
        if(se.authentifierClient("marie@gmail.com","mdp")!= null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 3 n'a pas fonctionné.");
        }
        
        if(se.authentifierClient("victor@gmail.com","mdp")!= null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 4 n'a pas fonctionné.");
        }
    }
    
    public static void testFonctionnel3() throws IOException{
        //on essaye d'authentifier un client avec :
        // 1 un mail et un mot de passe qui n'existent pas dans la base de données 
        // 2 un mail qui existe avec un mot de passe qui n'existe pas
        // 3 un mail qui n'existe pas et un mot de passe qui existe dans la base de données
        // 4 un mail et un mot de passe qui existent mais qui se sont pas associés au même employé dans la base de données 
        
        System.out.println(">test fonctionnel 3 : authentification employé dysfonctionnelle\n");
        
        if(se.authentifierEmploye("marie@gmail.com", "motdep") != null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 1 n'a pas fonctionné.");
        }
        
        if(se.authentifierEmploye("amine.elilj@gmail.com","motdep")!= null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 2 n'a pas fonctionné.");
        }
        
        if(se.authentifierEmploye("marie@gmail.com","mdp13")!= null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 3 n'a pas fonctionné.");
        }
        
        if(se.authentifierEmploye("amine.elilj@gmail.com","mdp13")!= null){
            System.out.println(">Vous êtes bien authentifié !\n");
        }else{
            System.out.println(">L'authentification 4 n'a pas fonctionné.");
        }
    }
    
    public static void testFonctionnel4() throws IOException{
        //on teste la recherche de consultation courante 
        // 1 pour un employé qui n'en a aucune
        // 2 pour un employé qui en a une en cours
        // 3 pour un employé qui les a terminées
        
        System.out.println("> test fonctionnel 4 : test de la fonctionnalité trouver la consultation courante");
        Employe e;
        Consultation c;
        Date date1 = new Date(2002-10-15);
        Client client;
        
        System.out.println(">Inscription du client");
        client = new Client("agathe","autier",date1,"3 clos", "F","mdp","agathe@gmail.com","06 16 20 10 47");
        se.inscriptionClient(client);     
        
        e = se.trouverEmployeParId(14L);
        e = se.authentifierEmploye(e.getMail(), e.getMotDePasse());
        System.out.println(">Bonjour "+e.getPrenom()+":");
        c = se.trouverConsultationCourante(e);       
        if(c != null){ System.out.println(">Voici votre consultation courante :"+c);
        }else{System.out.println(">Vous n'avez aucune consultation en cours.");}
        
        se.demanderConsultation(client, "17");
        
        c = se.trouverConsultationCourante(e);
        System.out.println(">Bonjour "+e.getPrenom()+"");
        if(c != null){System.out.println(">Voici votre consultation courante :\n"+c);
        }else{System.out.println(">Vous n'avez aucune consultation en cours.");}
        
        se.commenterConsultation("vraiment sympa",c);
        System.out.println(">Fin de la consultation");
        c = se.trouverConsultationCourante(e);
        System.out.println(">Bonjour "+e.getPrenom()+"");
        if(c != null){System.out.println(">Voici votre consultation courante :"+c);
        }else{System.out.println(">Vous n'avez aucune consultation en cours.");}
        

    }
    
    public static void testFonctionnel5() throws IOException{
        //on teste un déroulement de bout en bout d'une consultation
        
        System.out.println(">test fonctionnel 5 : déroulement normal de bout en bout");
        
        Date date1 = new Date(2002-10-15);
        Client C = new Client("agathe","autier",date1,"3 clos", "F","mdp","agathe@gmail.com","06 16 20 10 47");
        se.inscriptionClient(C);
        Client Cconnect = se.authentifierClient(C.getMail(), C.getMotDePasse());
        System.out.println(">Bonjour "+Cconnect.getPrenom()+" vous êtes bien authentifié.");
        System.out.println(">Voici votre profil astral");
        System.out.println(Cconnect.getProfilAstral());
        System.out.println(">Voici votre historique");
        System.out.println(se.consulterHistorique(Cconnect));
        System.out.println(">Vous avez demandé une consultation");
        se.demanderConsultation(Cconnect, "17");
        System.out.println(">Voici votre historique");
        System.out.println(se.consulterHistorique(Cconnect));
        System.out.println(">Vous êtes déconnecté");
        
        Employe e = se.authentifierEmploye("ziqing.mitoget@free.fr","mdp14");
        System.out.println(">Bonjour "+e.getPrenom()+" vous êtes bien authentifié");
        System.out.println(">Voici votre historique");
        System.out.println(se.consulterHistoriqueE(e));
        System.out.println(">Voici les informations de votre client :");
        Cconnect = se.trouverClientEnCours(e.getId());
        if(Cconnect != null){
            System.out.println(Cconnect.getPrenom()+" "+Cconnect.getNom()+", "+Cconnect.getProfilAstral()+" \nHistorique du client :\n"+se.consulterHistoriqueCpourE(Cconnect));
        }else{
            System.out.println(">Vous n'avez pas de consultation en cours.");
        }
        System.out.println(">Vous avez lancer la consultation en cours");
        Consultation consul = se.trouverConsultationCourante(e);
        System.out.println(">Voici votre prédiction automatique");
        System.out.println(se.obtenirPrediction("4", "2", "6", Cconnect));
        System.out.println(">Vous avez terminé la consultation en cours.");
        se.commenterConsultation("cool",consul);
        System.out.println(">Vous avez commenté la consultation en cours.");
        System.out.println(">Voici votre historique");
        System.out.println(se.consulterHistoriqueE(e));
    }
    
    public static void testFonctionnel6() throws IOException{
        //on teste le refus de la demande de consultation quand aucun employé n'est disponible pour incarner le médium
        //on enchaîne ensuite les consultations pour un employé
        //puis on vérifie que les statistiques associées sont cohérentes
        
        System.out.println("> test fonctionnel 6 : consultation refusée car aucun employé du genre adéquat disponible, \nenchainement de plusieurs consultations pour un même employé et statistiques");
        
        Consultation c;
        Consultation cCourante;
        Employe e;
        Date date1 = new Date(2002-10-15);
        Client client = new Client("agathe","autier",date1,"3 clos", "F","mdp","agathe@gmail.com","06 16 20 10 47");
        se.inscriptionClient(client);
        client = se.authentifierClient("agathe@gmail.com", "mdp");
        if(client != null){ System.out.println(">Vous êtes bien authentifié.");}
        
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "17");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "17");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "29");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "17");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "17");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        String pause = ">>pause pour aller voir la base de données";
        pause = Saisie.lireChaine(pause);
        
        e = se.trouverEmployeParId(7L);
        cCourante = se.trouverConsultationCourante(e);
        se.commenterConsultation("cool",cCourante);
        
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "26");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        cCourante = se.trouverConsultationCourante(e);
        se.commenterConsultation("cool2",cCourante);
        
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "26");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        cCourante = se.trouverConsultationCourante(e);
        se.commenterConsultation("cool3",cCourante);
        
        System.out.println(">Vous avez demandé une nouvelle consultation.");
        c = se.demanderConsultation(client, "29");
        if(c==null){System.out.println(">Votre médium n'est pas disponible");}
        cCourante = se.trouverConsultationCourante(e);
        se.commenterConsultation("cool4",cCourante);
        
        System.out.println(">Voici les statistiques :");
        System.out.println(se.obtenirStat1());            
        System.out.println(se.obtenirStat2());
        System.out.println(se.obtenirStat3());        
        
    }
    
    public static void InitialiserEmployesMediums(){
        se.initialiserEmployes();  
        se.initialiserMediums(); 
    }

    public static void IHMInscriptionClient() throws IOException{
        
        System.out.println(">Inscription");
        String prenom = ">Veuillez rentrez votre prénom";
        prenom = Saisie.lireChaine(prenom);
        String nom = ">Veuillez rentrez votre nom";
        nom = Saisie.lireChaine(nom);
        Scanner scanner = new Scanner(System.in);
        System.out.println(">Entrez une date au format yyyy-MM-dd : ");
        String input = scanner.nextLine();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(input);
            System.out.println(">La date entrée est : " + date);
        } catch (ParseException e) {
            System.out.println(">Format de date incorrect !");
        }
                
        String adresse = ">Veuillez rentrez votre adresse";
        adresse = Saisie.lireChaine(adresse);
        String genre = ">Veuillez rentrez votre genre (H ou F)";
        genre = Saisie.lireChaine(genre);
        String mdp = ">Veuillez rentrez votre mot de passe";
        mdp = Saisie.lireChaine(mdp);
        String mail = ">Veuillez rentrez votre mail";
        mail = Saisie.lireChaine(mail);
        String tel = ">Veuillez rentrez votre téléphone";
        tel = Saisie.lireChaine(tel);
                        
        Client C1 = new Client(prenom,nom,date,adresse, genre,mdp,mail,tel);
        se.inscriptionClient(C1);
    }
    
    
    
    public static Client IHMAuthentifierClient(){
        
        System.out.println(">Authentification");
        String mail = ">Veuillez rentrez votre mail";
        mail = Saisie.lireChaine(mail);
        String mdp = ">Veuillez rentrez votre mot de passe";
        mdp = Saisie.lireChaine(mdp);
        
        Client client = se.authentifierClient(mail,mdp);
        return client;
    }
    
    public static void IHMdemanderConsultation(Client clientActuel){
        System.out.println(">Demande de consultation");
        System.out.println(se.listerTousMediums());
        String id = ">Veuillez choisir l'id du médium qui vous correpond le plus";
        id = Saisie.lireChaine(id);
        Consultation cA = se.demanderConsultation(clientActuel, id);
        if(cA != null){
            System.out.println(">Votre medium est disponible, attendez qu'il vous contacte");
        }else{
            System.out.println(">Votre medium n'est pas disponible.");
        }
    } 
    
    public static void IHMconsulterHistoriqueC(Client clientActuel){
        System.out.println(se.consulterHistorique(clientActuel));
    }
   
    public static void IHMconsulterHistoriqueE(Employe employe){
        System.out.println(">Voici votre historique");
        System.out.println(se.consulterHistoriqueE(employe));
    }
    
    public static Employe IHMAuthentifierEmploye(){
        System.out.println(">Authentification");
        String mail = ">Veuillez rentrez votre mail";
        mail = Saisie.lireChaine(mail);
        String mdp = ">Veuillez rentrez votre mot de passe";
        mdp = Saisie.lireChaine(mdp);
        Employe employe = se.authentifierEmploye(mail,mdp);
        return employe;
    }    
    
    public static void IHMobtenirInfosClient(Long idEmploye){
        Client client = se.trouverClientEnCours(idEmploye);
        if(client!=null){
           System.out.println(">Voici les informations du client associé à la consultation en cours :");
           System.out.println(client.getProfilAstral());  
           System.out.println(">Historique du client :");
           System.out.println(se.consulterHistoriqueCpourE(client));           
        }else{
            System.out.println(">Vous n'avez aucune consultation en cours.");
        }

    }
    
    public static void IHMlancerConsultation(Employe employe) throws IOException{
        System.out.println(">Lancement de la consultation");
        Consultation consul = se.trouverConsultationCourante(employe);
        if(consul==null){
            System.out.println("Vous ne pouvez pas lancer de consultation\n");
        }else{
            String action = ">Vous souhaitez (1)obtenir une prédiction automatique (2) terminer la consultation ";
            while(!action.equals("2")){
                action = ">Vous souhaitez (1)obtenir une prédiction automatique (2) terminer la consultation ";
                action = Saisie.lireChaine(action);
                Client client = consul.getClientAssocie();

                if(action.equals("1")){
                    String nA = ">Veuillez rentrez le niveau d'Amour, entre 1 (mauvais) et 4 (excellent)";
                    nA = Saisie.lireChaine(nA);
                    String nS = ">Veuillez rentrez le niveau de santé, entre 1 (mauvais) et 4 (excellent)";
                    nS = Saisie.lireChaine(nS);
                    String nT = ">Veuillez rentrez le niveau de travail, entre 1 (mauvais) et 4 (excellent)";
                    nT = Saisie.lireChaine(nT);
                   System.out.println(se.obtenirPrediction(nA, nS, nT, client));
                }else if (!action.equals("2")){
                    System.out.println(">Vous n'avez pas rentré de valeur acceptée.");
                }                    
            }

            String com = ">Commentez la consultation qui vient de se terminer";
            com = Saisie.lireChaine(com); 
            se.commenterConsultation(com,consul);
        }
    }

    public static void IHMobtenirStatistiques(){
        String stat = ">Souhaitez vous (1) le top 5 des mediums (2) la répartition des consultations par médium (3) la répartition des consultations par employé";
        stat = Saisie.lireChaine(stat);
        
        if(stat.equals("1")){
            System.out.println(se.obtenirStat1());
        }else if (stat.equals("2")){
            System.out.println(se.obtenirStat2());
        } else if (stat.equals("3")){
            System.out.println(se.obtenirStat3());
        }else {
            System.out.println("Vous n'avez pas rentré une valeur acceptée.");
        }
    }

}
