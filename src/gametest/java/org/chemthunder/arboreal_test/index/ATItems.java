package org.chemthunder.arboreal_test.index;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.chemthunder.arboreal.api.core.task.RegisterTask;
import org.chemthunder.arboreal_test.ArborealTest;

/**
 * @author Chemthunder
 */
public interface ATItems {
    RegisterTask<Item> task = ArborealTest.MAIN.createTask(RegisterTask::new, BuiltInRegistries.ITEM);

    Item TEST = task.register("test", new Item(new Item.Properties().stacksTo(4)));
}
