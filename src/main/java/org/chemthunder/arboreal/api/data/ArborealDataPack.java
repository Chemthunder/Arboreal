package org.chemthunder.arboreal.api.data;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import org.apache.commons.lang3.function.TriFunction;
import org.chemthunder.arboreal.api.data.server.ArborealTagWidener;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ArborealDataPack {
    private final FabricDataGenerator.Pack datapack;

    public ArborealDataPack(FabricDataGenerator.Pack pack) {
        this.datapack = pack;
    }

    public static ArborealDataPack create(FabricDataGenerator fdg) {
        return new ArborealDataPack(fdg.createPack());
    }

    public ArborealDataPack translations(String code, BiConsumer<HolderLookup.Provider, FabricLanguageProvider.TranslationBuilder> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricLanguageProvider(output, code, registriesFuture) {
            public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
                configuration.accept(registryLookup, translationBuilder);
            }
        }));
        return this;
    }

    public ArborealDataPack translations(BiConsumer<HolderLookup.Provider, FabricLanguageProvider.TranslationBuilder> translations) {
        return this.translations("en_us", translations);
    }

    public ArborealDataPack models(Consumer<ItemModelGenerators> itemModels, Consumer<BlockModelGenerators> blockStates) {
        this.datapack.addProvider((output, registriesFuture) -> new FabricModelProvider(output) {
            public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
                blockStates.accept(blockStateModelGenerator);
            }

            public void generateItemModels(ItemModelGenerators itemModelGenerator) {
                itemModels.accept(itemModelGenerator);
            }
        });
        return this;
    }

    public ArborealDataPack models(Consumer<ItemModelGenerators> itemModels) {
        return this.models(itemModels, (_) -> {});
    }

    public <T> ArborealDataPack tags(ResourceKey<? extends Registry<T>> registryKey, BiConsumer<HolderLookup.Provider, ArborealTagWidener<T>> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new ArborealTagWidener<T>(output, registryKey, registriesFuture) {
            protected void addTags(HolderLookup.Provider registries) {
                configuration.accept(registries, this);
            }
        }));
        return this;
    }

    public ArborealDataPack dynamics(BiConsumer<HolderLookup.Provider, FabricDynamicRegistryProvider.Entries> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricDynamicRegistryProvider(output, registriesFuture) {
            protected void configure(HolderLookup.Provider registries, FabricDynamicRegistryProvider.Entries entries) {
                configuration.accept(registries, entries);
            }

            public String getName() {
                return "Dynamic Registries";
            }
        }));
        return this;
    }

    public ArborealDataPack blockLootTables(Consumer<BlockLootSubProvider> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricBlockLootSubProvider(output, registriesFuture) {
            public void generate() {
                configuration.accept(this);
            }
        }));
        return this;
    }

    public <T> ArborealDataPack codec(PackOutput.Target outputType, String directoryName, Codec<T> codec, BiConsumer<BiConsumer<Identifier, T>, HolderLookup.Provider> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricCodecDataProvider<T>(output, registriesFuture, outputType, directoryName, codec) {
            public String getName() {
                return "Codec Data";
            }

            protected void configure(BiConsumer<Identifier, T> provider, HolderLookup.Provider registryLookup) {
                configuration.accept(provider, registryLookup);
            }
        }));
        return this;
    }

    public ArborealDataPack entityLootTables(Consumer<EntityLootSubProvider> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricEntityLootSubProvider(output, registriesFuture) {
            public void generate() {
                configuration.accept(this);
            }
        }));
        return this;
    }

    public ArborealDataPack recipes(TriFunction<HolderLookup.Provider, BootstrapContext<Recipe<?>>, BootstrapContext<Advancement>, RecipeProvider> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricRecipeProvider(output, registriesFuture) {
            protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
                return configuration.apply(registries, recipes, advancements);
            }
        }));
        return this;
    }

    public ArborealDataPack sounds(BiConsumer<HolderLookup.Provider, FabricSoundsProvider.SoundExporter> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricSoundsProvider(output, registriesFuture) {
            protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
                configuration.accept(registryLookup, exporter);
            }

            public String getName() {
                return "Sounds";
            }
        }));
        return this;
    }

    public ArborealDataPack advancements(BiConsumer<HolderLookup.Provider, Consumer<AdvancementHolder>> configuration) {
        this.datapack.addProvider(((output, registriesFuture) -> new FabricAdvancementProvider(output, registriesFuture) {
            public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
                configuration.accept(registryLookup, consumer);
            }
        }));
        return this;
    }
}