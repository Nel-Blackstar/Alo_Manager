package com.live.rh.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Partenaire;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Offre extends LiveEntity {
    private Long quantite;
    private float montant;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_fourniture")
    private Fournitures fournitures;

    @ManyToOne
    @JoinColumn(name = "id_partenaire")
    private Partenaire partenaire;

    public Offre() {
    }

    public Offre(Long quantite, float montant, Date date) {
        this.quantite = quantite;
        this.montant = montant;
        this.date = date;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Fournitures getFournitures() {
        return fournitures;
    }

    public void setFournitures(Fournitures fournitures) {
        this.fournitures = fournitures;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }
}
