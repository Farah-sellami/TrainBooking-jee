package services;

import dao.impliment.BilletDaoImpl;
import model.Billet;

import java.util.List;

public class BilletService {

    private final BilletDaoImpl billetDao = new BilletDaoImpl();

    public void ajouterBillet(Billet billet) {
        billetDao.ajouter(billet);
    }

    public void modifierBillet(Billet billet) {
        billetDao.modifier(billet);
    }

    public void supprimerBillet(Long id) {
        billetDao.supprimer(id);
    }

    public Billet trouverBilletParId(Long id) {
        return billetDao.trouverParId(id);
    }

    public List<Billet> listerBilletsParUtilisateur(Long utilisateurId) {
        return billetDao.listerParUtilisateur(utilisateurId);
    }

    public List<Billet> listerTousLesBillets() {
        return billetDao.listerTous();
    }
    public List<Billet> trouverBilletsParUtilisateur(Long utilisateurId) {
        return billetDao.findByUtilisateurId(utilisateurId);
    }

}
