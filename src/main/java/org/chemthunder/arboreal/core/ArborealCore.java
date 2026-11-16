package org.chemthunder.arboreal.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.chemthunder.arboreal.core.networking.ArborealPacketNetwork;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
@ApiStatus.Internal
public class ArborealCore implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "arboreal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Arboreal MAIN = new Arboreal(MOD_ID);

	public void onInitialize() {
		LOGGER.info("Initializing Arboreal internal.");

		MAIN.addHandler(ArborealPacketNetwork::new);

		MAIN.initMain();
	}

	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		MAIN.initClient();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
