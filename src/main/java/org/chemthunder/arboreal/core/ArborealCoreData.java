package org.chemthunder.arboreal.core;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

/**
 * @author Chemthunder
 */
public class ArborealCoreData {
    public static class Models extends FabricModelProvider {
        public Models(FabricDataOutput output) {
            super(output);
        }

        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            ArborealCore.UPLOADERS.forEach(((modelUploader, item) ->
                    modelUploader.upload(itemModelGenerator, item))
            );
        }
    }
}
