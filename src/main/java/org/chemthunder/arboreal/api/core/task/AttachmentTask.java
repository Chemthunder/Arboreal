package org.chemthunder.arboreal.api.core.task;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.chemthunder.arboreal.api.core.ArborealTask;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public record AttachmentTask(Arboreal arboreal) implements ArborealTask {

    public <T> AttachmentType<T> register(String name, Consumer<AttachmentRegistry.Builder<T>> consumer) {
        return AttachmentRegistry.create(this.arboreal.id(name), consumer);
    }

    public void runMain() {}

    @Environment(EnvType.CLIENT)
    public void runClient() {}
}
