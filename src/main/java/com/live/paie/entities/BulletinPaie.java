package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BulletinPaie extends LiveEntity {
    private float nbreHeureTravaillees;
    private String description;
    private Long nbreJoursFeries;
    private Long nbreCongesPayes;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public BulletinPaie() {
    }

    public BulletinPaie(float nbreHeureTravaillees, String description, Long nbreJoursFeries, Long nbreCongesPayes,
			Personnel personnel) {
		super();
		this.nbreHeureTravaillees = nbreHeureTravaillees;
		this.description = description;
		this.nbreJoursFeries = nbreJoursFeries;
		this.nbreCongesPayes = nbreCongesPayes;
		this.personnel = personnel;
	}



	public float getNbreHeureTravaillees() {
        return nbreHeureTravaillees;
    }

    public void setNbreHeureTravaillees(float nbreHeureTravaillees) {
        this.nbreHeureTravaillees = nbreHeureTravaillees;
    }

    public Long getNbreJoursFeries() {
        return nbreJoursFeries;
    }

    public void setNbreJoursFeries(Long nbreJoursFeries) {
        this.nbreJoursFeries = nbreJoursFeries;
    }

    public Long getNbreCongesPayes() {
        return nbreCongesPayes;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNbreCongesPayes(Long nbreCongesPayes) {
        this.nbreCongesPayes = nbreCongesPayes;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
