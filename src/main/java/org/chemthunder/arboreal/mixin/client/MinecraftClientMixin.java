package org.chemthunder.arboreal.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.Entity;
import org.chemthunder.arboreal.api.event.client.LeftClickCallback;
import org.chemthunder.arboreal.api.event.client.ShouldRenderOutlineCallback;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
@Mixin(value = MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Final public GameOptions options;

    @WrapMethod(method = "hasOutline")
    private boolean arboreal$ShouldRenderOutlineCallback(Entity entity, Operation<Boolean> original) {
        return original.call(entity) || ShouldRenderOutlineCallback.EVENT.invoker().shouldHaveOutline(entity);
    }

    @WrapOperation(
            method = "handleInputEvents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/MinecraftClient;handleBlockBreaking(Z)V"
            )
    )
    private void arboreal$LeftClickCallback(MinecraftClient instance, boolean breaking, Operation<Void> original) {
        if (this.options.attackKey.isPressed()) {
            LeftClickCallback.EVENT.invoker().onLeftClick((MinecraftClient) (Object) this);
        }
        original.call(instance, breaking);
    }
}
