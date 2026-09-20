package org.chemthunder.arboreal.api.data.resources.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
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

    public record SuppliedTag(Identifier identifier, List<TagEntry> entries) {}
}
