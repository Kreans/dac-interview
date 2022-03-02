package com.kurek.interview.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Auction extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "auctionId", updatable = false, insertable = false)
    private List<Item> items;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
