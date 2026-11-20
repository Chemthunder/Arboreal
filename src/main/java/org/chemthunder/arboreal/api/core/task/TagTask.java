package org.chemthunder.arboreal.api.core.task;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.chemthunder.arboreal.api.core.ArborealTask;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class TagTask<T> implements ArborealTask {
    public final ResourceKey<Registry<T>> registry;

    public final Arboreal arboreal;

    public TagTask(ResourceKey<Registry<T>> registry, Arboreal arboreal) {
        this.registry = registry;
        this.arboreal = arboreal;
    }

    public TagKey<T> create(String name) {
        return TagKey.create(this.registry, this.arboreal.id(name));
    }

    public void runMain() {}

    @Environment(EnvType.CLIENT)
    public void runClient() {}
}
