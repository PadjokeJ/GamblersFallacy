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

import java.util.Random;

public class ModArmor extends ArmorItem {
    public ModArmor(RegistryEntry<ArmorMaterial> material, Type type, Settings settings){
        super(material, type, settings);
    }
    @Override
    public int getProtection() {
        Random random = new Random();
        float mult = random.nextFloat();
        mult *= 0.3f;
        mult += 0.95f;

        int protection = ((ArmorMaterial)this.material.value()).getProtection(this.type);

        return (int)Math.round(protection * mult);
    }
    @Override

    public float getToughness() {
        Random random = new Random();
        float mult = random.nextFloat();
        mult *= 0.3f;
        mult += 0.95f;

        float toughness = ((ArmorMaterial)this.material.value()).toughness();

        return toughness * mult;
    }
}
