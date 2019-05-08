package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class LivrePaie extends LiveEntity {
    @Temporal(TemporalType.DATE)
    private Date dateSaisie;

    public LivrePaie() {
    }

    public LivrePaie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

}
