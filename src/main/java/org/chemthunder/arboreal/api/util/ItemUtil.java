package org.chemthunder.arboreal.api.util;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Chemthunder
 */
@ApiStatus.NonExtendable
@SuppressWarnings("unused")
public abstract class ItemUtil {
    public static <T> ItemStack createStackWithComponent(ItemLike item, DataComponentType<T> component, T value) {
        ItemStack stack = new ItemStack(item);
        stack.set(component, value);
        return stack;
    }

    public static ResourceKey<Item> key(ItemLike ItemLike) {
        Item item = ItemLike.asItem();
        Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
        return ResourceKey.create(Registries.ITEM, itemId);
    }

    public static ItemAttributeModifiers createBasicAttributes(float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                attackDamage,
                                AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    public static int createItemBarStep(int progress, int maxValue) {
        return Math.clamp(Math.round((float) progress / maxValue * 13), 0, 13);
    }

    /**
     * @author AcoYT
     * From AcornLib by AcoYT
     * <a href="https://modrinth.com/mod/acornlib">AcornLib</a>
     */
    public static String formatString(String input) {
        StringBuilder outputBuilder = new StringBuilder();

        boolean expecting = false;
        for (int i = 0; i < input.length(); i++) {
            char indexChar = input.toCharArray()[i];

            if (i == 0) {
                indexChar = Character.toUpperCase(indexChar);
            }

            if (indexChar == '_') {
                indexChar = ' ';
                expecting = true;
            }

            if (expecting) {
                indexChar = Character.toUpperCase(indexChar);
                expecting = false;
            }

            outputBuilder.append(indexChar);
        }

        return outputBuilder.toString();
    }
}
