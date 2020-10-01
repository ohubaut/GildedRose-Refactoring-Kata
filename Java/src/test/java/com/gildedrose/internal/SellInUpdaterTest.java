package com.gildedrose.internal;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SellInUpdaterTest {

    @Test
    void regular_item_sellIn_decrease_over_time() {
        final Item regularItem = new Item("regular item", 10, 42);
        final SellInUpdater updater = SellInUpdater.forRegularGood();

        updater.accept(regularItem);
        assertAll(() -> assertEquals(9, regularItem.sellIn, "SellIn should be decreased by one"),
                  () -> assertEquals(42, regularItem.quality, "Quality should not be impacted"));
    }

    @Test
    void legendary_item_sellIn_is_invariant() {
        final Item legendaryItem = new Item("legendary item", 10, 42);
        final SellInUpdater updater = SellInUpdater.forLegendaryGood();

        updater.accept(legendaryItem);
        assertAll(() -> assertEquals(10, legendaryItem.sellIn, "SellIn should not be impacted"),
                  () -> assertEquals(42, legendaryItem.quality, "Quality should not be impacted"));
    }
}