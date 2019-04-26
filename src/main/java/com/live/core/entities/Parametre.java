package com.live.core.entities;

import com.live.common.entities.CodeValue;
import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Parametre extends LiveEntity {

    private String cle;
    private String valeur;
    private String label;

    @OneToOne(targetEntity = CodeValue.class)
    private CodeValue type_parametre ;// entier, double, chaine, date, ...

    public Parametre() {
    }

    public Parametre(String cle, String valeur, String label) {
        this.cle = cle;
        this.valeur = valeur;
        this.label = label;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CodeValue getType_parametre() {
        return type_parametre;
    }

    public void setType_parametre(CodeValue type_parametre) {
        this.type_parametre = type_parametre;
    }
}
