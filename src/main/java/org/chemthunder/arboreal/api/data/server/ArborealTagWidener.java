package org.chemthunder.arboreal.api.data.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public abstract class ArborealTagWidener<T> extends TagsProvider<T> {
    public ArborealTagWidener(PackOutput output, ResourceKey<? extends Registry<T>> registryKey, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, registryKey, lookupProvider);
    }

    public TagBuilder accessTagBuilder(TagKey<T> tagKey) {
        return this.getOrCreateRawBuilder(tagKey);
    }
}
