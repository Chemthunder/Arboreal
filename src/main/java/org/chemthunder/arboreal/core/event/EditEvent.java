package org.chemthunder.arboreal.core.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.util.Pair;
import org.chemthunder.arboreal.api.event.server.WorldInteractionCallbacks;
import org.chemthunder.arboreal.core.index.ArborealItems;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Chemthunder
 */
public class EditEvent implements WorldInteractionCallbacks.EditDamage {
    public Optional<Pair<DamageSource, Float>> editDamage(@Nullable Entity attacker, LivingEntity victim, DamageSource source, float amount) {
        if (victim.getMainHandStack().isOf(ArborealItems.TEST)) {
            return Optional.of(new Pair<>(victim.getDamageSources().create(DamageTypes.ARROW, attacker), amount * 2));
        }
        return Optional.empty();
    }
}
