package org.chemthunder.arboreal.api.data.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagBuilder;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public abstract class ArborealTagWidener<T> extends FabricTagProvider<T> {
    public ArborealTagWidener(FabricDataOutput output, RegistryKey<? extends Registry<T>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }

    public TagBuilder accessTagBuilder(TagKey<T> tagKey) {
        return this.getTagBuilder(tagKey);
    }
}
