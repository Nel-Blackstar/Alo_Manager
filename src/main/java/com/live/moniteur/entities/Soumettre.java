package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;
import com.live.rh.entities.Apprenant;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Soumettre extends LiveEntity {
    @Temporal(TemporalType.DATE)
    private Date dateSoumission;
    private float note;
    @ManyToOne
    @JoinColumn(name = "id_evaluation")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "id_apprenant")
    private Apprenant apprenant;

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
