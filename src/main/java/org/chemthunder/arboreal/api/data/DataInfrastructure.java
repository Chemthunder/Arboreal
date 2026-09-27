package org.chemthunder.arboreal.api.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.chemthunder.arboreal.api.data.resources.DataHook;
import org.chemthunder.arboreal.core.ArborealCore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class DataInfrastructure {
    private final List<DataHook> supports;

    public DataInfrastructure() {
        this.supports = new ArrayList<>();
    }

    public DataInfrastructure instantiateSupport(DataHook support) {
        this.supports.add(support);
        return this;
    }

    public void seal(FabricDataGenerator generator) {
        var pack = generator.createPack();

        for (DataHook support : supports) {
            try {
                support.generate(generator, pack);
                ArborealCore.LOGGER.info("Generated {} from {}", support.getName(), support.getId());
            } catch (Exception e) {
                ArborealCore.LOGGER.info("Unable to generate DataHook: {}", String.valueOf(e));
            }
        }
    }
}
