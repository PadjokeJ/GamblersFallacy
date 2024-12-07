package dev.padjokej.gamblersfallacy.items;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Objects;
import java.util.function.Supplier;

public enum ModMaterials implements ToolMaterial {
    GAMBLING_MATERIAL(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            1200, 9.0F, 4.0F, 0, () -> Ingredient.ofItems(Items.DIAMOND));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private ModMaterials(final TagKey inverseTag, final int itemDurability, final float miningSpeed,
                         final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }
    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }
    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }
    @Override
    public int getEnchantability() {
        return this.enchantability;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return (Ingredient) this.repairIngredient.get();
    }

}
