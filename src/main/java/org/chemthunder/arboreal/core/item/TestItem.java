package org.chemthunder.arboreal.core.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.chemthunder.arboreal.api.item.Tooltip;
import org.chemthunder.arboreal.api.util.WorldUtil;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class TestItem extends Item implements Tooltip {
    public TestItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        WorldUtil.playClientSound(SoundEvents.BLOCK_SAND_PLACE, 1, 1, user);
        return super.use(world, user, hand);
    }

    public void applyTooltip(ItemStack instance, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.literal("ATTACKING_VERTICAL").formatted(Formatting.DARK_GRAY));
    }
}
