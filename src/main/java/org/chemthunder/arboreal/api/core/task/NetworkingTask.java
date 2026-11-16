package org.chemthunder.arboreal.api.core.task;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.chemthunder.arboreal.api.core.ArborealTask;
import org.chemthunder.arboreal.core.ArborealCore;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public abstract class NetworkingTask implements ArborealTask {
    public abstract void registerTypes();

    public abstract void serverbound();

    @Environment(EnvType.CLIENT)
    public abstract void clientbound();

    public void runMain() {
        this.registerTypes();
        this.serverbound();

        ArborealCore.LOGGER.info("[NETWORKING] Registered main networking for {}", this.getClass().getSimpleName());
    }

    @Environment(EnvType.CLIENT)
    public void runClient() {
        this.clientbound();

        ArborealCore.LOGGER.info("[NETWORKING] Registered client networking for {}", this.getClass().getSimpleName());
    }
}
