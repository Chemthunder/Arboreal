package org.chemthunder.arboreal.core.index;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import org.chemthunder.arboreal.api.registration.Index;
import org.chemthunder.arboreal.api.util.ItemUtil;
import org.chemthunder.arboreal.core.ArborealCore;
import org.chemthunder.arboreal.core.item.TestItem;

/**
 * @author Chemthunder
 */
public interface ArborealItems {
    Index<Item> index = ArborealCore.MAIN.createIndex(Registries.ITEM);

    Item TEST = index.register("test", new TestItem(new Item.Settings()
            .registryKey(ItemUtil.key(index.id("test")))
            .maxCount(1))
    );

    static void init() {}
}
