package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.chemthunder.arboreal.api.event.server.MobEntityGetTargetCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = MobEntity.class)
public abstract class MobEntityMixin {
//    @ModifyReturnValue(method = "getTarget", at = @At(value = "RETURN"))
//    private LivingEntity arboreal$mobEntityGetTargetCallback(LivingEntity target) {
//        return MobEntityGetTargetCallback.EVENT.invoker().getTarget((MobEntity) (Object) this, target) || target;
//    }
//
//    @WrapMethod(method = "getTarget")
//    private LivingEntity arboreal$callback(Operation<LivingEntity> original) {
//
//        return original.call() || MobEntityGetTargetCallback.EVENT.invoker().getTarget((MobEntity) (Object) this, original.call());
//    }
}
