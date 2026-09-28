package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import org.chemthunder.arboreal.api.item.CustomConsumeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ConsumableComponent.class)
public abstract class ConsumableComponentMixin {
    @WrapOperation(
            method = "spawnParticlesAndPlaySound",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"
            )
    )
    private void arboreal$CustomConsumeItem(LivingEntity instance, SoundEvent soundEvent, float v, float f, Operation<Void> original) {
        original.call(
                instance,
                instance.getStackInHand(instance.getActiveHand()).getItem() instanceof CustomConsumeItem c
                        ? c.getConsumeEatSound(instance)
                        : soundEvent,
                v,
                f
        );
    }
}