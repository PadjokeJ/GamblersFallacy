package dev.padjokej.gamblersfallacy.items;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CHIP = registerItem("chip",
            new Item(new Item.Settings()));
    public static final Item GAMBLING_CHIP = registerItem("gambling_chip",
            new Item(new Item.Settings()));
    public static void registerModItems()
    {
        GamblersFallacy.LOGGER.debug("Registering Mod Items for Gambler's Fallacy!");
    }
    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(GamblersFallacy.MOD_ID, name), item);
    }
}
