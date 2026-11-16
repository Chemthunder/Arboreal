package org.chemthunder.test.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import org.chemthunder.arboreal.api.core.task.DataTask;
import org.chemthunder.test.ATest;

/**
 * @author Chemthunder
 */
public interface TestDamageTypes {
    DataTask<DamageType> TASK = new DataTask<>(Registries.DAMAGE_TYPE, ATest.MAIN);

    ResourceKey<DamageType> TEST = TASK.register("test", new DamageType("test", 4.0F));
}
