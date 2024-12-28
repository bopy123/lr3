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
        updateItemQuality(item);
        updateSellIn(item);
        handleExpiredItem(item);
    }

    private void updateItemQuality(Item item) {
        if (isAgedBrie(item) || isBackstage(item)) {
        	qualityDecrease(item);
            applySpecialRules(item);
            return;
        }
       	qualityDecrease(item);
    }

    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            item.sellIn--;
        }
    }

    private void handleExpiredItem(Item item) {
        if (item.sellIn >= 0) return;

        if (isAgedBrie(item)) {
        	qualityIncrease(item);
        	return;
            }
        if (isBackstage(item)) {
            item.quality = 0;
            return
        }
        qualityDecrease(item);
    }

    private void applySpecialRules(Item item) {
        if (isBackstage(item)) return; 
        if (item.sellIn < 11) {
        	qualityIncrease(item);
        }
        if (item.sellIn < 6) {
            qualityIncrease(item);
        }
    }
    
    private void qualityDecrease(Item item) {
        if (item.quality > 0 && !isSulfuras(item)) {
            item.quality--;
        }
    }
    
    private void qualityIncrease(Item item) {
    	if (item.quality < 50) {
    		item.quality++;
    	}
    }
    
    private boolean isAgedBrie(Item item) {
    	return item.name.equals("Aged Brie");
    }
    
    private boolean isBackstage(Item item) {
    	return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
    
    private boolean isSulfuras(Item item) {
    	return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
    
}
