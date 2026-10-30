package org.chemthunder.arboreal.core.networking.s2c;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvent;
import org.chemthunder.arboreal.core.ArborealCore;

/**
 * @author Chemthunder
 */
public record PlayClientSoundPayload(SoundEvent event, float volume, float pitch) implements CustomPacketPayload {
    public static final Type<PlayClientSoundPayload> ID = new Type<>(ArborealCore.id("play_client_sound"));
    public static final StreamCodec<ByteBuf, PlayClientSoundPayload> CODEC = StreamCodec.composite(
            SoundEvent.DIRECT_STREAM_CODEC, PlayClientSoundPayload::event,
            ByteBufCodecs.FLOAT, PlayClientSoundPayload::volume,
            ByteBufCodecs.FLOAT, PlayClientSoundPayload::pitch,
            PlayClientSoundPayload::new
    );

    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<PlayClientSoundPayload> {
        public void receive(PlayClientSoundPayload payload, ClientPlayNetworking.Context context) {
            SoundManager soundManager = context.client().getSoundManager();

            soundManager.play(
                    SimpleSoundInstance.forUI(
                            payload.event,
                            payload.pitch,
                            payload.volume
                    )
            );
        }
    }
}
