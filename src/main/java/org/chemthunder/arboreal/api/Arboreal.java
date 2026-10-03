package org.chemthunder.arboreal.api;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.registration.Index;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public record Arboreal(String modId) {
    public <T> Index<T> createIndex(Registry<T> registry) {
        return new Index<>(registry, modId);
    }

    public Identifier id(String path) {
        return Identifier.of(modId, path);
    }
}
