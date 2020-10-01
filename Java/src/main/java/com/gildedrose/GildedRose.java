package com.gildedrose;

import com.gildedrose.internal.Good;
import com.gildedrose.internal.Inventory;

import java.util.ArrayList;
import java.util.List;

class GildedRose {

    private final List<Good> availableGoods = new ArrayList<>();

    Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
        for (final Item item : items) {
            availableGoods.add(Inventory.stockItem(item));
        }
    }

    public void updateQuality() {
        availableGoods.forEach(Good::updateQuality);
    }
}