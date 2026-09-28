package org.chemthunder.arboreal.api.impl;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.LivingEntity;
import org.chemthunder.arboreal.core.ArborealCore;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public interface ArborealAttachmentTypes {
    AttachmentType<LazyEntityReference<LivingEntity>> OWNER = register(
            "owner",
            builder -> builder
                    .syncWith(LazyEntityReference.createPacketCodec(), AttachmentSyncPredicate.all())
                    .persistent(LazyEntityReference.createCodec())
    );

    static void init() {}

    static <T> AttachmentType<T> register(String name, Consumer<AttachmentRegistry.Builder<T>> consumer) {
        return AttachmentRegistry.create(ArborealCore.id(name), consumer);
    }
}
