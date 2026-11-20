package org.chemthunder.arboreal.api.core.task;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.chemthunder.arboreal.api.core.ArborealTask;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public abstract class HudRenderTask implements ArborealTask {
    public final String id;

    public final Arboreal arboreal;

    protected HudRenderTask(String id, Arboreal arboreal) {
        this.arboreal = arboreal;
        this.id = id;
    }

    public abstract void tick(Minecraft client);

    public abstract void render(GuiGraphicsExtractor graphicsExtractor, DeltaTracker tracker);

    public void runMain() {}

    @Environment(EnvType.CLIENT)
    public void runClient() {
        ClientTickEvents.START_CLIENT_TICK.register(this::tick);
        HudElementRegistry.addFirst(this.arboreal.id(this.id), this::render);
    }
}
