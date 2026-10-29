package org.chemthunder.arboreal.api;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.data.DataOutput;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.data.client.ModelUploader;
import org.chemthunder.arboreal.api.data.server.ArborealTagWidener;
import org.chemthunder.arboreal.api.registration.DataIndex;
import org.chemthunder.arboreal.api.registration.Index;
import org.chemthunder.arboreal.core.ArborealCore;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public record Arboreal(String modId) {
    public <T> Index<T> createIndex(Registry<T> registry) {
        return new Index<>(registry, modId);
    }

    public <T> DataIndex<T> createDataIndex(RegistryKey<Registry<T>> registry) {
        return new DataIndex<>(registry, modId);
    }

    public DataPack createDataPack(FabricDataGenerator fdg) {
        return DataPack.create(fdg);
    }

    public Identifier id(String path) {
        return Identifier.of(modId, path);
    }

    public void mapModelUploader(ModelUploader uploader, Item target) {
        ArborealCore.UPLOADERS.put(uploader, target);
    }

    public void registerDataIndex(DataIndex<?> index) {
        ArborealCore.TO_REGISTER.add(index);
    }

    public static class DataPack {
        private final FabricDataGenerator.Pack datapack;

        public DataPack(FabricDataGenerator.Pack pack) {
            this.datapack = pack;
        }

        public static DataPack create(FabricDataGenerator fdg) {
            return new DataPack(fdg.createPack());
        }

        public DataPack translations(String code, BiConsumer<RegistryWrapper.WrapperLookup, FabricLanguageProvider.TranslationBuilder> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricLanguageProvider(output, code, registriesFuture) {
                public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
                    configuration.accept(registryLookup, translationBuilder);
                }
            }));
            return this;
        }

        public DataPack translations(BiConsumer<RegistryWrapper.WrapperLookup, FabricLanguageProvider.TranslationBuilder> translations) {
            return this.translations("en_us", translations);
        }

        public DataPack models(Consumer<ItemModelGenerator> itemModels, Consumer<BlockStateModelGenerator> blockStates) {
            this.datapack.addProvider((output, registriesFuture) -> new FabricModelProvider(output) {
                public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
                    blockStates.accept(blockStateModelGenerator);
                }

                public void generateItemModels(ItemModelGenerator itemModelGenerator) {
                    itemModels.accept(itemModelGenerator);
                }
            });
            return this;
        }

        public DataPack models(Consumer<ItemModelGenerator> itemModels) {
            return this.models(itemModels, (blockStateModelGenerator) -> {});
        }

        public <T> DataPack tags(RegistryKey<? extends Registry<T>> registryKey, BiConsumer<RegistryWrapper.WrapperLookup, ArborealTagWidener<T>> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new ArborealTagWidener<T>(output, registryKey, registriesFuture) {
                protected void configure(RegistryWrapper.WrapperLookup registries) {
                    configuration.accept(registries, this);
                }
            }));
            return this;
        }

        public DataPack dynamics(BiConsumer<RegistryWrapper.WrapperLookup, FabricDynamicRegistryProvider.Entries> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricDynamicRegistryProvider(output, registriesFuture) {
                protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
                    configuration.accept(registries, entries);
                }

                public String getName() {
                    return "Dynamic Registries";
                }
            }));
            return this;
        }

        public DataPack blockLootTables(Consumer<FabricBlockLootTableProvider> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricBlockLootTableProvider(output, registriesFuture) {
                public void generate() {
                    configuration.accept(this);
                }
            }));
            return this;
        }

        public <T> DataPack codec(DataOutput.OutputType outputType, String directoryName, Codec<T> codec, BiConsumer<BiConsumer<Identifier, T>, RegistryWrapper.WrapperLookup> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricCodecDataProvider<T>(
                    output,
                    registriesFuture,
                    outputType,
                    directoryName,
                    codec
            ) {
                protected void configure(BiConsumer<Identifier, T> provider, RegistryWrapper.WrapperLookup lookup) {
                    configuration.accept(provider, lookup);
                }

                public String getName() {
                    return directoryName + " Codec Data";
                }
            }));
            return this;
        }

        public DataPack entityLootTables(Consumer<FabricEntityLootTableProvider> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricEntityLootTableProvider(output, registriesFuture) {
                public void generate() {
                    configuration.accept(this);
                }
            }));
            return this;
        }

        public DataPack recipes(BiFunction<RegistryWrapper.WrapperLookup, RecipeExporter, RecipeGenerator> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricRecipeProvider(output, registriesFuture) {
                protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
                    return configuration.apply(registries, exporter);
                }

                public String getName() {
                    return "";
                }
            }));
            return this;
        }

        public DataPack sounds(BiConsumer<RegistryWrapper.WrapperLookup, FabricSoundsProvider.SoundExporter> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricSoundsProvider(output, registriesFuture) {
                protected void configure(RegistryWrapper.WrapperLookup registryLookup, SoundExporter exporter) {
                    configuration.accept(registryLookup, exporter);
                }

                public String getName() {
                    return "Sounds";
                }
            }));
            return this;
        }

        public DataPack advancements(BiConsumer<RegistryWrapper.WrapperLookup, Consumer<AdvancementEntry>> configuration) {
            this.datapack.addProvider(((output, registriesFuture) -> new FabricAdvancementProvider(output, registriesFuture) {
                public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
                    configuration.accept(registryLookup, consumer);
                }
            }));
            return this;
        }
    }
}
