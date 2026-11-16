package org.chemthunder.test.index;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.chemthunder.arboreal.api.core.task.RegisterTask;
import org.chemthunder.test.ATest;

/**
 * @author Chemthunder
 */
public interface TestItems {
    RegisterTask<Item> TASK = new RegisterTask<>(BuiltInRegistries.ITEM, ATest.MAIN);

    Item TEST = TASK.register("test", new Item(new Item.Properties()
            .stacksTo(1)
            .setId(TASK.keyOf("test"))
    ));
}
