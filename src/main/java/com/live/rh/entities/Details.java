package com.live.rh.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Partenaire;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Details extends LiveEntity {

    @Temporal(TemporalType.DATE)
    private Date date;

    private  String description;

    private double valeur;


    @ManyToOne
    @JoinColumn(name = "id_offre")
    private Offre offre;


    public Details() {
    }

    public Details(Date date, String description, double valeur, Offre offre) {
        this.date = date;
        this.description = description;
        this.valeur = valeur;
        this.offre = offre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
}
