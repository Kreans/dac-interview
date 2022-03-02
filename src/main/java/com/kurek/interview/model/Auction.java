package com.kurek.interview.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Auction extends BaseEntity {

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
