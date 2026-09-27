package org.chemthunder.arboreal.api.event.server;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.chemthunder.arboreal.api.event.EventUtil;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public interface MobEntityGetTargetCallback {
    Event<MobEntityGetTargetCallback> EVENT = EventFactory.createArrayBacked(MobEntityGetTargetCallback.class,
            events -> (
                    mob,
                    target
            ) -> {
                for (MobEntityGetTargetCallback event : EventUtil.sortAndCollectEvents(events, MobEntityGetTargetCallback::getPriority)) {
                    @Nullable LivingEntity callback = event.getTarget(mob, target);
                    return callback;
                }
                return null;
            }
    );

    default int getPriority() {
        return 1000;
    }

    LivingEntity getTarget(MobEntity mob, LivingEntity originalTarget);
}
