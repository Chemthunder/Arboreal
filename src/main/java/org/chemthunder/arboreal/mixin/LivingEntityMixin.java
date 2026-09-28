package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import org.chemthunder.arboreal.api.item.CustomConsumeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {
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
}
