package com.gildedrose.internal;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GoodTest {

    @Test
    void degrading_affects_the_underlying_item() {
        final int initialSellIn = 30;
        final int intitalQuality = 15;
        final Item item = new Item("sample item", initialSellIn, intitalQuality);
        final Good good = new Good(item);
        good.updateQuality();
        assertAll(() -> assertTrue(initialSellIn > item.sellIn, "SellIn should have decreased"),
                  () -> assertTrue(intitalQuality > item.quality, "Quality should have decreased"));
    }
}