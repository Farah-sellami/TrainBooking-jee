package model;

import jakarta.persistence.*;

@Entity
@Table(name = "trajet")
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gare_depart_id")
    private Gare gareDepart;

    @ManyToOne
    @JoinColumn(name = "gare_arrivee_id")
    private Gare gareArrivee;

    private double distanceKm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gare getGareDepart() {
		return gareDepart;
	}

	public void setGareDepart(Gare gareDepart) {
		this.gareDepart = gareDepart;
	}

	public Gare getGareArrivee() {
		return gareArrivee;
	}

	public void setGareArrivee(Gare gareArrivee) {
		this.gareArrivee = gareArrivee;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}

	@Override
	public String toString() {
		return "Trajet [id=" + id + ", gareDepart=" + gareDepart + ", gareArrivee=" + gareArrivee + ", distanceKm="
				+ distanceKm + "]";
	}

    
}
