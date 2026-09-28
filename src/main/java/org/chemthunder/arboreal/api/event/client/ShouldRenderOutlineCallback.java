package org.chemthunder.arboreal.api.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import org.chemthunder.arboreal.api.event.ArborealEvent;
import org.chemthunder.arboreal.api.event.EventUtil;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public interface ShouldRenderOutlineCallback extends ArborealEvent {
    Event<ShouldRenderOutlineCallback> EVENT = EventFactory.createArrayBacked(ShouldRenderOutlineCallback.class,
            events -> (
                    entity
            ) -> {
                for (ShouldRenderOutlineCallback event : EventUtil.sortAndCollectEvents(events, ShouldRenderOutlineCallback::getPriority)) {
                    return event.shouldHaveOutline(entity);
                }
                return false;
            }
    );

    boolean shouldHaveOutline(Entity entity);
}
