package org.chemthunder.arboreal.core;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ArborealCoreData {
    public static class Models extends FabricModelProvider {
        public Models(FabricPackOutput output) {
            super(output);
        }

        public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {}

        public void generateItemModels(ItemModelGenerators itemModelGenerators) {
            ArborealCore.UPLOADERS.forEach(((modelUploader, item) ->
                    modelUploader.upload(itemModelGenerators, item))
            );
        }
    }

    public static class Dynamics extends FabricDynamicRegistryProvider {
        public Dynamics(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        protected void configure(HolderLookup.Provider registries, Entries entries) {
            ArborealCore.TO_REGISTER.forEach(index -> index.configure(registries, entries));
        }

        public String getName() {
            return "Dynamic Registries";
        }
    }
}
