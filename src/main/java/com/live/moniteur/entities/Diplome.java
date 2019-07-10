package com.live.moniteur.entities;

import com.live.common.entities.CodeValue;
import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Diplome extends LiveEntity {
	@OneToOne
	private Inscription inscrit;
    @Temporal(TemporalType.DATE)
    private Date dateDelivrance;
    private boolean statut;
    
    @OneToOne
    private CodeValue categoriePermis;


    public Diplome() {
    }

    public Diplome(Date dateDelivrance, boolean statut) {
        this.dateDelivrance = dateDelivrance;
        this.statut = statut;
    }
    
    public CodeValue getCategoriePermis() {
		return categoriePermis;
	}

	public void setCategoriePermis(CodeValue categoriePermis) {
		this.categoriePermis = categoriePermis;
	}

	public Inscription getInscrit() {
		return inscrit;
	}

	public void setInscrit(Inscription inscrit) {
		this.inscrit = inscrit;
	}

	public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
    
}
