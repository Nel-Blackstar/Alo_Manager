package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Evaluation extends LiveEntity {
    private String typeEvaluation;
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany
    private Set<Soumettre> soumettres;

    @ManyToOne(optional = true, targetEntity = Chapitre.class)
    private Chapitre chapitre;

    @ManyToOne(optional = true, targetEntity = Cours.class)
    private Cours cours;

    public Evaluation() {
    }

    public Evaluation(String typeEvaluation, Date date) {
        this.typeEvaluation = typeEvaluation;
        this.date = date;
    }

    public String getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(String typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Soumettre> getSoumettres() {
        return soumettres;
    }

    public void setSoumettres(Set<Soumettre> soumettres) {
        this.soumettres = soumettres;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }
}
