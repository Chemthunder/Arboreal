package org.chemthunder.arboreal.core.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.chemthunder.arboreal.api.networking.PacketNetwork;
import org.chemthunder.arboreal.core.networking.s2c.PlayClientSoundPayload;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class ArborealPacketNetwork extends PacketNetwork {
    public void registerTypes() {
        PayloadTypeRegistry.playS2C().register(PlayClientSoundPayload.ID, PlayClientSoundPayload.CODEC);
    }

    public void client2server() {}

    @Environment(EnvType.CLIENT)
    public void server2client() {
        ClientPlayNetworking.registerGlobalReceiver(PlayClientSoundPayload.ID, new PlayClientSoundPayload.Receiver());
    }
}
