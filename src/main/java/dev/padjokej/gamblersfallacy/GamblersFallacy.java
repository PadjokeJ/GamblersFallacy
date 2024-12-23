package dev.padjokej.gamblersfallacy;

import dev.padjokej.gamblersfallacy.block.ModBlocks;
import dev.padjokej.gamblersfallacy.block.entity.ModBlockEntities;
import dev.padjokej.gamblersfallacy.component.ModCCAComponents;
import dev.padjokej.gamblersfallacy.component.ModDataComponentTypes;
import dev.padjokej.gamblersfallacy.items.ModItemGroup;
import dev.padjokej.gamblersfallacy.items.ModItems;
import dev.padjokej.gamblersfallacy.items.ModWeapons;
import dev.padjokej.gamblersfallacy.stats.Stats;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamblersFallacy implements ModInitializer {
	public static final String MOD_ID = "gamblers_fallacy";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModWeapons.registerModWeapons();
		ModDataComponentTypes.registerDataComponentTypes();

		ModBlockEntities.registerModBlockEntities();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroup.registerItemGroups();
		ModDataComponentTypes.registerDataComponentTypes();
		ModCCAComponents.registerCCAComponents();
		Stats.registerModStats();

		LOGGER.info("Hello Fabric world!");
	}


}