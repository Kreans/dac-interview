package com.kurek.interview.controllers;

import com.kurek.interview.controllers.exception.ItemNotFoundException;
import com.kurek.interview.dto.BidRequestDto;
import com.kurek.interview.dto.ItemDto;
import com.kurek.interview.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable UUID id) {
        return itemService.getItemDto(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    @PostMapping
    public ItemDto addItem(@RequestBody ItemDto itemDto) {
        final var savedItem = itemService.saveItem(itemDto);
        return ItemDto.createDetailedItemDto(savedItem);
    }

    @PostMapping("/{id}/bid")
    public ItemDto addBidRequest(@PathVariable UUID id, @RequestBody BidRequestDto bidRequestDto) {
        final var item = itemService.bidItem(id, bidRequestDto);
        return ItemDto.createDetailedItemDto(item);
    }
}
