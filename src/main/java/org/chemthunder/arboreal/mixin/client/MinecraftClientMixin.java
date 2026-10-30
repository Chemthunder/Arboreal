package org.chemthunder.arboreal.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.chemthunder.arboreal.api.event.client.LeftClickCallback;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
@Mixin(value = Minecraft.class)
public abstract class MinecraftClientMixin {
    @Shadow @Final public Options options;

    @WrapOperation(
            method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;continueAttack(Z)V"
            )
    )
    private void arboreal$LeftClickCallback(Minecraft instance, boolean down, Operation<Void> original) {
        if (this.options.keyAttack.isDown()) {
            LeftClickCallback.EVENT.invoker().onLeftClick((Minecraft) (Object) this);
        }
        original.call(instance, down);
    }
}
