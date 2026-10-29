package org.chemthunder.arboreal.api.registration;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.util.objects.KeyMap;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class DataIndex<T> {
    private final RegistryKey<Registry<T>> registryKey;
    private final String modid;

    public final KeyMap<RegistryKey<T>, T> output = new KeyMap<>();

    public DataIndex(RegistryKey<Registry<T>> registryKey, String modid) {
        this.modid = modid;
        this.registryKey = registryKey;
    }

    public RegistryKey<T> register(String id, T value) {
        RegistryKey<T> key = RegistryKey.of(this.registryKey, this.id(id));
        this.output.put(key, value);
        return key;
    }

    public void configure(RegistryWrapper.WrapperLookup lookup, FabricDynamicRegistryProvider.Entries entries) {
        entries.addAll(lookup.getOrThrow(this.registryKey));
    }

    public void generate(Registerable<T> registerable) {
        this.output.forEach(registerable::register);
    }

    public Identifier id(String path) {
        return Identifier.of(modid, path);
    }
}
