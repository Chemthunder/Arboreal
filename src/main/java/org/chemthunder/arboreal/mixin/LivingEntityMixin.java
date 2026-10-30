package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import org.chemthunder.arboreal.api.event.server.WorldInteractionCallbacks;
import org.chemthunder.arboreal.api.item.CustomConsumeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, WaypointTransmitter {
    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @WrapOperation(
            method = "spawnItemParticles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
            )
    )
    private void arboreal$CustomConsumeItem(Level instance, ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd, Operation<Void> original) {
        LivingEntity living = (LivingEntity) (Object)this;

        if (living.getItemInHand(living.getUsedItemHand()).getItem() instanceof CustomConsumeItem c) {
            original.call(instance, c.getConsumeParticleEffect(living), x, y, z, xd, yd, zd);
            return;
        }
        original.call(instance, particle, x, y, z, xd, yd, zd);
    }

    @WrapMethod(method = "hurtServer")
    private boolean arboreal$WorldInteractionCallbacks$EditDamage(ServerLevel level, DamageSource source, float damage, Operation<Boolean> original) {
        LivingEntity living = (LivingEntity) (Object) this;

        Optional<Pair<DamageSource, Float>> optionalPair = WorldInteractionCallbacks.EDIT_DAMAGE.invoker().editDamage(
                source.getEntity(),
                living,
                source,
                damage
        );

        if (optionalPair.isPresent()) {
            Pair<DamageSource, Float> pair = optionalPair.get();
            return original.call(level, pair.getFirst(), pair.getSecond());
        }
        return original.call(level, source, damage);
    }
}
