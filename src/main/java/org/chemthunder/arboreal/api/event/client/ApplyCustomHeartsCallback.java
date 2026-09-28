package org.chemthunder.arboreal.api.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.event.ArborealEvent;
import org.chemthunder.arboreal.api.event.EventUtil;

import java.util.Optional;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public interface ApplyCustomHeartsCallback extends ArborealEvent {
    Event<ApplyCustomHeartsCallback> EVENT = EventFactory.createArrayBacked(ApplyCustomHeartsCallback.class,
            events -> (
                    client,
                    cameraPlayer
            ) -> {
                for (ApplyCustomHeartsCallback event : EventUtil.sortAndCollectEvents(events, ApplyCustomHeartsCallback::getPriority)) {
                    return event.getHeartIdentifier(client, cameraPlayer);
                }
                return Optional.empty();
            }
    );

    Optional<Identifier> getHeartIdentifier(MinecraftClient client, PlayerEntity cameraPlayer);
}
