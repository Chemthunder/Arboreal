//package org.chemthunder.arboreal.mixin.client;
//
//import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
//import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemStack;
//import org.apostasy.apostle.core.client.event.ItemGroupCyclingEvents;
//import org.apostasy.apostle.core.index.ApostleItemGroups;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//
///**
// * @author Chemthunder
// */
//@Mixin(value = CreativeInventoryScreen.class)
//public abstract class CreativeInventoryScreenMixin {
//    @WrapOperation(
//            method = "renderTabIcon",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/item/ItemGroup;getIcon()Lnet/minecraft/item/ItemStack;"
//            )
//    )
//    private ItemStack apostle$cycleItems(ItemGroup instance, Operation<ItemStack> original) {
//        if (instance == ApostleItemGroups.GROUP) {
//            Item cycledItem = ItemGroupCyclingEvents.fetch();
//            return cycledItem.getDefaultStack();
//        }
//        return original.call(instance);
//    }
//}