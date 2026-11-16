package org.chemthunder.arboreal.core.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.chemthunder.arboreal.api.core.task.NetworkingTask;
import org.chemthunder.arboreal.core.networking.s2c.PlayClientSoundPayload;

/**
 * @author Chemthunder
 */
public class ArborealPacketNetwork extends NetworkingTask {
    public void registerTypes() {
        PayloadTypeRegistry.clientboundPlay().register(PlayClientSoundPayload.ID, PlayClientSoundPayload.CODEC);
    }

    public void serverbound() {}

    @Environment(EnvType.CLIENT)
    public void clientbound() {
        ClientPlayNetworking.registerGlobalReceiver(PlayClientSoundPayload.ID, new PlayClientSoundPayload.Receiver());
    }
}
