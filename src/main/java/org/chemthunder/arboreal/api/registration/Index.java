package org.chemthunder.arboreal.api.registration;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class Index<T> implements Iterable<T> {
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
        return this.registry.getKey(object);
    }

    public Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(modid, path);
    }

    public ResourceKey<T> keyOf(String path) {
        return ResourceKey.create(this.registry.key(), this.id(path));
    }

    @NotNull
    public Iterator<T> iterator() {
        return this.objs.iterator();
    }
}
