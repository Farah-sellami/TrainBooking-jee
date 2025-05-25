package dao.interfaces;

import model.Gare;
import java.util.List;

public interface IGareDao {
    void ajouter(Gare gare);
    void modifier(Gare gare);
    void supprimer(Long id);
    Gare trouverParId(Long id);
    List<Gare> listerToutes();
}
