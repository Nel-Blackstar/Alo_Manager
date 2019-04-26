package com.live.common.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Detail extends LiveEntity {

    private String nom_entite;
    private Long id_entite;

    @ManyToOne(optional = true, targetEntity = Detail.class)
    public Detail detailParent;

    @OneToMany(mappedBy = "detailParent")
    public List<Detail> detailsFils = new ArrayList<>();

    private String nom;
    private String label;
    private String valeur;
    private String type;

    public Detail() {
    }

    public Detail(String nom_entite, Long id_entite, String nom, String label, String valeur, String type) {
        this.nom_entite = nom_entite;
        this.id_entite = id_entite;
        this.nom = nom;
        this.label = label;
        this.valeur = valeur;
        this.type = type;
    }

    public String getNom_entite() {
        return nom_entite;
    }

    public void setNom_entite(String nom_entite) {
        this.nom_entite = nom_entite;
    }

    public Long getId_entite() {
        return id_entite;
    }

    public void setId_entite(Long id_entite) {
        this.id_entite = id_entite;
    }

    public Detail getDetailParent() {
        return detailParent;
    }

    public void setDetailParent(Detail detailParent) {
        this.detailParent = detailParent;
    }

    public List<Detail> getDetailsFils() {
        return detailsFils;
    }

    public void setDetailsFils(List<Detail> detailsFils) {
        this.detailsFils = detailsFils;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
