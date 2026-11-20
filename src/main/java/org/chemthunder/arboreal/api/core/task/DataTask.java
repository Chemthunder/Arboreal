package org.chemthunder.arboreal.api.core.task;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.chemthunder.arboreal.api.core.ArborealTask;
import org.chemthunder.arboreal.api.util.objects.KeyMap;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class DataTask<T> implements ArborealTask, Iterable<T> {
    public final KeyMap<ResourceKey<T>, T> items = new KeyMap<>();
    public final ResourceKey<Registry<T>> registry;

    public final Arboreal arboreal;

    public DataTask(ResourceKey<Registry<T>> registry, Arboreal arboreal) {
        this.registry = registry;
        this.arboreal = arboreal;
    }

    public ResourceKey<T> register(String key, T obj) {
        ResourceKey<T> resource = ResourceKey.create(this.registry, this.arboreal.id(key));
        this.items.put(resource, obj);
        return resource;
    }

    public void configure(HolderLookup.Provider lookup, FabricDynamicRegistryProvider.Entries entries) {
        entries.addAll(lookup.lookupOrThrow(this.registry));
    }

    public void buildRegistry(RegistrySetBuilder set) {
        set.add(this.registry, this::generate);
    }

    public void generate(BootstrapContext<T> boot) {
        this.items.forEach(boot::register);
    }

    @NotNull
    public Iterator<T> iterator() {
        return this.items.values().iterator();
    }

    public void runMain() {}

    @Environment(EnvType.CLIENT)
    public void runClient() {}
}
