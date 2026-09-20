package org.chemthunder.arboreal.core;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;

import org.chemthunder.arboreal.api.Arboreal;
import org.chemthunder.arboreal.api.data.DataInfrastructure;
import org.chemthunder.arboreal.core.impl.data.ArborealTranslations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArborealCore implements ModInitializer, DataGeneratorEntrypoint {
	public static final String MOD_ID = "arboreal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Arboreal main = new Arboreal(MOD_ID);

	public void onInitialize() {
		LOGGER.info("Initializing Arboreal internal.");
	}

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		DataInfrastructure infrastructure = new DataInfrastructure()
				.instantiateSupport(new ArborealTranslations(id("translations")));

		infrastructure.seal(fabricDataGenerator);
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
