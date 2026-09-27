package org.chemthunder.arboreal.api;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.registration.Index;

/**
 * @author Chemthunder
 */
public class Arboreal {
    private final String modid;

    public Arboreal(String modid) {
        this.modid = modid;
    }

    public <T> Index<T> createIndex(Registry<T> registry) {
        return new Index<>(registry, modid);
    }

    public Identifier id(String path) {
        return Identifier.of(modid, path);
    }
}
