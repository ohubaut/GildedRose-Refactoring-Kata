package com.gildedrose.internal;

import com.gildedrose.Item;

public class Good {

    static final int MAX_QUALITY = 50;
    private final Item item;

    Good(final Item item) {
        this.item = item;
    }

    public void updateQuality() {
        if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.quality -= 1;
                }
            }
        } else {
            if (item.quality < MAX_QUALITY) {
                item.quality += 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        if (item.quality < MAX_QUALITY) {
                            item.quality += 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < MAX_QUALITY) {
                            item.quality += 1;
                        }
                    }
                }
            }
        }

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn -= 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals("Aged Brie")) {
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality -= 1;
                        }
                    }
                } else {
                    item.quality -= item.quality;
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality += 1;
                }
            }
        }
    }
}
