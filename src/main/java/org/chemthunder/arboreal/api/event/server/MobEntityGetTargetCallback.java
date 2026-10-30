package org.chemthunder.arboreal.api.event.server;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.chemthunder.arboreal.api.event.ArborealEvent;
import org.chemthunder.arboreal.api.event.EventUtil;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public interface MobEntityGetTargetCallback extends ArborealEvent {
    Event<MobEntityGetTargetCallback> EVENT = EventFactory.createArrayBacked(MobEntityGetTargetCallback.class,
            events -> (
                    mob,
                    target
            ) -> {
                for (MobEntityGetTargetCallback event : EventUtil.sortAndCollectEvents(events, MobEntityGetTargetCallback::getPriority)) {
                    @Nullable LivingEntity callback = event.getTarget(mob, target);
                    return callback;
                }
                return target;
            }
    );

    @Nullable LivingEntity getTarget(Mob mob, LivingEntity originalTarget);
}
