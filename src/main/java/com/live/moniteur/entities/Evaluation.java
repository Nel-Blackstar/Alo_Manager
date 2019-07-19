package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Evaluation extends LiveEntity {
	@NotNull
    private String typeEvaluation;
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @NotNull
    private float note;

    @ManyToOne
    @JoinColumn(name = "id_chapitre")
    private Chapitre chapitre;
    
    @ManyToOne
    @JoinColumn(name = "id_inscription")
    private Inscription inscription;

	public Evaluation() {
    }

    public Evaluation(String typeEvaluation, Date date, float note, Chapitre chapitre, Inscription inscription) {
		super();
		this.typeEvaluation = typeEvaluation;
		this.date = date;
		this.note = note;
		this.chapitre = chapitre;
		this.inscription = inscription;
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
    
    public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}
    
    public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}
}
