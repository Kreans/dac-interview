package com.kurek.interview.dto;

import com.kurek.interview.model.Auction;

import java.util.List;
import java.util.UUID;

public class AuctionDto {
    private UUID id;
    private String name;
    private String description;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private AuctionDto() {
    }

    public static AuctionDto createDetailedAuctionDto(Auction auction) {
        final var auctionDto = createSimplyAuctionDto(auction);
        auctionDto.description = auction.getDescription();

        return auctionDto;
    }

    public static AuctionDto createSimplyAuctionDto(Auction auction) {
        final var auctionDto = new AuctionDto();
        auctionDto.id = auction.getId();
        auctionDto.name = auction.getName();

        return auctionDto;
    }

    public static List<AuctionDto> createAuctionList(List<Auction> auctions) {
        return auctions.stream()
                .map(AuctionDto::createSimplyAuctionDto)
                .toList();
    }
}
