package dev.padjokej.gamblersfallacy.items;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CHIP = registerItem("chip",
            new Item(new Item.Settings()));
    public static final Item GAMBLING_CHIP = registerItem("gambling_chip",
            new Item(new Item.Settings()));

    public static final Item DICE = registerItem("dice",
            new DiceItem(new Item.Settings()));
    public static final Item GAMBLITE = registerItem("gamblite",
            new Item(new Item.Settings()));

    public static final Item GAMBLITE_HELMET = registerItem("gamblite_helmet",
            new ModArmor(ArmorMaterials.GAMBLITE_ARMOR, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(16))));
    public static final Item GAMBLITE_CHESTPLATE = registerItem("gamblite_chestplate",
            new ModArmor(ArmorMaterials.GAMBLITE_ARMOR, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(16))));
    public static final Item GAMBLITE_LEGGINGS = registerItem("gamblite_leggings",
            new ModArmor(ArmorMaterials.GAMBLITE_ARMOR, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(16))));
    public static final Item GAMBLITE_BOOTS = registerItem("gamblite_boots",
            new ModArmor(ArmorMaterials.GAMBLITE_ARMOR, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(16))));

    public static void registerModItems()
    {
        GamblersFallacy.LOGGER.debug("Registering Mod Items for Gambler's Fallacy!");
    }
    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(GamblersFallacy.MOD_ID, name), item);
    }
}
