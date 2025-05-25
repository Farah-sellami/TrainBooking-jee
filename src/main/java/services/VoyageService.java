package services;

import dao.impliment.VoyageDaoImpl;
import dao.interfaces.IVoyageDao;
import model.Trajet;
import model.Voyage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class VoyageService {

    private final IVoyageDao voyageDao;

    public VoyageService() {
        this.voyageDao = new VoyageDaoImpl();
    }

    public void ajouterVoyage(Voyage voyage) {
        voyageDao.ajouter(voyage);
    }

    public void modifierVoyage(Voyage voyage) {
        voyageDao.modifier(voyage);
    }

    public void supprimerVoyage(Long id) {
        voyageDao.supprimer(id);
    }

    public Voyage trouverVoyageParId(Long id) {
        return voyageDao.trouverParId(id);
    }

    public List<Voyage> listerTousVoyages() {
        return voyageDao.listerTous();
    }

    public List<Voyage> listerVoyagesDisponibles() {
        return voyageDao.listerDisponibles();
    }

    public List<Voyage> rechercherVoyagesParGareEtDate(String villeDepart, String villeArrivee, LocalDate dateDepart) {
        return voyageDao.rechercherParGareEtDate(villeDepart, villeArrivee, dateDepart);
    }

    public List<Voyage> rechercherVoyagesParTrajetEtDate(Long trajetId, String date) {
        return voyageDao.rechercherParTrajetEtDate(trajetId, date);
    }
    
    public List<Voyage> findByTrajet(Trajet trajet) {
        return voyageDao.findByTrajet(trajet);
    }

}
