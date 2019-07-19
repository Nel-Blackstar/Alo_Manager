package com.live.rh.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class RendezVous extends LiveEntity {
	@NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
	@NotNull
    private String lieu;
	@NotNull
    private String libelle;

    public RendezVous() {
    }

    public RendezVous(Date date, String lieu, String libelle) {
        this.date = date;
        this.lieu = lieu;
        this.libelle = libelle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
