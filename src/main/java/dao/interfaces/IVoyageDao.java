package dao.interfaces;

import model.Trajet;
import model.Voyage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IVoyageDao {
    void ajouter(Voyage voyage);
    void modifier(Voyage voyage);
    void supprimer(Long id);
    Voyage trouverParId(Long id);
    List<Voyage> listerTous();
    List<Voyage> rechercherParTrajetEtDate(Long trajetId, String date);
    List<Voyage> listerDisponibles();
  //  List<Voyage> rechercherParGareEtDate(String villeDepart, String villeArrivee, LocalDateTime date);
	List<Voyage> rechercherParGareEtDate(String villeDepart, String villeArrivee, LocalDate dateDepart);
	List<Voyage> findByTrajet(Trajet trajet);

}
