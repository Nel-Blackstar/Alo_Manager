package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Chapitre extends LiveEntity {
    private String titre;
    
    @ManyToOne
    @JoinColumn(name = "id_cours")
    private Cours cours;
    public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	@Column(columnDefinition = "TEXT")
    private String resume;

    @OneToMany(targetEntity = Evaluation.class)
    private List<Evaluation> evaluations;
    @OneToMany(targetEntity = Suivre.class)
    private List<Evaluation> suivres;

	public Chapitre() {
    }

    public Chapitre(String titre, String resume) {
        this.titre = titre;
        this.resume = resume;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }
    
    public List<Evaluation> getSuivres() {
		return suivres;
	}

	public void setSuivres(List<Evaluation> suivres) {
		this.suivres = suivres;
	}

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
