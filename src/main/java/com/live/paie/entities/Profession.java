package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Profession extends LiveEntity {
    String nom;
    @OneToMany(targetEntity = Contrat.class)
    private List<Contrat> contrats;
    @ManyToOne(optional = true, targetEntity = CIMR.class)
    private CIMR cimr;

    public Profession() {
    }

    public Profession(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
