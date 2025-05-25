package services;

import dao.impliment.UtilisateurDaoImpl;
import dao.interfaces.IUtilisateurDao;
import model.Utilisateur;

import java.util.List;

public class UtilisateurService {

    private final IUtilisateurDao utilisateurDao;

    public UtilisateurService() {
        this.utilisateurDao = new UtilisateurDaoImpl();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        // Ici tu peux ajouter validations, logs, etc.
        utilisateurDao.ajouter(utilisateur);
    }

    public void modifierUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.modifier(utilisateur);
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurDao.supprimer(id);
    }

    public Utilisateur trouverUtilisateurParId(Long id) {
        return utilisateurDao.trouverParId(id);
    }

    public Utilisateur trouverUtilisateurParEmail(String email) {
        return utilisateurDao.trouverParEmail(email);
    }

    public List<Utilisateur> listerTousUtilisateurs() {
        return utilisateurDao.listerTous();
    }

    public Utilisateur authentifierUtilisateur(String email, String motDePasse) {
        return utilisateurDao.authentifier(email, motDePasse);
    }

    public void bloquerCompte(Long id) {
        utilisateurDao.bloquerCompte(id);
    }

    public void debloquerCompte(Long id) {
        utilisateurDao.debloquerCompte(id);
    }

    public List<Utilisateur> listerUtilisateursParRole(String role) {
        return utilisateurDao.listerParRole(role);
    }

    public List<Utilisateur> listerUtilisateursBloques() {
        return utilisateurDao.listerBloques();
    }
}
