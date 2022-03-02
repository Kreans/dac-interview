package com.kurek.interview.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

public class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
