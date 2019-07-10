package com.live.moniteur.entities;

import com.live.common.entities.CodeValue;
import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Diplome extends LiveEntity {
	@OneToOne
	private CodeValue permis;

    @Temporal(TemporalType.DATE)
    private Date dateDelivrance;
    private boolean statut;

    public Diplome() {
    }

    public Diplome(Date dateDelivrance, boolean statut) {
        this.dateDelivrance = dateDelivrance;
        this.statut = statut;
    }
    
    public CodeValue getPermis() {
		return permis;
	}

	public void setPermis(CodeValue permi) {
		this.permis = permi;
	}

	public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
    
}
