package dao.interfaces;

import model.DemandeAnnulation;
import java.util.List;

public interface IAnnulationDao {
    void ajouter(DemandeAnnulation demande);
    void modifier(DemandeAnnulation demande);
    void supprimer(Long id);
    DemandeAnnulation trouverParId(Long id);
    List<DemandeAnnulation> listerToutes();
    List<DemandeAnnulation> listerParUtilisateur(Long utilisateurId);
    List<DemandeAnnulation> listerEnAttente();
    void valider(Long demandeId , boolean accepter);
}
