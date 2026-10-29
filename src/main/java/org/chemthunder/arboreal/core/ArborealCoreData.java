package org.chemthunder.arboreal.core;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

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

    public static class Dynamics extends FabricDynamicRegistryProvider {
        public Dynamics(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
            ArborealCore.TO_REGISTER.forEach(index -> index.configure(registries, entries));
        }

        public String getName() {
            return "Dynamic Registries";
        }
    }
}
