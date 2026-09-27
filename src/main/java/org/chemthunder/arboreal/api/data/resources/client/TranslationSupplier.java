package org.chemthunder.arboreal.api.data.resources.client;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.data.resources.DataHook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class TranslationSupplier extends DataHook {
    public TranslationSupplier(Identifier id) {
        super(id);
    }

    public void generate(FabricDataGenerator generator, FabricDataGenerator.Pack pack) {
        class Translations extends FabricLanguageProvider {
            public Translations(
                    FabricDataOutput dataOutput,
                    CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup
            ) {
                super(dataOutput, registryLookup);
            }

            public void generateTranslations(
                    RegistryWrapper.WrapperLookup registryLookup,
                    TranslationBuilder translationBuilder
            ) {
                TranslationSupplier.this.supplyItems(registryLookup, translationBuilder);
                TranslationSupplier.this.supplyBlocks(registryLookup, translationBuilder);
                TranslationSupplier.this.supplyEntityTypes(registryLookup, translationBuilder);

                translationBuilder.add("arboreal.unused.spacer", "MISC TEXT");

                TranslationSupplier.this.supplyAdvancements(registryLookup, translationBuilder);
                TranslationSupplier.this.supplyTexts(registryLookup, translationBuilder);
            }
        }

        pack.addProvider(Translations::new);
    }

    public String getDataType() {
        return "translations";
    }

    public void supplyItems(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {}
    public void supplyBlocks(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {}
    public void supplyEntityTypes(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {}

    public void supplyAdvancements(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {}
    public void supplyTexts(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {}
}
