package dev.padjokej.gamblersfallacy.block.entity;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import dev.padjokej.gamblersfallacy.block.ModBlocks;
import dev.padjokej.gamblersfallacy.block.slot_machine.SlotMachineBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<SlotMachineBlockEntity> SLOT_MACHINE_BLOCK_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(GamblersFallacy.MOD_ID, "slot_machine_block_entity"),
                    FabricBlockEntityTypeBuilder.create(SlotMachineBlockEntity::new,
                            ModBlocks.SLOT_MACHINE_BLOCK).build());

    public static void registerModBlockEntities(){
        GamblersFallacy.LOGGER.info("Registering block entities for " + GamblersFallacy.MOD_ID);
    }
}
