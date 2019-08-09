package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;
import com.live.rh.entities.Apprenant;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Inscription extends LiveEntity {
	@NotNull
    private String paiement;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    private SessionFormation formation;
    
    @OneToMany(targetEntity = Evaluation.class)
    private List<Evaluation> evaluations;
    
    @OneToMany(targetEntity = Suivre.class)
    private List<Suivre> suivres;
    
    @ManyToOne
    @JoinColumn(name = "id_apprenant")
    private Apprenant apprenant;
    @OneToOne
    private Diplome diplome;
    
    
	public String getPaiement() {
        return paiement;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }

    public Diplome getDiplome() {
		return diplome;
	}

	public void setDiplome(Diplome diplome) {
		this.diplome = diplome;
	}

	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SessionFormation getFormation() {
        return formation;
    }

    public void setFormation(SessionFormation formation) {
        this.formation = formation;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }
    
    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

	public List<Suivre> getSuivres() {
		return suivres;
	}

	public void setSuivres(List<Suivre> suivres) {
		this.suivres = suivres;
	}
    
    
}
