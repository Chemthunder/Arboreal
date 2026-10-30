package org.chemthunder.arboreal.api.data.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface ModelUploader {
    void upload(ItemModelGenerators generator, Item target);
}
