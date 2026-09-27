package org.chemthunder.arboreal.api.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.chemthunder.arboreal.api.event.EventUtil;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public interface UpdateRenderStateCallback {
    Event<UpdateRenderStateCallback> EVENT = EventFactory.createArrayBacked(UpdateRenderStateCallback.class,
            events -> (
                    entity,
                    renderState
            ) -> {
                for (UpdateRenderStateCallback event : EventUtil.sortAndCollectEvents(events, UpdateRenderStateCallback::getPriority)) {
                    event.updateRenderState(entity, renderState);
                }
            }
    );

    default int getPriority() {
        return 1000;
    }

    void updateRenderState(LivingEntity living, LivingEntityRenderState renderState);
}