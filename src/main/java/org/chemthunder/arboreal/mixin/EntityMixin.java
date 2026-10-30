package org.chemthunder.arboreal.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.chemthunder.arboreal.api.event.server.WorldInteractionCallbacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = net.minecraft.world.entity.Entity.class)
public abstract class EntityMixin {
    @Inject(method = "killedEntity", at = @At(value = "HEAD"))
    private void arboreal$WorldInteractionCallbacks$OnKilledOther(ServerLevel level, LivingEntity entity, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;
        WorldInteractionCallbacks.ON_KILL_OTHER.invoker().onKillOther(self, entity);
    }
}
