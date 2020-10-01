package com.gildedrose.internal;

import com.gildedrose.Item;

import java.util.function.Consumer;

@FunctionalInterface
interface SellInUpdater extends Consumer<Item> {

    static SellInUpdater forRegularGood() {
        return item -> item.sellIn--;
    }

    static SellInUpdater forLegendaryGood() {
        return item -> {
        };
    }
}
