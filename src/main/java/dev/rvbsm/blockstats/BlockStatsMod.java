package dev.rvbsm.blockstats;

import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockStatsMod implements DedicatedServerModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitializeServer() {
		LOGGER.info("Initialized mod");
	}
}
