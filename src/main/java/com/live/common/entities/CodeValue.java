package com.live.common.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CodeValue extends LiveEntity {

    @Column(nullable = false)
    private String valeur;

    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @OneToOne(targetEntity = Code.class)
    private Code code;

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CodeValue{" +
                "valeur='" + valeur + '\'' +
                ", description='" + description + '\'' +
                ", code=" + code +
                '}';
    }
}
