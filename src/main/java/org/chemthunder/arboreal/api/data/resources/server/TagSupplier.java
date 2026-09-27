package org.chemthunder.arboreal.api.data.resources.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.data.resources.DataHook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class TagSupplier<T> extends DataHook {
    private final RegistryKey<? extends Registry<T>> registryKey;
    private List<SuppliedTag> entries;

    public TagSupplier(Identifier id, RegistryKey<? extends Registry<T>> registryKey) {
        super(id);
        this.registryKey = registryKey;
        this.entries = new ArrayList<>();
    }

    public void generate(FabricDataGenerator generator, FabricDataGenerator.Pack pack) {
        class Tags extends FabricTagProvider<T> {
            public Tags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
                super(output, TagSupplier.this.registryKey, registriesFuture);
            }

            protected void configure(RegistryWrapper.WrapperLookup registries) {
                TagSupplier.this.entries = supplyTagEntries();

                TagSupplier.this.entries.forEach(tagEntry -> {
                    for (TagEntry element : tagEntry.entries) {
                        this.getTagBuilder(TagKey.of(TagSupplier.this.registryKey, tagEntry.identifier))
                                .add(element);
                    }
                });
            }
        }

        pack.addProvider(Tags::new);
    }

    public List<SuppliedTag> supplyTagEntries() {
        return List.of();
    }

    public String getDataType() {
        return "tag entries";
    }

    public RegistryKey<Item> getItemKey(ItemConvertible value) {
        return RegistryKey.of(RegistryKeys.ITEM, Registries.ITEM.getId(value.asItem()));
    }

    public RegistryKey<Block> getBlockKey(Block value) {
        return RegistryKey.of(RegistryKeys.BLOCK, Registries.BLOCK.getId(value));
    }

//    public <V extends Entity> RegistryKey<EntityType<V>> getEntityKey(EntityType<V> value) {
//        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Registries.ENTITY_TYPE.getId(value));
//    }

    public record SuppliedTag(Identifier identifier, List<TagEntry> entries) {}
}
