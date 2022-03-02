package com.kurek.interview.model;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Item extends BaseEntity {

    private String name;
    private UUID auctionId;
    private String description;
    private int startAmountInCents;
    private Integer bidAmountInCents;

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
