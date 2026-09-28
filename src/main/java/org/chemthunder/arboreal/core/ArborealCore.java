package org.chemthunder.arboreal.core;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;

import org.chemthunder.arboreal.api.Arboreal;
import org.chemthunder.arboreal.api.data.DataInfrastructure;
import org.chemthunder.arboreal.api.impl.ArborealAttachmentTypes;
import org.chemthunder.arboreal.api.impl.ArborealTrackedDataHandlers;
import org.chemthunder.arboreal.api.networking.PacketNetwork;
import org.chemthunder.arboreal.core.impl.data.ArborealItemTags;
import org.chemthunder.arboreal.core.impl.data.ArborealTranslations;
import org.chemthunder.arboreal.core.impl.index.ArborealItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class ArborealCore implements ModInitializer, DataGeneratorEntrypoint {
	public static final String MOD_ID = "arboreal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Arboreal MAIN = new Arboreal(MOD_ID);

	public static final List<PacketNetwork> NETWORKS = new ArrayList<>();

	public void onInitialize() {
		LOGGER.info("Initializing Arboreal internal.");

		ArborealItems.init();

		ArborealTrackedDataHandlers.init();
		ArborealAttachmentTypes.init();

		NETWORKS.forEach(packetNetwork -> {
			packetNetwork.registerTypes();
			packetNetwork.client2server();
		});
	}

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		DataInfrastructure infrastructure = new DataInfrastructure()
				.instantiateSupport(new ArborealItemTags(id("tags")))
				.instantiateSupport(new ArborealTranslations(id("translations")));

		infrastructure.seal(fabricDataGenerator);
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
