package org.chemthunder.arboreal.api.event.server;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.chemthunder.arboreal.api.event.ArborealEvent;
import org.chemthunder.arboreal.api.event.EventUtil;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface ItemCallbacks {
    Event<InventoryTick> INVENTORY_TICK = EventFactory.createArrayBacked(InventoryTick.class,
            events -> (
                    stack,
                    world,
                    entity,
                    slot
            ) -> {
                for (InventoryTick event : EventUtil.sortAndCollectEvents(events, InventoryTick::getPriority)) {
                    event.tick(stack, world, entity, slot);
                }
            }
    );

    Event<InjectTooltip> INJECT_TOOLTIP = EventFactory.createArrayBacked(InjectTooltip.class,
            events -> (
                    instance,
                    context,
                    displayComponent,
                    textConsumer,
                    type
            ) -> {
                for (InjectTooltip event : EventUtil.sortAndCollectEvents(events, InjectTooltip::getPriority)) {
                    event.applyTooltip(instance, context, displayComponent, textConsumer, type);
                }
            }
    );

    interface InventoryTick extends ArborealEvent {
        void tick(ItemStack stack, Level world, @Nullable Entity entity, EquipmentSlot slot);
    }

    interface InjectTooltip extends ArborealEvent {
        void applyTooltip(
                ItemStack instance,
                Item.TooltipContext context,
                TooltipDisplay displayComponent,
                Consumer<Component> textConsumer,
                TooltipFlag type
        );
    }
}
