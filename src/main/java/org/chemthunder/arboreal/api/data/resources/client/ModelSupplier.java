package org.chemthunder.arboreal.api.data.resources.client;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.data.resources.DataHook;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class ModelSupplier extends DataHook {
    public ModelSupplier(Identifier id) {
        super(id);
    }

    public void generate(FabricDataGenerator generator, FabricDataGenerator.Pack pack) {
        class Models extends FabricModelProvider {
            public Models(FabricDataOutput output) {
                super(output);
            }

            public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
                ModelSupplier.this.supplyBlockStateModels(blockStateModelGenerator);
            }

            public void generateItemModels(ItemModelGenerator itemModelGenerator) {
                ModelSupplier.this.supplyItemModels(itemModelGenerator);
            }
        }

        pack.addProvider(Models::new);
    }

    public void supplyItemModels(ItemModelGenerator generator) {}
    public void supplyBlockStateModels(BlockStateModelGenerator generator) {}

    public String getDataType() {
        return "models";
    }
}
