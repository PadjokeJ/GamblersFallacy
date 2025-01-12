package dev.padjokej.gamblersfallacy.items;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import dev.padjokej.gamblersfallacy.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup GAMBLERS_FALLACY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(GamblersFallacy.MOD_ID, "gambling_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.GAMBLING_CHIP))
                    .displayName(Text.translatable("itemgroup.gamblers_fallacy.gambling_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CHIP);
                        entries.add(ModItems.GAMBLING_CHIP);
                        entries.add(ModBlocks.SLOT_MACHINE_BLOCK);
                        entries.add(ModWeapons.GAMBLING_WEAPON);
                        entries.add(ModItems.DICE);
                        entries.add(ModItems.GAMBLITE);
                        entries.add(ModItems.GAMBLITE_SMITHING_TEMPLATE);
                        entries.add(ModItems.GAMBLITE_BOOTS);
                        entries.add(ModItems.GAMBLITE_LEGGINGS);
                        entries.add(ModItems.GAMBLITE_CHESTPLATE);
                        entries.add(ModItems.GAMBLITE_HELMET);
                    }).build());
    public static void registerItemGroups() {
        GamblersFallacy.LOGGER.info("Registering Item Groups for " + GamblersFallacy.MOD_ID);
    }
}
