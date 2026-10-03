package org.chemthunder.arboreal.api.util.objects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public record WorldPoint(RegistryKey<World> dimension, BlockPos pos) {
    public static final Codec<WorldPoint> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            World.CODEC.fieldOf("dimension").forGetter(WorldPoint::dimension),
            BlockPos.CODEC.fieldOf("position").forGetter(WorldPoint::pos)
    ).apply(codec, WorldPoint::new));

    public static final PacketCodec<ByteBuf, WorldPoint> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
