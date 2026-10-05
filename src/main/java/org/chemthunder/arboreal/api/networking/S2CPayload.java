package org.chemthunder.arboreal.api.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public abstract class S2CPayload<T extends S2CPayload<?>> implements CustomPayload, ArborealPayload {
    private final Identifier id;

    public S2CPayload(Identifier id) {
        this.id = id;
    }

    public Id<? extends CustomPayload> getId() {
        return new Id<>(this.id);
    }

    public abstract PacketCodec<? super RegistryByteBuf, T> getCodec();

    public abstract void receive(T payload, ClientPlayNetworking.Context context);

    public class Relay implements ClientPlayNetworking.PlayPayloadHandler<T> {
        public void receive(T payload, ClientPlayNetworking.Context context) {
            S2CPayload.this.receive(payload, context);
        }
    }
}
