package com.kurek.interview.service;

import com.kurek.interview.controllers.exception.BidAmountTooLow;
import com.kurek.interview.controllers.exception.ItemNotFoundException;
import com.kurek.interview.dto.BidRequestDto;
import com.kurek.interview.dto.ItemDto;
import com.kurek.interview.model.Item;
import com.kurek.interview.repositiry.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ExchangeService exchangeService;

    public List<Item> getItems(UUID id) {
        return itemRepository.findAllByAuctionId(id);
    }

    public List<ItemDto> getItemsDto(UUID id) {
        final var items = getItems(id);
        return ItemDto.createItemsList(items);
    }

    public Optional<Item> getItem(UUID id) {
        return itemRepository.findById(id);
    }

    public Optional<ItemDto> getItemDto(UUID id) {
        final var ratio = exchangeService.exchangeEuroCentsToPln();

        return getItem(id)
                .map(item -> ItemDto.createDetailedItemDto(item, ratio));
    }

    public Item saveItem(ItemDto itemDto) {
        final var item = new Item();
        item.setAuctionId(itemDto.getAuctionId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setStartAmountInCents(itemDto.getStartAmountInCents());

        return itemRepository.save(item);
    }

    public Item bidItem(UUID itemId, BidRequestDto bidRequestDto) {
        final var item = getItem(itemId)
                .orElseThrow(ItemNotFoundException::new);

        if (validateBidAmounts(bidRequestDto, item)) {
            item.setBidAmountInCents(bidRequestDto.getBidAmountInCents());
            return itemRepository.save(item);
        }
        throw new BidAmountTooLow();
    }

    private boolean validateBidAmounts(BidRequestDto bidRequestDto, Item item) {
        return (item.getBidAmountInCents() == null ||
                bidRequestDto.getBidAmountInCents() > item.getBidAmountInCents()) &&
                bidRequestDto.getBidAmountInCents() > item.getBidAmountInCents();
    }
}