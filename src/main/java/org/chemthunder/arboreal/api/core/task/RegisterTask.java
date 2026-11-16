package org.chemthunder.arboreal.api.core.task;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
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
public class RegisterTask<T> implements ArborealTask, Iterable<T> {
    public final KeyMap<String, T> registrationTargets = new KeyMap<>();
    public final Registry<T> registry;

    protected final Arboreal arboreal;

    public RegisterTask(Registry<T> registry, Arboreal arboreal) {
        this.registry = registry;
        this.arboreal = arboreal;
    }

    public T register(String name, T object) {
        this.registrationTargets.put(name, object);
        return object;
    }

    public ResourceKey<T> keyOf(String name) {
        return ResourceKey.create(this.registry.key(), this.arboreal.id(name));
    }

    @NotNull
    public Iterator<T> iterator() {
        return this.registrationTargets.values().iterator();
    }

    public void runMain() {
        for (T obj : registrationTargets.values()) {
            String key = registrationTargets.getFromVal(obj);
            Registry.register(this.registry, this.arboreal.id(key), obj);
        }
    }

    @Environment(EnvType.CLIENT)
    public void runClient() {}
}
