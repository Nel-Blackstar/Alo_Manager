package com.live.rh.entities;

import com.live.common.entities.LiveEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Sortie extends LiveEntity {
	@OneToOne
	private Offre offre;

	@NotNull
    private String type;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	@NotNull
    private  String description;
	@NotNull
    private  String auteur;
	@NotNull
	@DecimalMin("0")
    private Long quantite;

    public Sortie() {
    }

    public Sortie(String type, String description, Long quantite, Date date,String auteur) {
        this.type = type;
        this.description = description;
        this.quantite = quantite;
        this.date=date;
        this.auteur=auteur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
    
	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	
}
