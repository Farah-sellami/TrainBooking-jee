package services;

import dao.impliment.GareDaoImpl;
import model.Gare;

import java.util.List;

public class GareService {

    private final GareDaoImpl gareDao = new GareDaoImpl();

    public void ajouterGare(Gare gare) {
        gareDao.ajouter(gare);
    }

    public void modifierGare(Gare gare) {
        gareDao.modifier(gare);
    }

    public void supprimerGare(Long id) {
        gareDao.supprimer(id);
    }

    public Gare trouverGareParId(Long id) {
        return gareDao.trouverParId(id);
    }

    public List<Gare> listerToutesLesGares() {
        return gareDao.listerToutes();
    }
}
