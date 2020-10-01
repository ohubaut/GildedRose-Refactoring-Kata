package com.gildedrose.internal;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QualityUpdaterTest {

    @Test
    void regular_item_quality_decrease_over_time() {
        final Item regularItem = new Item("regular item", 10, 20);
        final QualityUpdater updater = QualityUpdater.forRegularItem();

        updater.accept(regularItem);

        assertAll(() -> assertEquals(10, regularItem.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(19, regularItem.quality, "Quality should have decrease by one"));

        // Let's check at sellIn limit

        final Item otherRegularItem = new Item("regular item", 0, 20);

        updater.accept(otherRegularItem);

        assertAll(() -> assertEquals(0, otherRegularItem.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(19, otherRegularItem.quality, "Quality should have decrease by one"));
    }

    @Test
    void regular_item_quality_decrease_faster_after_sellIn_date() {
        final Item regularItem = new Item("regular item", -1, 20);
        final QualityUpdater updater = QualityUpdater.forRegularItem();

        updater.accept(regularItem);

        assertAll(() -> assertEquals(-1, regularItem.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(18, regularItem.quality, "Quality should have decrease by two"));
    }

    @Test
    void legendary_item_quality_is_invariant() {
        final Item legendaryItem = new Item("legendary item", 10, 20);
        final Item otherLegendaryItem = new Item("legendary item", -1, 20);
        final QualityUpdater updater = QualityUpdater.forLegendaryItem();

        updater.accept(legendaryItem);
        updater.accept(otherLegendaryItem);

        assertAll(() -> assertEquals(10, legendaryItem.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(20, legendaryItem.quality, "Quality should be constant"),
                  () -> assertEquals(-1, otherLegendaryItem.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(20, otherLegendaryItem.quality, "Quality should be constant"));
    }

    @Test
    void well_aging_item_quality_increase_over_time() {
        final Item wellAging = new Item("well aging", 10, 20);
        final Item otherWellAging = new Item("well aging", 0, 20);
        final Item pastSellWellAging = new Item("well aging", -1, 20);
        final QualityUpdater updater = QualityUpdater.forWellAgingItem();

        updater.accept(wellAging);
        updater.accept(otherWellAging);
        updater.accept(pastSellWellAging);

        assertAll(() -> assertEquals(10, wellAging.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(0, otherWellAging.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(-1, pastSellWellAging.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(21, wellAging.quality, "Quality should have increased by one"),
                  () -> assertEquals(21, otherWellAging.quality, "Quality should have increased by one"),
                  () -> assertEquals(22, pastSellWellAging.quality, "Quality should have increased by one"));

    }

    @Test
    void well_aging_item_quality_tops_at_50() {
        final Item wellAging = new Item("well aging", 10, 49);
        final QualityUpdater updater = QualityUpdater.forWellAgingItem();

        updater.accept(wellAging);

        assertEquals(50, wellAging.quality, "Quality can increase up to 50");

        updater.accept(wellAging);

        assertEquals(50, wellAging.quality, "Quality cannot increase over 50");
    }

    @CsvSource({"6, 11", "5, 11", "4, 12", "3, 12", "2, 13", "1, 13", "0, 13", "-1, 0"})
    @ParameterizedTest
    void speculative_item_quality_increase_per_step_until_becoming_worthless(final int sellInDate,
                                                                             final int expectedQuality) {
        final Item speculativeItem = new Item("speculative item", sellInDate, 10);
        final QualityUpdater updater = QualityUpdater.forSpeculativeItem(Arrays.asList(5, 3));

        updater.accept(speculativeItem);

        assertAll(() -> assertEquals(sellInDate, speculativeItem.sellIn, "SellIn should not be affected"),
                  () -> assertEquals(expectedQuality, speculativeItem.quality,
                                     "Quality does not match the expectation"));

    }
}