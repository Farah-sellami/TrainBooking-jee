package dao.interfaces;

import model.Paiement;
import java.util.List;

public interface IPaiementDao {
    void ajouter(Paiement paiement);
    Paiement trouverParId(Long id);
    List<Paiement> listerTous();
}
