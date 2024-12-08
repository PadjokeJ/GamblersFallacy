package dev.padjokej.gamblersfallacy.items;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModWeapons {

    public static final Item GAMBLING_WEAPON = register(new GamblingWeapon(ModMaterials.GAMBLING_MATERIAL, new Item.Settings()
            .attributeModifiers(GamblingWeapon.createAttributeModifiers(ModMaterials.GAMBLING_MATERIAL, 3, -2.4F))),
            "gambling_weapon");
    public static Item register(Item item, String id)
    {
        Identifier itemID = Identifier.of(GamblersFallacy.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        return registeredItem;
    }

    public static void registerModWeapons()
    {
        GamblersFallacy.LOGGER.debug("Registering Mod Items for Gambler's Fallacy!");
    }

}
