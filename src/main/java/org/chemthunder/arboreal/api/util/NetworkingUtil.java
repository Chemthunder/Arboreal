package org.chemthunder.arboreal.api.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Chemthunder
 */
@ApiStatus.NonExtendable
@SuppressWarnings("unused")
public abstract class NetworkingUtil {
    public static void sendToAll(Level level, CustomPacketPayload payload) {
        MinecraftServer server = level.getServer();

        if (server != null) {
            for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
                ServerPlayNetworking.send(serverPlayer, payload);
            }
        }
    }

    public static void sendToAll(Entity entity, CustomPacketPayload payload) {
        MinecraftServer server = entity.level().getServer();

        if (server != null) {
            for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
                ServerPlayNetworking.send(serverPlayer, payload);
            }
        }
    }
}
