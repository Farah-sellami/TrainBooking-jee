package dao.interfaces;

import model.Trajet;
import java.util.List;

public interface ITrajetDao {
    void ajouter(Trajet trajet);
    void modifier(Trajet trajet);
    void supprimer(Long id);
    Trajet trouverParId(Long id);
    List<Trajet> listerTous();
    List<Trajet> rechercher(String villeDepart, String villeArrivee);
}
