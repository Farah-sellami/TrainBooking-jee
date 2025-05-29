package model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Billet billet;

    private double montant;

    private Date datePaiement;

    private String methodePaiement; // CB,, etc.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Billet getBillet() {
		return billet;
	}

	public void setBillet(Billet billet) {
		this.billet = billet;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date date) {
		this.datePaiement = date;
	}

	public String getMethodePaiement() {
		return methodePaiement;
	}

	public void setMethodePaiement(String methodePaiement) {
		this.methodePaiement = methodePaiement;
	}

	@Override
	public String toString() {
		return "Paiement [id=" + id + ", billet=" + billet + ", montant=" + montant + ", datePaiement=" + datePaiement
				+ ", methodePaiement=" + methodePaiement + "]";
	}

   
}
