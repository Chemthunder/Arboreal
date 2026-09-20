package org.chemthunder.arboreal.api.utilities;

import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class ItemUtil {
    public static <T> ItemStack createStackWithComponent(ItemConvertible item, ComponentType<T> component, T value) {
        ItemStack stack = new ItemStack(item);
        stack.set(component, value);
        return stack;
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
}
