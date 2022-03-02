package com.kurek.interview.service;


import com.kurek.interview.dto.AuctionDto;
import com.kurek.interview.model.Auction;
import com.kurek.interview.repositiry.AuctionRepository;
import com.kurek.interview.repositiry.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Auction> getAuctions() {
        return auctionRepository.findAll();
    }

    public List<AuctionDto> getAuctionsDto() {
        final var auctions = getAuctions();
        return AuctionDto.createAuctionList(auctions);
    }

    public Optional<Auction> getAuction(UUID id) {
        return auctionRepository.findById(id);
    }

    public Optional<AuctionDto> getAuctionDto(UUID id) {
        return getAuction(id)
                .map(AuctionDto::createDetailedAuctionDto);
    }

    public Auction saveAuction(AuctionDto auctionDto) {
        final var auction = new Auction();
        auction.setName(auctionDto.getName());
        auction.setDescription(auctionDto.getDescription());

        return auctionRepository.save(auction);
    }
}
