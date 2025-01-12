package dev.padjokej.gamblersfallacy.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

public class ModArmor extends ArmorItem {
    public ModArmor(RegistryEntry<ArmorMaterial> material, Type type, Settings settings){
        super(material, type, settings);
    }

}
