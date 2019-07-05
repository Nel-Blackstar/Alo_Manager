package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Suivre extends LiveEntity {
	private String appreciation;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_chapitre")
    private Chapitre chapitre;
    
    @ManyToOne
    @JoinColumn(name = "id_inscription")
    private Inscription inscription;

	public Suivre() {
    }


	public Suivre(String appreciation, Date date) {
		super();
		this.appreciation = appreciation;
		this.date = date;
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
    
    public String getAppreciation() {
		return appreciation;
	}


	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}


	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}
}
