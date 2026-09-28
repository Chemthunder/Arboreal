package org.chemthunder.arboreal.api.event.server;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Pair;
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
                    self,
                    source,
                    amount
            ) -> {
                for (EditDamage event : EventUtil.sortAndCollectEvents(events, EditDamage::getPriority)) {
                    return event.editDamage(attacker, self, source, amount);
                }
                return Optional.empty();
            }
    );

    interface OnKillOther extends ArborealEvent {
        void onKillOther(Entity entity, LivingEntity victim);
    }

    interface EditDamage extends ArborealEvent {
        Optional<Pair<RegistryKey<DamageType>, Float>> editDamage(@Nullable Entity attacker, LivingEntity self, DamageSource source, float amount);
    }
}
