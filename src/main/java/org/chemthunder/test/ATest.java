package org.chemthunder.test;

import net.fabricmc.api.ModInitializer;
import org.chemthunder.arboreal.api.core.Arboreal;
import org.chemthunder.test.index.TestDamageTypes;
import org.chemthunder.test.index.TestItems;
import org.chemthunder.test.index.TestNetworking;

/**
 * @author Chemthunder
 */
public class ATest implements ModInitializer {
    public static final String MOD_ID = "a_test";

    public static final Arboreal MAIN = new Arboreal(MOD_ID);

    public void onInitialize() {
        MAIN.addHandler(TestItems.TASK);
        MAIN.addHandler(TestDamageTypes.TASK);

        MAIN.addHandler(TestNetworking::new);

        MAIN.initMain();
    }
}
