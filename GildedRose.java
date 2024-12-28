package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
    
    public void updateQuality() {
        for (Item item : items) {
            processItem(item);
        }
    }

    private void processItem(Item item) {
        refreshItemQuality(item);
        updateSellIn(item);
        handleExpiredItem(item);
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality--;
            }
            applySpecialRules(item);
        } else {
            if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality--;
            }
        }
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
        }
    }

    private void handleExpiredItem(Item item) {
        if (item.sellIn >= 0) return;

        if (item.name.equals("Aged Brie")) {
            if (item.quality < 50) {
                item.quality++;
            }
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality--;
            }
        }
    }

    private void applySpecialRules(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality++;
                }
            }
            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality++;
                }
            }
        }
    }
}
