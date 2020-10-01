package com.gildedrose.internal;

import com.gildedrose.Item;

public class Good {

    static final int MAX_QUALITY = 50;
    private final Item item;
    private final SellInUpdater sellInUpdater;
    private final QualityUpdater qualityUpdater;

    Good(final Item item, final SellInUpdater sellInUpdater, final QualityUpdater qualityUpdater) {
        this.item = item;
        this.sellInUpdater = sellInUpdater;
        this.qualityUpdater = qualityUpdater;
    }

    public void updateQuality() {
        sellInUpdater.andThen(qualityUpdater).accept(item);
    }

    Good conjure() {
        final QualityUpdater conjuredUpdater = i -> {
            qualityUpdater.accept(i);
            qualityUpdater.accept(i);
        };
        return new Good(item, sellInUpdater, conjuredUpdater);
    }
}
