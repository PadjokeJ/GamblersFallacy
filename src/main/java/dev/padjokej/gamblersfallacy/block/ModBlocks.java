package dev.padjokej.gamblersfallacy.block;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import dev.padjokej.gamblersfallacy.block.slot_machine.SlotMachineBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block SLOT_MACHINE_BLOCK = registerBlock("slot_machine_block",
            new SlotMachineBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)
                    .strength(1f).nonOpaque()));
    private static Block registerBlock(String name, Block block)
    {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(GamblersFallacy.MOD_ID, name), block);
    }
    private static Item registerBlockItems(String name, Block block)
    {
        return Registry.register(Registries.ITEM, Identifier.of(GamblersFallacy.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks()
    {
        GamblersFallacy.LOGGER.debug("Registering mod blocks for " + GamblersFallacy.MOD_ID);
    }
}
