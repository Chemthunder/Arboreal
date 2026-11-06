package org.chemthunder.arboreal.api.data.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Chemthunder
 */
@ApiStatus.Experimental
@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface ModelUploader {
    void upload(ItemModelGenerators generator, Item target);
}
