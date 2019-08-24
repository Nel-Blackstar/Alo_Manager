package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class TypeContrat extends LiveEntity {
    private int numeroType;
    private String nomType;

    private String libeller;
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

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public String getLibeller() {
        return libeller;
    }

    public void setLibeller(String libeller) {
        this.libeller = libeller;
    }
}
