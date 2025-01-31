package dev.padjokej.gamblersfallacy.datagen;

import dev.padjokej.gamblersfallacy.block.ModBlocks;
import dev.padjokej.gamblersfallacy.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SLOT_MACHINE_BLOCK)
                .pattern("P ")
                .pattern("CL")
                .pattern("S ")
                .input('P', Items.SPRUCE_PLANKS)
                .input('S', Items.SPRUCE_SLAB)
                .input('C', ModItems.CHIP)
                .input('L', Items.LEVER)
                .criterion(hasItem(ModItems.CHIP), conditionsFromItem(ModItems.CHIP))
                .offerTo(recipeExporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DICE)
                .input(Items.INK_SAC)
                .input(Items.GLASS)
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(recipeExporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHIP, 4)
                .input(Items.IRON_NUGGET)
                .input(Items.IRON_NUGGET)
                .input(Items.GOLD_NUGGET)
                .input(Items.GOLD_NUGGET)
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                .offerTo(recipeExporter);

        offerSmithingTemplateCopyingRecipe(recipeExporter, ModItems.GAMBLITE_SMITHING_TEMPLATE, ModItems.CHIP);
    }
}
