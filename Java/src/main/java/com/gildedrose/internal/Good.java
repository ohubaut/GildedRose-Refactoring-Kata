package com.gildedrose.internal;

import com.gildedrose.Item;

import java.util.function.Consumer;

public class Good {

    static final int MAX_QUALITY = 50;
    private final Item item;
    private final Consumer<Item> itemUpdater;

    Good(final Item item, final SellInUpdater sellInUpdater, final QualityUpdater qualityUpdater) {
        this.item = item;
        itemUpdater = sellInUpdater.andThen(qualityUpdater);
    }

    public void updateQuality() {
        itemUpdater.accept(item);
    }
}
