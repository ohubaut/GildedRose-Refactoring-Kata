package com.gildedrose;

class GildedRose {
    private static final int DEFAULT_MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality -= 1;
                    }
                }
            } else {
                if (item.quality < DEFAULT_MAX_QUALITY) {
                    item.quality += 1;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11) {
                            if (item.quality < DEFAULT_MAX_QUALITY) {
                                item.quality += 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < DEFAULT_MAX_QUALITY) {
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
                    if (item.quality < DEFAULT_MAX_QUALITY) {
                        item.quality += 1;
                    }
                }
            }
        }
    }
}