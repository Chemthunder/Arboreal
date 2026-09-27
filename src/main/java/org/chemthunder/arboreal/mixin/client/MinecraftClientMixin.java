package org.chemthunder.arboreal.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.chemthunder.arboreal.api.event.client.ShouldRenderOutlineCallback;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @WrapMethod(method = "hasOutline")
    private boolean arboreal$ShouldRenderOutlineCallback(Entity entity, Operation<Boolean> original) {
        return original.call(entity) || ShouldRenderOutlineCallback.EVENT.invoker().shouldHaveOutline(entity);
    }
}
