package org.chemthunder.arboreal.api.impl;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface ArborealMiscCodecs {
    PacketCodec<ByteBuf, RegistryKey<DamageType>> DAMAGE_TYPE_REGISTRY_KEY_PACKET_CODEC = RegistryKey.createPacketCodec(RegistryKeys.DAMAGE_TYPE);
    PacketCodec<ByteBuf, List<String>> STRING_LIST = PacketCodecs.codec(Codec.STRING.listOf());
}
