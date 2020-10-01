package com.gildedrose.internal;

import com.gildedrose.Item;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class Inventory {

    /**
     * Converts an {@code Item} into a sellable Good
     */
    @Contract("null -> fail; !null -> !null")
    public static Good stockItem(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException("An item cannot be null");
        }
        if (item.name.equals("Aged Brie")) {
            return new Good(item, SellInUpdater.forRegularGood(), QualityUpdater.forWellAgingItem());
        }
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return new Good(item, SellInUpdater.forLegendaryGood(), QualityUpdater.forLegendaryItem());
        }
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new Good(item, SellInUpdater.forRegularGood(),
                            QualityUpdater.forSpeculativeItem(Arrays.asList(10, 5)));
        }
        return new Good(item, SellInUpdater.forRegularGood(), QualityUpdater.forRegularItem());
    }
}
