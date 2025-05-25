package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demande_annulation")
public class DemandeAnnulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToOne
    private Billet billet;

    private String motif;

    private LocalDateTime dateDemande;

    private String statut; // EN_ATTENTE, ACCEPTEE, REFUSEE

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Billet getBillet() {
		return billet;
	}

	public void setBillet(Billet billet) {
		this.billet = billet;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public LocalDateTime getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(LocalDateTime dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "DemandeAnnulation [id=" + id + ", utilisateur=" + utilisateur + ", billet=" + billet + ", motif="
				+ motif + ", dateDemande=" + dateDemande + ", statut=" + statut + "]";
	}

   
}
