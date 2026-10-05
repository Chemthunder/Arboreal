package org.chemthunder.arboreal.api.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public class test extends S2CPayload<test> {
    public test(Identifier id) {
        super(id);
    }

    public PacketCodec<? super RegistryByteBuf, test> getCodec() {
        return null;
    }

    public void receive(test payload, ClientPlayNetworking.Context context) {
    }

    public void registerType() {

    }

    public void registerReceiver() {

    }
}
