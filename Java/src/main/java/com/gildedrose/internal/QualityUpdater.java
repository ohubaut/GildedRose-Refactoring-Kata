package com.gildedrose.internal;

import com.gildedrose.Item;

import java.util.List;
import java.util.function.Consumer;

interface QualityUpdater extends Consumer<Item> {

    static QualityUpdater forRegularItem() {
        return item -> {
            final int decreaseValue = item.sellIn >= 0 ? 1 : 2;
            item.quality = Math.max(0, item.quality - decreaseValue);
        };
    }

    static QualityUpdater forLegendaryItem() {
        return item -> {
        };
    }

    static QualityUpdater forWellAgingItem() {
        return item -> {
            final int increaseValue = item.sellIn >= 0 ? 1 : 2;
            item.quality = Math.min(Good.MAX_QUALITY, item.quality + increaseValue);
        };
    }

    /**
     * @param sellInMarkers Each step in the sell in process at which the item quality increase fasten.
     */
    static QualityUpdater forSpeculativeItem(final List<Integer> sellInMarkers) {
        return item -> {
            if (item.sellIn < 0) {
                item.quality = 0;
                return;
            }
            final long increaseValue = 1 + sellInMarkers.stream().filter(marker -> item.sellIn < marker).count();
            item.quality = (int) Math.min(Good.MAX_QUALITY, item.quality + increaseValue);
        };
    }

}
