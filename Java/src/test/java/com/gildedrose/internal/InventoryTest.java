package com.gildedrose.internal;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InventoryTest {

    @Test
    void null_is_not_a_valid_item() {
        assertThrows(IllegalArgumentException.class, () -> Inventory.stockItem(null));
    }

    @Test
    void an_item_is_converted_as_a_good() {
        final Item item = new Item("my shiny item", 30, 15);
        final Good good = Inventory.stockItem(item);
        assertNotNull(good, "A good should have been created");
    }

    @Test
    void aged_brie_quality_increase_over_time() {
        final Item agedBrie = new Item("Aged Brie", 2, 0);
        final Good good = Inventory.stockItem(agedBrie);
        good.updateQuality();

        assertAll(() -> assertEquals(1, agedBrie.sellIn, "SellIn should have decrease"),
                  () -> assertEquals(1, agedBrie.quality, "Quality should have increase"));
    }

    @Test
    void sulfuras_is_invariant() {
        final Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        final Good good = Inventory.stockItem(sulfuras);
        good.updateQuality();

        assertAll(() -> assertEquals(0, sulfuras.sellIn, "SellIn should stay the same"),
                  () -> assertEquals(80, sulfuras.quality, "Quality should stay the same"));
    }

    @CsvSource({"15, 20, 21",
                "11, 20, 21",
                "10, 20, 22",
                "6, 20, 22",
                "5, 20, 23",
                "1, 20, 23",
                "0, 20, 0",
                "10, 49, 50",
                "5, 49, 50"})
    @ParameterizedTest
    void backstage_pass_is_speculative(final int sellIn, final int baseQuality, final int expectedQuality) {
        final Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, baseQuality);
        final Good good = Inventory.stockItem(backstagePass);
        good.updateQuality();

        assertAll(() -> assertEquals(sellIn - 1, backstagePass.sellIn, "SellIn should have decreased"),
                  () -> assertEquals(expectedQuality, backstagePass.quality, "Quality does not match expectation"));
    }
}