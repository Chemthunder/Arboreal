package org.chemthunder.arboreal.api.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import org.chemthunder.arboreal.api.event.EventUtil;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public interface LeftClickCallback {
    Event<LeftClickCallback> EVENT = EventFactory.createArrayBacked(LeftClickCallback.class,
            events -> (
                    client
            ) -> {
                for (LeftClickCallback event : EventUtil.sortAndCollectEvents(events, LeftClickCallback::getPriority)) {
                    event.onLeftClick(client);
                }
            }
    );

    default int getPriority() {
        return 1000;
    }

    void onLeftClick(MinecraftClient client);
}
