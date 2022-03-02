package com.kurek.interview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private UUID auctionId;
    private String description;
    private int startAmountInCents;
    private Integer bidAmountInCents;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(UUID auctionId) {
        this.auctionId = auctionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartAmountInCents() {
        return startAmountInCents;
    }

    public void setStartAmountInCents(int startAmountInCents) {
        this.startAmountInCents = startAmountInCents;
    }

    public Integer getBidAmountInCents() {
        return bidAmountInCents;
    }

    public void setBidAmountInCents(Integer bidAmountInCents) {
        this.bidAmountInCents = bidAmountInCents;
    }
}
