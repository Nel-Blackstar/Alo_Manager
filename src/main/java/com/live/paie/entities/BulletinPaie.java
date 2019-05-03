package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BulletinPaie extends LiveEntity {
    private float nbreHeureTravaillees;
    private float nbreHeureSup50;
    private float nbreHeureSup100;
    private float nbreHeureSup25;
    private Long nbreJoursFeries;
    private Long nbreCongesPayes;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public BulletinPaie() {
    }

    public BulletinPaie(float nbreHeureTravaillees, float nbreHeureSup50, float nbreHeureSup100, float nbreHeureSup25,
                        Long nbreJoursFeries, Long nbreCongesPayes) {
        this.nbreHeureTravaillees = nbreHeureTravaillees;
        this.nbreHeureSup50 = nbreHeureSup50;
        this.nbreHeureSup100 = nbreHeureSup100;
        this.nbreHeureSup25 = nbreHeureSup25;
        this.nbreJoursFeries = nbreJoursFeries;
        this.nbreCongesPayes = nbreCongesPayes;
    }

    public float getNbreHeureTravaillees() {
        return nbreHeureTravaillees;
    }

    public void setNbreHeureTravaillees(float nbreHeureTravaillees) {
        this.nbreHeureTravaillees = nbreHeureTravaillees;
    }

    public float getNbreHeureSup50() {
        return nbreHeureSup50;
    }

    public void setNbreHeureSup50(float nbreHeureSup50) {
        this.nbreHeureSup50 = nbreHeureSup50;
    }

    public float getNbreHeureSup100() {
        return nbreHeureSup100;
    }

    public void setNbreHeureSup100(float nbreHeureSup100) {
        this.nbreHeureSup100 = nbreHeureSup100;
    }

    public float getNbreHeureSup25() {
        return nbreHeureSup25;
    }

    public void setNbreHeureSup25(float nbreHeureSup25) {
        this.nbreHeureSup25 = nbreHeureSup25;
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

    public void setNbreCongesPayes(Long nbreCongesPayes) {
        this.nbreCongesPayes = nbreCongesPayes;
    }
}
