package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;
import com.live.rh.entities.Apprenant;
import com.live.rh.entities.Details;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Inscription extends LiveEntity {
	@NotNull
    private float paiement;
    @Temporal(TemporalType.DATE)
    private Date date;

    private  String lieu_examen;
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
    @OneToOne
    private Dossier dossier;

    @OneToMany
    @JoinColumn(name = "id_inscription")
    private List<Details> details;

    private  Boolean epreuveTheorique;
    private  Boolean epreuvePratique;
    
    
	public float getPaiement() {
        return paiement;
    }

    public void setPaiement(float paiement) {
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

    public String getLieu_examen() {
        return lieu_examen;
    }

    public void setLieu_examen(String lieu_examen) {
        this.lieu_examen = lieu_examen;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public Boolean getEpreuveTheorique() {
        return epreuveTheorique;
    }

    public void setEpreuveTheorique(Boolean epreuveTheorique) {
        this.epreuveTheorique = epreuveTheorique;
    }

    public Boolean getEpreuvePratique() {
        return epreuvePratique;
    }

    public void setEpreuvePratique(Boolean epreuvePratique) {
        this.epreuvePratique = epreuvePratique;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}
