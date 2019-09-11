package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Contrat extends LiveEntity {
	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEmbauche;
    private String frequencePaie;
    private  String basePaie;
    private String modeReglement;
    private float salaireBase;

    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    @ManyToOne(optional = true, targetEntity = TypeContrat.class)
    private TypeContrat typeContrat;

    @ManyToOne(optional = true, targetEntity = Profession.class)
    private Profession profession;

    public Contrat() {
    }

    public Contrat(LocalDate dateEmbauche, String frequencePaie, String basePaie, String modeReglement, float salaireBase) {
        this.dateEmbauche = dateEmbauche;
        this.frequencePaie = frequencePaie;
        this.basePaie = basePaie;
        this.modeReglement = modeReglement;
        this.salaireBase = salaireBase;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getFrequencePaie() {
        return frequencePaie;
    }

    public void setFrequencePaie(String frequencePaie) {
        this.frequencePaie = frequencePaie;
    }

    public String getBasePaie() {
        return basePaie;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public void setBasePaie(String basePaie) {
        this.basePaie = basePaie;
    }

    public String getModeReglement() {
        return modeReglement;
    }

    public void setModeReglement(String modeReglement) {
        this.modeReglement = modeReglement;
    }

    public float getSalaireBase() {
        return salaireBase;
    }

    public void setSalaireBase(float salaireBase) {
        this.salaireBase = salaireBase;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
