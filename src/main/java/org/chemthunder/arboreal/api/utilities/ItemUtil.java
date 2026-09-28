package org.chemthunder.arboreal.api.utilities;

import net.minecraft.component.ComponentType;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
@ApiStatus.NonExtendable
@SuppressWarnings("unused")
public abstract class ItemUtil {
    public static <T> ItemStack createStackWithComponent(ItemConvertible item, ComponentType<T> component, T value) {
        ItemStack stack = new ItemStack(item);
        stack.set(component, value);
        return stack;
    }

    public static RegistryKey<Item> key(ItemConvertible itemConvertible) {
        Item item = itemConvertible.asItem();
        Identifier itemId = Registries.ITEM.getId(item);
        return RegistryKey.of(RegistryKeys.ITEM, itemId);
    }

    public static RegistryKey<Item> key(Identifier itemId) {
        return RegistryKey.of(RegistryKeys.ITEM, itemId);
    }

    public static AttributeModifiersComponent createBasicAttributes(float attackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                attackDamage,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    // I already know Aco is gonna scream at me if I try doing this
//    public static String formatString(String string) {
//        List<Character> characters = new ArrayList<>();
//
//        for (char character : string.toCharArray()) {
//            characters.add(character);
//        }
//
//        return characters;
//    }

    public static List<Item> getHotbarItems(LivingEntity living) {
        List<Item> stacks = new ArrayList<>();

        if (living instanceof PlayerEntity player) {
            for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
                stacks.add(player.getInventory().getStack(i).getItem());
            }
        } else {
            stacks.add(living.getMainHandStack().getItem());
            stacks.add(living.getOffHandStack().getItem());
        }

        return stacks;
    }

    public static int createItemBarStep(int progress, int maxValue) {
        return Math.clamp(Math.round((float) progress / maxValue * 13), 0, 13);
    }
}
