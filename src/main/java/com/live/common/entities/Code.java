package com.live.common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Code extends LiveEntity {

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String description;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
