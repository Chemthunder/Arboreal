package org.chemthunder.arboreal.api.registration;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.chemthunder.arboreal.api.util.objects.KeyMap;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class DataIndex<T> {
    private final ResourceKey<Registry<T>> resourceKey;
    private final String modid;

    public final KeyMap<ResourceKey<T>, T> output = new KeyMap<>();

    public DataIndex(ResourceKey<Registry<T>> resourceKey, String modid) {
        this.modid = modid;
        this.resourceKey = resourceKey;
    }

    public ResourceKey<T> register(String id, T value) {
        ResourceKey<T> key = ResourceKey.create(this.resourceKey, this.id(id));
        this.output.put(key, value);
        return key;
    }

    public void configure(HolderLookup.Provider lookup, FabricDynamicRegistryProvider.Entries entries) {
        entries.addAll(lookup.lookupOrThrow(this.resourceKey));
    }

    public void generate(BootstrapContext<T> boot) {
        this.output.forEach(boot::register);
    }

    public Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(modid, path);
    }
}
