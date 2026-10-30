package org.chemthunder.arboreal.api.event.server;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.chemthunder.arboreal.api.event.ArborealEvent;
import org.chemthunder.arboreal.api.event.EventUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface WorldInteractionCallbacks {
    Event<OnKillOther> ON_KILL_OTHER = EventFactory.createArrayBacked(OnKillOther.class,
            events -> (
                    entity,
                    victim
            ) -> {
                for (OnKillOther event : EventUtil.sortAndCollectEvents(events, OnKillOther::getPriority)) {
                    event.onKillOther(entity, victim);
                }
            }
    );

    Event<EditDamage> EDIT_DAMAGE = EventFactory.createArrayBacked(EditDamage.class,
            events -> (
                    attacker,
                    victim,
                    source,
                    amount
            ) -> {
                for (EditDamage event : EventUtil.sortAndCollectEvents(events, EditDamage::getPriority)) {
                    return event.editDamage(attacker, victim, source, amount);
                }
                return Optional.empty();
            }
    );

    interface OnKillOther extends ArborealEvent {
        void onKillOther(Entity entity, LivingEntity victim);
    }

    interface EditDamage extends ArborealEvent {
        Optional<Pair<DamageSource, Float>> editDamage(@Nullable Entity attacker, LivingEntity victim, DamageSource source, float amount);
    }
}
