package org.chemthunder.arboreal.api;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.data.client.ModelUploader;
import org.chemthunder.arboreal.api.registration.Index;
import org.chemthunder.arboreal.core.ArborealCore;

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

    public void mapModelUploader(ModelUploader uploader, Item target) {
        ArborealCore.UPLOADERS.put(uploader, target);
    }
}
