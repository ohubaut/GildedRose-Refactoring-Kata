package com.gildedrose.internal;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}