package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CIMR extends LiveEntity {
    private String tauxCIMRSalarial;
    private String tauxCIMRPatronale;
    @OneToMany(targetEntity = Profession.class)
    private List<Profession> professions;

    public CIMR() {
    }

    public CIMR(String tauxCIMRSalarial, String tauxCIMRPatronale) {
        this.tauxCIMRSalarial = tauxCIMRSalarial;
        this.tauxCIMRPatronale = tauxCIMRPatronale;
    }

    public String getTauxCIMRSalarial() {
        return tauxCIMRSalarial;
    }

    public void setTauxCIMRSalarial(String tauxCIMRSalarial) {
        this.tauxCIMRSalarial = tauxCIMRSalarial;
    }

    public String getTauxCIMRPatronale() {
        return tauxCIMRPatronale;
    }

    public void setTauxCIMRPatronale(String tauxCIMRPatronale) {
        this.tauxCIMRPatronale = tauxCIMRPatronale;
    }
}
