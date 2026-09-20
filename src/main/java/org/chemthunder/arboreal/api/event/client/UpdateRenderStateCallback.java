package org.chemthunder.arboreal.api.event.client;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface UpdateRenderStateCallback {
    Event<UpdateRenderStateCallback> EVENT = EventFactory.createArrayBacked(UpdateRenderStateCallback.class,
            events -> (
                    entity,
                    renderState
            ) -> {
                List<UpdateRenderStateCallback> sortedEvents = new ArrayList<>(Arrays.asList(events));
                sortedEvents.sort(Comparator.comparingInt(UpdateRenderStateCallback::getPriority));
                for (UpdateRenderStateCallback event : sortedEvents) {
                    event.updateRenderState(entity, renderState);
                }
            }
    );

    default int getPriority() {
        return 1000;
    }

    void updateRenderState(LivingEntity living, LivingEntityRenderState renderState);
}