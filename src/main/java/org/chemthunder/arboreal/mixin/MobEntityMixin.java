package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.chemthunder.arboreal.api.event.server.MobEntityGetTargetCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = Mob.class)
public abstract class MobEntityMixin {
    @ModifyReturnValue(method = "getTarget", at = @At(value = "RETURN"))
    private LivingEntity arboreal$MobEntityGetTargetCallback(LivingEntity original) {
        return MobEntityGetTargetCallback.EVENT.invoker().getTarget((Mob) (Object) this, original);
    }
}
