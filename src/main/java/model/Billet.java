package model;

import jakarta.persistence.*;

@Entity
@Table(name = "billet")
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Voyage voyage;

    private String numeroBillet;

    private boolean estValide;

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

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	public String getNumeroBillet() {
		return numeroBillet;
	}

	public void setNumeroBillet(String numeroBillet) {
		this.numeroBillet = numeroBillet;
	}

	public boolean isEstValide() {
		return estValide;
	}

	public void setEstValide(boolean estValide) {
		this.estValide = estValide;
	}

	@Override
	public String toString() {
		return "Billet [id=" + id + ", utilisateur=" + utilisateur + ", voyage=" + voyage + ", numeroBillet="
				+ numeroBillet + ", estValide=" + estValide + "]";
	}

	public void setStatut(String string) {
		// TODO Auto-generated method stub
		
	}

   
}
