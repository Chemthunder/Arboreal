package org.chemthunder.arboreal.core.impl.data;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.data.resources.client.TranslationSupplier;

/**
 * @author Chemthunder
 */
public class ArborealTranslations extends TranslationSupplier {
    public ArborealTranslations(Identifier id) {
        super(id);
    }

    public void supplyTexts(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {
        translationBuilder.add("misc.text.arboreal", "Happy funtimes!!!");
    }
}
