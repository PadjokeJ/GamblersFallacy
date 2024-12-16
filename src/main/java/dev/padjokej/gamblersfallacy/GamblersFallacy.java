package dev.padjokej.gamblersfallacy;

import dev.padjokej.gamblersfallacy.block.ModBlocks;
import dev.padjokej.gamblersfallacy.block.entity.ModBlockEntities;
import dev.padjokej.gamblersfallacy.component.ModDataComponentTypes;
import dev.padjokej.gamblersfallacy.items.ModWeapons;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamblersFallacy implements ModInitializer {
	public static final String MOD_ID = "gamblers_fallacy";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModWeapons.registerModWeapons();
		ModDataComponentTypes.registerDataComponentTypes();

		ModBlockEntities.registerModBlockEntities();
		ModBlocks.registerModBlocks();


		LOGGER.info("Hello Fabric world!");
	}


}