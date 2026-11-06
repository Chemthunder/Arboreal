package org.chemthunder.arboreal.api.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.chemthunder.arboreal.core.ArborealCore;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Chemthunder
 */
@ApiStatus.Experimental
@SuppressWarnings("unused")
public abstract class PacketNetwork {
    public abstract void registerTypes();

    public abstract void client2server();

    @Environment(EnvType.CLIENT)
    public abstract void server2client();

    public static void registerNetwork(PacketNetwork network) {
        ArborealCore.NETWORKS.add(network);
        ArborealCore.LOGGER.info("[NETWORKING] Created new PacketNetwork: {}", network.toString());
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
