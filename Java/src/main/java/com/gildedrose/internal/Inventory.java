package com.gildedrose.internal;

import com.gildedrose.Item;
import org.jetbrains.annotations.Contract;

public class Inventory {

    /**
     * Converts an {@code Item} into a sellable Good
     */
    @Contract("null -> fail; !null -> !null")
    public static Good stockItem(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException("An item cannot be null");
        }
        return new Good(item);
    }
}
