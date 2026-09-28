package org.chemthunder.arboreal.core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.util.Identifier;
import org.chemthunder.arboreal.api.Arboreal;
import org.chemthunder.arboreal.api.event.server.WorldInteractionCallbacks;
import org.chemthunder.arboreal.api.networking.PacketNetwork;
import org.chemthunder.arboreal.core.event.EditEvent;
import org.chemthunder.arboreal.core.index.ArborealAttachmentTypes;
import org.chemthunder.arboreal.core.index.ArborealItems;
import org.chemthunder.arboreal.core.index.ArborealTrackedDataHandlers;
import org.chemthunder.arboreal.core.networking.ArborealPacketNetwork;
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

		ArborealTrackedDataHandlers.init();
		ArborealAttachmentTypes.init();

		ArborealItems.init();

		PacketNetwork.registerNetwork(new ArborealPacketNetwork());

		NETWORKS.forEach(packetNetwork -> {
			packetNetwork.registerTypes();
			packetNetwork.client2server();
		});

		WorldInteractionCallbacks.EDIT_DAMAGE.register(new EditEvent());
	}

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
