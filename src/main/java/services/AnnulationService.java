package services;

import dao.impliment.AnnulationDaoImpl;
import model.DemandeAnnulation;

import java.util.List;

public class AnnulationService {

    private final AnnulationDaoImpl annulationDao = new AnnulationDaoImpl();

    public void ajouterDemande(DemandeAnnulation demande) {
        annulationDao.ajouter(demande);
    }

    public void modifierDemande(DemandeAnnulation demande) {
        annulationDao.modifier(demande);
    }

    public void supprimerDemande(Long id) {
        annulationDao.supprimer(id);
    }

    public DemandeAnnulation trouverDemandeParId(Long id) {
        return annulationDao.trouverParId(id);
    }

    public List<DemandeAnnulation> listerToutesDemandes() {
        return annulationDao.listerToutes();
    }

    public List<DemandeAnnulation> listerDemandesParUtilisateur(Long utilisateurId) {
        return annulationDao.listerParUtilisateur(utilisateurId);
    }

    public List<DemandeAnnulation> listerDemandesEnAttente() {
        return annulationDao.listerEnAttente();
    }

    public void validerDemande(Long demandeId, boolean accepter) {
        annulationDao.valider(demandeId, accepter);
    }
}
