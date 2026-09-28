package org.chemthunder.arboreal.core.index;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import org.chemthunder.arboreal.core.ArborealCore;

import java.util.List;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface ArborealTrackedDataHandlers {
    TrackedDataHandler<List<ItemStack>> ITEM_STACK_LIST = TrackedDataHandler.create(ItemStack.OPTIONAL_LIST_PACKET_CODEC);
    TrackedDataHandler<RegistryKey<DamageType>> DAMAGE_TYPE_KEY = TrackedDataHandler.create(ArborealMiscCodecs.DAMAGE_TYPE_REGISTRY_KEY_PACKET_CODEC);
    TrackedDataHandler<ParticleEffect> PARTICLE_EFFECT = TrackedDataHandler.create(ParticleTypes.PACKET_CODEC);
    TrackedDataHandler<byte[]> BYTE_LIST = TrackedDataHandler.create(PacketCodecs.BYTE_ARRAY);
    TrackedDataHandler<List<String>> STRING_LIST = TrackedDataHandler.create(ArborealMiscCodecs.STRING_LIST);

    static void init() {
        register("item_stack_list", ITEM_STACK_LIST);
        register("damage_type_registry_key", DAMAGE_TYPE_KEY);
        register("particle_effect", PARTICLE_EFFECT);
        register("byte_list", BYTE_LIST);
        register("string_list", STRING_LIST);
    }

    private static <T> void register(String name, TrackedDataHandler<T> handler) {
        FabricTrackedDataRegistry.register(ArborealCore.id(name), handler);
    }
}
