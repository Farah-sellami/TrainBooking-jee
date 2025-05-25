package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "voyage")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Trajet trajet;

    private LocalDateTime dateDepart;

    private LocalDateTime dateArrivee;

    private int nombrePlaces;

    private double prix;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Trajet getTrajet() {
		return trajet;
	}

	public void setTrajet(Trajet trajet) {
		this.trajet = trajet;
	}

	public LocalDateTime getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(LocalDateTime dateDepart) {
		this.dateDepart = dateDepart;
	}

	public LocalDateTime getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(LocalDateTime dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public int getNombrePlaces() {
		return nombrePlaces;
	}

	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Date getDateDepartAsDate() {
	    if (dateDepart == null) return null;
	    return java.util.Date.from(dateDepart.atZone(java.time.ZoneId.systemDefault()).toInstant());
	}

	public Date getDateArriveeAsDate() {
	    if (dateArrivee == null) return null;
	    return java.util.Date.from(dateArrivee.atZone(java.time.ZoneId.systemDefault()).toInstant());
	}

	@Override
	public String toString() {
		return "Voyage [id=" + id + ", trajet=" + trajet + ", dateDepart=" + dateDepart + ", dateArrivee=" + dateArrivee
				+ ", nombrePlaces=" + nombrePlaces + ", prix=" + prix + "]";
	}

    
}
