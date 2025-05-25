package services;

import dao.impliment.PaiementDaoImpl;
import model.Paiement;

import java.util.List;

public class PaiementService {

    private final PaiementDaoImpl paiementDao = new PaiementDaoImpl();

    public void ajouterPaiement(Paiement paiement) {
        paiementDao.ajouter(paiement);
    }

    public Paiement trouverPaiementParId(Long id) {
        return paiementDao.trouverParId(id);
    }

    public List<Paiement> listerTousLesPaiements() {
        return paiementDao.listerTous();
    }
}
