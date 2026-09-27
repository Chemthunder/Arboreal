package org.chemthunder.arboreal.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.chemthunder.arboreal.api.item.Tooltip;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@Mixin(value = ItemStack.class)
public abstract class ItemStackMixin {
    @WrapOperation(
            method = "appendTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;appendComponentTooltip(Lnet/minecraft/component/ComponentType;Lnet/minecraft/item/Item$TooltipContext;Lnet/minecraft/component/type/TooltipDisplayComponent;Ljava/util/function/Consumer;Lnet/minecraft/item/tooltip/TooltipType;)V",
                    ordinal = 0
            )
    )
    private <T> void arboreal$implTooltips(ItemStack instance, ComponentType<T> componentType, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type, Operation<Void> original) {
        if (instance.getItem() instanceof Tooltip tooltip) {
            tooltip.applyTooltip(instance, context, displayComponent, textConsumer, type);
        }
        original.call(instance, componentType, context, displayComponent, textConsumer, type);
    }
}
