package org.chemthunder.arboreal.api.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public abstract class PacketNetwork {
    public abstract void registerTypes();

    public abstract void client2server();

    @Environment(EnvType.CLIENT)
    public abstract void server2client();
}
