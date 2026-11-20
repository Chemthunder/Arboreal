package org.chemthunder.arboreal_test;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class ArborealTestClient implements ClientModInitializer {
    public void onInitializeClient() {
        ArborealTest.MAIN.initClient();
    }
}
