package org.chemthunder.arboreal.api.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import org.chemthunder.arboreal.api.event.ArborealEvent;
import org.chemthunder.arboreal.api.event.EventUtil;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public interface LeftClickCallback extends ArborealEvent {
    Event<LeftClickCallback> EVENT = EventFactory.createArrayBacked(LeftClickCallback.class,
            events -> (
                    client
            ) -> {
                for (LeftClickCallback event : EventUtil.sortAndCollectEvents(events, LeftClickCallback::getPriority)) {
                    event.onLeftClick(client);
                }
            }
    );

    void onLeftClick(MinecraftClient client);
}
