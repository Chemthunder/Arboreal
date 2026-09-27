package org.chemthunder.arboreal.api.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import org.chemthunder.arboreal.api.event.EventUtil;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public interface ShouldRenderOutlineCallback {
    Event<ShouldRenderOutlineCallback> EVENT = EventFactory.createArrayBacked(ShouldRenderOutlineCallback.class,
            events -> (
                    entity
            ) -> {
                for (ShouldRenderOutlineCallback event : EventUtil.sortAndCollectEvents(events, ShouldRenderOutlineCallback::getPriority)) {
                    boolean callback = event.shouldHaveOutline(entity);
                    return callback;
                }
                return false;
            }
    );

    default int getPriority() {
        return 1000;
    }

    boolean shouldHaveOutline(Entity entity);
}
