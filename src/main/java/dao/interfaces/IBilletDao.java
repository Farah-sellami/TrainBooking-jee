package dao.interfaces;

import model.Billet;
import java.util.List;

public interface IBilletDao {
    void ajouter(Billet billet);
    void modifier(Billet billet);
    void supprimer(Long id);
    Billet trouverParId(Long id);
    List<Billet> listerParUtilisateur(Long utilisateurId);
    List<Billet> listerTous();
    List<Billet> findByUtilisateurId(Long utilisateurId);
}
