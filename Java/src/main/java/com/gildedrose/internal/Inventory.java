package com.gildedrose.internal;

import com.gildedrose.Item;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.function.Function;

public class Inventory {

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
        final Function<QualityUpdater, QualityUpdater> multiplier = conjured
                                                                    ? QualityUpdater::doublePass
                                                                    : QualityUpdater::singlePass;
        if (normalisedName.equals("Aged Brie")) {
            return new Good(item, SellInUpdater.forRegularGood(), multiplier.apply(QualityUpdater.forWellAgingItem()));
        }
        if (normalisedName.equals("Sulfuras, Hand of Ragnaros")) {
            return new Good(item, SellInUpdater.forLegendaryGood(),
                            multiplier.apply(QualityUpdater.forLegendaryItem()));
        }
        if (normalisedName.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new Good(item, SellInUpdater.forRegularGood(),
                            multiplier.apply(QualityUpdater.forSpeculativeItem(Arrays.asList(10, 5))));
        }
        return new Good(item, SellInUpdater.forRegularGood(), multiplier.apply(QualityUpdater.forRegularItem()));
    }
}
