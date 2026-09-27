package org.chemthunder.arboreal.core.impl.data;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagEntry;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.data.resources.server.TagSupplier;
import org.chemthunder.arboreal.core.ArborealCore;
import org.chemthunder.arboreal.core.impl.index.ArborealItems;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class ArborealItemTags extends TagSupplier<Item> {
    public ArborealItemTags(Identifier id) {
        super(id, RegistryKeys.ITEM);
    }

    public List<SuppliedTag> supplyTagEntries() {
        List<SuppliedTag> tags = new ArrayList<>();
        tags.add(new SuppliedTag(ArborealCore.id("debug_tag"), List.of(
                TagEntry.create(ArborealItems.index.getId(ArborealItems.DEBUG))
        )));
        return tags;
    }
}
