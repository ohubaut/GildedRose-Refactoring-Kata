package com.gildedrose.internal;

import com.gildedrose.Item;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class Inventory {

    private Inventory() {
    }

    /**
     * Converts an {@code Item} into a sellable Good
     */
    @Contract("null -> fail; !null -> !null")
    public static Good stockItem(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException("An item cannot be null");
        }
        final boolean conjured = item.name.startsWith("Conjured ");
        final String normalisedName = conjured ? item.name.substring(9) : item.name;
        final Good good = generateGood(normalisedName, item);
        return conjured ? good.conjure() : good;
    }

    private static Good generateGood(final String normalisedName, final Item item) {
        if (normalisedName.equals("Aged Brie")) {
            return new Good(item, SellInUpdater.forRegularGood(), QualityUpdater.forWellAgingItem());
        }
        if (normalisedName.equals("Sulfuras, Hand of Ragnaros")) {
            return new Good(item, SellInUpdater.forLegendaryGood(), QualityUpdater.forLegendaryItem());
        }
        if (normalisedName.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new Good(item, SellInUpdater.forRegularGood(),
                            QualityUpdater.forSpeculativeItem(Arrays.asList(10, 5)));
        }
        return new Good(item, SellInUpdater.forRegularGood(), QualityUpdater.forRegularItem());
    }
}
