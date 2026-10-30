package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.component.Consumable;
import org.chemthunder.arboreal.api.item.CustomConsumeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Consumable.class)
public abstract class ConsumableComponentMixin {
    @WrapOperation(
            method = "emitParticlesAndSounds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"
            )
    )
    private void arboreal$CustomConsumeItem(LivingEntity instance, SoundEvent soundEvent, float vol, float pi, Operation<Void> original) {
        original.call(
                instance,
                instance.getItemInHand(instance.getUsedItemHand()).getItem() instanceof CustomConsumeItem c
                        ? c.getConsumeEatSound(instance)
                        : soundEvent,
                vol,
                pi
        );
    }
}