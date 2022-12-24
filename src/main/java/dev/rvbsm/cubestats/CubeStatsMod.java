package dev.rvbsm.cubestats;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CubeStatsMod implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cubestats");

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized mod");
    }
}
