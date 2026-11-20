package org.chemthunder.arboreal_test;

import net.fabricmc.api.ModInitializer;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
public class ArborealTest implements ModInitializer {
    public static final String MOD_ID = "arboreal_test";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Arboreal MAIN = new Arboreal(MOD_ID);

    public void onInitialize() {
        LOGGER.info("arboreal test initialized");

        MAIN.initMain();
    }
}
