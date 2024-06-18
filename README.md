# PredictIF

PREDICT’IF est une application qui permet à des clients de prendre des rendez-vous avec des médiums incarnés par des employés de l’entreprise. Cette application peut être exécutée par deux types d’utilisateurs : les employés et les clients.

## Fonctionnalités

### Du point de vue client

- **Création de compte** : Le client peut se créer un compte en renseignant :
  - Nom
  - Prénom
  - Mail (servant d’identifiant)
  - Numéro de téléphone
  - Date de naissance (format yyyy-mm-dd)
  - Mot de passe
  - Genre (seulement 'H' pour homme et 'F' pour femme)
  - Adresse postale
  - **Condition** : Aucun autre compte ne doit exister avec la même adresse mail.

- **Authentification** : 
  - Prérequis : Avoir déjà créé un compte.
  - Identifiants : Mail et mot de passe.

- **Accès aux fonctionnalités** :
  - **Profil astral** : Affichage du signe du zodiaque, de l’animal totem, de la couleur fétiche et du signe chinois.
  - **Historique des consultations** : Consultation des précédents rendez-vous.
  - **Demande de nouvelle consultation** :
    - Affichage de la liste des médiums avec leurs informations (présentation, genre, support, formation, promotion).
    - Entrée de l’ID du médium souhaité pour valider le rendez-vous.
    - **Conditions** :
      - Le choix de l’employé dépend du genre du médium demandé et du nombre de consultations déjà effectuées.
      - Si aucun employé n’est disponible pour incarner le médium, la demande est refusée.
  - **Déconnexion** : Le client peut quitter l’application à tout moment.

### Du point de vue Employé

- **Authentification** : 
  - Les employés sont déjà enregistrés dans la base de données.
  - Identifiants : Mail et mot de passe (même méthode que pour les clients).

- **Accès aux fonctionnalités** :
  - **Consultation des clients** : Voir tous les clients dans la base de données et obtenir le détail de leurs informations (profil astral, historique) en entrant leur ID.
  - **Statistiques** :
    - TOP 5 des médiums générant le plus de consultations.
    - Répartition des consultations par employé.
    - Répartition des consultations par médiums.
  - **Lancement de consultations** :
    - Conditions : La consultation doit lui être attribuée (choisie par un client) et être en cours (aucun commentaire encore ajouté).
    - **Prédictions automatiques** : Générer des prédictions pour le client dans les catégories amour, santé et travail (avec une note entre 1 et 4).
    - **Fin de consultation** : Ajout d’un commentaire.
    - **Historique** : Consultation de l’historique des consultations réalisées, y compris la consultation en cours.
  - **Déconnexion** : L’employé peut quitter l’application à tout moment.
