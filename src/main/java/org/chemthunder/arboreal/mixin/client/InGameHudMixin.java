package org.chemthunder.arboreal.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.event.client.ApplyCustomHeartsCallback;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(value = InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow @Nullable protected abstract PlayerEntity getCameraPlayer();

    @WrapOperation(
            method = "drawHeart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud$HeartType;getTexture(ZZZ)Lnet/minecraft/util/Identifier;"
            )
    )
    private Identifier arboreal$ApplyCustomHeartsCallback(InGameHud.HeartType instance, boolean hardcore, boolean half, boolean blinking, Operation<Identifier> original) {
        Optional<Identifier> callback = ApplyCustomHeartsCallback.EVENT.invoker().getHeartIdentifier(
                MinecraftClient.getInstance(),
                this.getCameraPlayer()
        );

        if (callback.isPresent()) {
            Identifier id = callback.get();

            if (instance != InGameHud.HeartType.CONTAINER && instance != InGameHud.HeartType.ABSORBING && instance != InGameHud.HeartType.POISONED && instance != InGameHud.HeartType.WITHERED) {
                return Identifier.of(id.getNamespace(), id.getPath()
                        + (half ? "_half" : "_full")
                        + (blinking ? "blinking" : "")
                );
            }
        }
        return original.call(instance, hardcore, half, blinking);
    }
}