package org.chemthunder.arboreal.core.impl.index;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import org.chemthunder.arboreal.api.registration.Index;
import org.chemthunder.arboreal.api.utilities.ItemUtil;
import org.chemthunder.arboreal.core.ArborealCore;
import org.chemthunder.arboreal.core.impl.item.DebugItem;

/**
 * @author Chemthunder
 */
public interface ArborealItems {
    Index<Item> index = ArborealCore.MAIN.createIndex(Registries.ITEM);

    Item DEBUG = index.register("debug", new DebugItem(new Item.Settings()
            .maxCount(1)
            .fireproof()
            .registryKey(ItemUtil.key(index.id("debug")))
            .attributeModifiers(ItemUtil.createBasicAttributes(6.5F, -2.6F))
    ));

    static void init() {}
}
