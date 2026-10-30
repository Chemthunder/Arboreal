package org.chemthunder.arboreal.core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.chemthunder.arboreal.api.data.client.ModelUploader;
import org.chemthunder.arboreal.api.networking.PacketNetwork;
import org.chemthunder.arboreal.api.registration.DataIndex;
import org.chemthunder.arboreal.core.networking.ArborealPacketNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chemthunder
 */
public class ArborealCore implements ModInitializer, DataGeneratorEntrypoint {
	public static final String MOD_ID = "arboreal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Map<ModelUploader, Item> UPLOADERS = new HashMap<>();
	public static final List<PacketNetwork> NETWORKS = new ArrayList<>();
	public static final List<DataIndex<?>> TO_REGISTER = new ArrayList<>();

	public void onInitialize() {
		LOGGER.info("Initializing Arboreal internal.");

		PacketNetwork.registerNetwork(new ArborealPacketNetwork());

		NETWORKS.forEach(packetNetwork -> {
			packetNetwork.registerTypes();
			packetNetwork.client2server();
		});
	}

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ArborealCoreData.Models::new);
		pack.addProvider(ArborealCoreData.Dynamics::new);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
