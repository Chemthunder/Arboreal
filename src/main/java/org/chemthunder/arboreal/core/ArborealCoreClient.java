package org.chemthunder.arboreal.core;

import net.fabricmc.api.ClientModInitializer;
import org.chemthunder.arboreal.api.networking.PacketNetwork;

/**
 * @author Chemthunder
 */
public class ArborealCoreClient implements ClientModInitializer {
    public void onInitializeClient() {
        ArborealCore.NETWORKS.forEach(PacketNetwork::server2client);
    }
}
