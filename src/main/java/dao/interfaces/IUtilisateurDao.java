package dao.interfaces;

import model.Utilisateur;
import java.util.List;

public interface IUtilisateurDao {
    
    // CRUD de base
    void ajouter(Utilisateur utilisateur);
    void modifier(Utilisateur utilisateur);
    void supprimer(Long id);
    
    // Recherche
    Utilisateur trouverParId(Long id);
    Utilisateur trouverParEmail(String email);
    List<Utilisateur> listerTous();

    // Authentification
    Utilisateur authentifier(String email, String motDePasse);

    // Blocage / déblocage (admin)
    void bloquerCompte(Long id);
    void debloquerCompte(Long id);

    // Recherche filtrée
    List<Utilisateur> listerParRole(String role); // Ex: "admin", "client"
    List<Utilisateur> listerBloques();
}
