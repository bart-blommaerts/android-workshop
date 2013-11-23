package be.hp.workshop.data.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrownBagItems {

    public static List<BrownBag> ITEMS = new ArrayList<BrownBag>();
    public static Map<String, BrownBag> ITEM_MAP = new HashMap<String, BrownBag>();

    public BrownBagItems() {
        removeAll();
    }

    public static void addItem(BrownBag item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    public static void removeAll() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }
}
