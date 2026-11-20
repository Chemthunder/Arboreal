package org.chemthunder.arboreal.api.core.task;

import com.mojang.serialization.Codec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.chemthunder.arboreal.api.core.ArborealTask;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Chemthunder
 */
@ApiStatus.Experimental
@SuppressWarnings({"unchecked", "unused"})
public class IndexTask implements ArborealTask {
    public final List<Indexed<?>> indexed = new ArrayList<>();

    public final Arboreal arboreal;

    public IndexTask(Arboreal arboreal) {
        this.arboreal = arboreal;
    }

    public <T> T register(String name, Registry<T> registry, T value) {
        T output = Registry.register(registry, ResourceKey.create(registry.key(), this.arboreal.id(name)), value);
        this.indexed.add(new Indexed<>(registry, output));
        return output;
    }

    public Item item(String name, Item item) {
        return register(name, BuiltInRegistries.ITEM, item);
    }

    public Item item(String name, Function<Item.Properties, Item> fn) {
        return item(name, fn.apply(new Item.Properties()));
    }

    public Block block(String name, Block block) {
        return register(name, BuiltInRegistries.BLOCK, block);
    }

    public Block blockWithItem(String name, Block block, Item.Properties properties) {
        item(name, new Item(properties));
        return block(name, block);
    }

    public Block blockWithItem(String name, Block block) {
        return blockWithItem(name, block, new Item.Properties());
    }

    public <T> DataComponentType<T> dataComponentType(String name, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return (DataComponentType<T>) register(name, BuiltInRegistries.DATA_COMPONENT_TYPE, DataComponentType.<T>builder()
                .persistent(codec)
                .networkSynchronized(streamCodec)
                .build()
        );
    }

    public <T> DataComponentType<T> dataComponentType(String name, Codec<T> codec) {
        return (DataComponentType<T>) register(name, BuiltInRegistries.DATA_COMPONENT_TYPE, DataComponentType.<T>builder()
                .persistent(codec)
                .build()
        );
    }

    public <T extends ParticleType<?>> T particleType(String name, T particleType) {
        return (T) register(name, BuiltInRegistries.PARTICLE_TYPE, particleType);
    }

    public SoundEvent soundEvent(String name) {
        return register(name, BuiltInRegistries.SOUND_EVENT, SoundEvent.createVariableRangeEvent(this.arboreal.id(name)));
    }

    public <T extends Entity> EntityType<T> entityType(String name, EntityType.Builder<T> builder) {
        return (EntityType<T>) register(name, BuiltInRegistries.ENTITY_TYPE, builder.build(ResourceKey.create(Registries.ENTITY_TYPE, this.arboreal.id(name))));
    }

    public void runMain() {}

    @Environment(EnvType.CLIENT)
    public void runClient() {}

    public record Indexed<T>(Registry<T> registry, T t) {}
}
