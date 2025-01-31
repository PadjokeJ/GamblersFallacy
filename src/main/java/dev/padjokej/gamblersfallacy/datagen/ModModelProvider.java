package dev.padjokej.gamblersfallacy.datagen;

import dev.padjokej.gamblersfallacy.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(ModItems.CHIP, Models.GENERATED);
        //itemModelGenerator.register(ModItems.GAMBLING_CHIP, Models.GENERATED);
        itemModelGenerator.register(ModItems.GAMBLITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.GAMBLITE_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.GAMBLITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.GAMBLITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.GAMBLITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.GAMBLITE_BOOTS);
    }
}
