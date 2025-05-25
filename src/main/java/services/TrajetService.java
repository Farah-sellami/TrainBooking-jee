package services;

import dao.impliment.TrajetDaoImpl;
import model.Trajet;

import java.util.List;

public class TrajetService {

    private final TrajetDaoImpl trajetDao = new TrajetDaoImpl();

    public void ajouterTrajet(Trajet trajet) {
        trajetDao.ajouter(trajet);
    }

    public void modifierTrajet(Trajet trajet) {
        trajetDao.modifier(trajet);
    }

    public void supprimerTrajet(Long id) {
        trajetDao.supprimer(id);
    }

    public Trajet trouverTrajetParId(Long id) {
        return trajetDao.trouverParId(id);
    }

    public List<Trajet> listerTousLesTrajets() {
        return trajetDao.listerTous();
    }

    public List<Trajet> rechercherTrajets(String villeDepart, String villeArrivee) {
        return trajetDao.rechercher(villeDepart, villeArrivee);
    }
}
