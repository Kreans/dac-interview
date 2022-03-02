package com.kurek.interview.dto;

import com.kurek.interview.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

public class ItemDto {
    private UUID id;
    private String name;
    private UUID auctionId;
    private String description;
    private Integer startAmountInCents;
    private BigDecimal startAmountInPln;
    private Integer bidAmountInCents;
    private BigDecimal bidAmountInPln;

    private ItemDto() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStartAmountInCents() {
        return startAmountInCents;
    }

    public Integer getBidAmountInCents() {
        return bidAmountInCents;
    }

    public BigDecimal getStartAmountInPln() {
        return startAmountInPln;
    }

    public BigDecimal getBidAmountInPln() {
        return bidAmountInPln;
    }

    public static ItemDto createSimplyItemDto(Item item) {
        final var itemDto = new ItemDto();
        itemDto.id = item.getId();
        itemDto.name = item.getName();

        return itemDto;
    }

    public static ItemDto createDetailedItemDto(Item item, BigDecimal euroToPlnRatio) {
        final var itemDto = createDetailedItemDto(item);
        itemDto.startAmountInPln = convertEuroCentsToPln(item.getStartAmountInCents(), euroToPlnRatio);
        itemDto.bidAmountInPln = convertEuroCentsToPln(item.getBidAmountInCents(), euroToPlnRatio);

        return itemDto;
    }

    public static ItemDto createDetailedItemDto(Item item) {
        final var itemDto = createSimplyItemDto(item);
        itemDto.auctionId = item.getAuctionId();
        itemDto.description = item.getDescription();
        itemDto.startAmountInCents = item.getStartAmountInCents();
        itemDto.bidAmountInCents = item.getBidAmountInCents();

        return itemDto;
    }

    public static List<ItemDto> createItemsList(List<Item> items) {
        return items.stream()
                .map(ItemDto::createSimplyItemDto)
                .toList();
    }

    private static BigDecimal convertEuroCentsToPln(Integer euroCents, BigDecimal ratio) {
        if (euroCents != null && ratio != null) {
            final var euro = BigDecimal.valueOf(euroCents).divide(BigDecimal.valueOf(100), 2, RoundingMode.UNNECESSARY);
            return euro.multiply(ratio);
        }
        return null;
    }
}
