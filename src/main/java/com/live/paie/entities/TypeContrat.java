package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class TypeContrat extends LiveEntity {
    private int numeroType;
    private String nomType;
    @OneToMany(targetEntity = Contrat.class)
    private List<Contrat> contrats;


    public TypeContrat() {
    }

    public TypeContrat(int numeroType, String nomType) {
        this.numeroType = numeroType;
        this.nomType = nomType;
    }

    public int getNumeroType() {
        return numeroType;
    }

    public void setNumeroType(int numeroType) {
        this.numeroType = numeroType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
}
