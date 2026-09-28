package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import net.minecraft.world.waypoint.ServerWaypoint;
import org.chemthunder.arboreal.api.event.server.WorldInteractionCallbacks;
import org.chemthunder.arboreal.api.item.CustomConsumeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, ServerWaypoint {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @WrapOperation(
            method = "spawnItemParticles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
            )
    )
    private void arboreal$CustomConsumeItem(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Operation<Void> original) {
        LivingEntity living = (LivingEntity) (Object)this;

        if (living.getStackInHand(living.getActiveHand()).getItem() instanceof CustomConsumeItem c) {
            original.call(instance, c.getConsumeParticleEffect(living), x, y, z, velocityX, velocityY, velocityZ);
            return;
        }
        original.call(instance, parameters, x, y, z, velocityX, velocityY, velocityZ);
    }

    @WrapMethod(method = "damage")
    private boolean arboreal$WorldInteractionCallbacks$EditDamage(ServerWorld world, DamageSource source, float amount, Operation<Boolean> original) {
        LivingEntity living = (LivingEntity) (Object) this;

        Optional<Pair<RegistryKey<DamageType>, Float>> optionalPair = WorldInteractionCallbacks.EDIT_DAMAGE.invoker().editDamage(
                source.getAttacker(),
                living,
                source,
                amount
        );

        if (optionalPair.isPresent()) {
            Pair<RegistryKey<DamageType>, Float> pair = optionalPair.get();
            return original.call(world, this.getDamageSources().create(pair.getLeft()), pair.getRight());
        }
        return original.call(world, source, amount);
    }
}
