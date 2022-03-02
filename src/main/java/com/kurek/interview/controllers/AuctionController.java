package com.kurek.interview.controllers;

import com.kurek.interview.controllers.exception.AuctionNotFoundException;
import com.kurek.interview.dto.AuctionDto;
import com.kurek.interview.dto.ItemDto;
import com.kurek.interview.service.AuctionService;
import com.kurek.interview.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<AuctionDto> getAuctions() {
        return auctionService.getAuctionsDto();
    }

    @GetMapping("/{id}")
    public AuctionDto getAuction(@PathVariable UUID id) {
        return auctionService.getAuctionDto(id)
                .orElseThrow(AuctionNotFoundException::new);
    }

    @PostMapping
    public AuctionDto addAuction(@RequestBody AuctionDto auctionDto) {
        final var savedAuction = auctionService.saveAuction(auctionDto);
        return AuctionDto.createDetailedAuctionDto(savedAuction);
    }

    @GetMapping("/{id}/items")
    public List<ItemDto> getItems(@PathVariable UUID id) {
        return itemService.getItemsDto(id);
    }
}
