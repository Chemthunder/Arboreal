package org.chemthunder.arboreal.api.registration;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class Index<T> {
    public final List<T> objs = new ArrayList<>();

    private final Registry<T> registry;
    private final String modid;

    public Index(Registry<T> registry, String modid) {
        this.registry = registry;
        this.modid = modid;
    }

    public <M extends T> M register(String name, M obj) {
        this.objs.add(obj);
        return Registry.register(this.registry, id(name), obj);
    }

    public Identifier getId(T object) {
        return this.registry.getId(object);
    }

    public Identifier id(String path) {
        return Identifier.of(modid, path);
    }
}
