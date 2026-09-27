package org.chemthunder.arboreal.core.impl.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.chemthunder.arboreal.api.item.Tooltip;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class DebugItem extends Item implements Tooltip {
    public DebugItem(Settings settings) {
        super(settings);
    }

    public void applyTooltip(ItemStack instance, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.literal("bingus"));
    }
}
