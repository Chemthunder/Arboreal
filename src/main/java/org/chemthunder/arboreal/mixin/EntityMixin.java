package org.chemthunder.arboreal.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.chemthunder.arboreal.api.event.server.WorldInteractionCallbacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = Entity.class)
public abstract class EntityMixin {
    @Inject(method = "onKilledOther", at = @At(value = "HEAD"))
    private void arboreal$WorldInteractionCallbacks$OnKilledOther(ServerWorld world, LivingEntity other, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;
        WorldInteractionCallbacks.ON_KILL_OTHER.invoker().onKillOther(self, other);
    }
}
