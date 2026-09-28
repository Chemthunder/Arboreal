package org.chemthunder.arboreal.core.networking.s2c;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.sound.SoundEvent;
import org.chemthunder.arboreal.core.ArborealCore;

/**
 * @author Chemthunder
 */
public record PlayClientSoundPayload(SoundEvent event, float volume, float pitch) implements CustomPayload {
    public static final Id<PlayClientSoundPayload> ID = new Id<>(ArborealCore.id("play_client_sound"));
    public static final PacketCodec<ByteBuf, PlayClientSoundPayload> CODEC = PacketCodec.tuple(
            SoundEvent.PACKET_CODEC, PlayClientSoundPayload::event,
            PacketCodecs.FLOAT, PlayClientSoundPayload::volume,
            PacketCodecs.FLOAT, PlayClientSoundPayload::pitch,
            PlayClientSoundPayload::new
    );

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<PlayClientSoundPayload> {
        public void receive(PlayClientSoundPayload payload, ClientPlayNetworking.Context context) {
            SoundManager soundManager = context.client().getSoundManager();

            soundManager.play(
                    PositionedSoundInstance.ui(
                            payload.event,
                            payload.pitch,
                            payload.volume
                    )
            );
        }
    }
}
