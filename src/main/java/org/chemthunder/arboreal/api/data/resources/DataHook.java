package org.chemthunder.arboreal.api.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public abstract class DataHook {
    private final Identifier id;

    public DataHook(Identifier id) {
        this.id = id;
    }

    public abstract void generate(FabricDataGenerator generator, FabricDataGenerator.Pack pack);

    public abstract String getName();

    public Identifier getId() {
        return id;
    }
}
