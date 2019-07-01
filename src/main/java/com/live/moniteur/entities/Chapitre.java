package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Chapitre extends LiveEntity {
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String resume;

    @OneToMany(targetEntity = Evaluation.class)
    private List<Evaluation> evaluations;

    public Chapitre() {
    }

    public Chapitre(String titre, String resume) {
        this.titre = titre;
        this.resume = resume;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
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
