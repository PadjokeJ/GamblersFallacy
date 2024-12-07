package dev.padjokej.gamblersfallacy.items;

import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stat;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GamblingWeapon extends SwordItem {

    public boolean isLifesteal = false;
    public boolean isBerserk = false;
    public boolean isIcy = false;
    public boolean isSpicy = false;

    public GamblingWeapon(ToolMaterial toolMaterial, Settings settings) {

        super(toolMaterial, settings);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof LivingEntity attacked) {
            if (this.isLifesteal) {
                double damage = attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                attacker.heal(0.2F * (float) damage);
            }
            if (this.isIcy){
                attacked.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SLOWNESS,
                        100, 1
                ));
            }
            if (this.isBerserk) {
                double damage = (attacker.getMaxHealth() - attacker.getHealth()) * 0.1F;
                attacked.setHealth(attacked.getHealth() - (float)damage);
            }
            if (this.isSpicy)
            {
                attacked.setFireTicks(50);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 100);
        if (world.isClient)
            return TypedActionResult.pass(user.getStackInHand(hand));
        RollState(world, user);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    void RollState(World world, PlayerEntity player) {
        RollEffect(world, player);
        RollEnchant(world, player);
    }

    void RollEffect(World world, PlayerEntity player) {
        float rnd = world.random.nextFloat();

        if (rnd <= 0.25f) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SPEED,
                    50, 1
            ));
            return;
        }
        if (rnd <= 0.5f) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.REGENERATION,
                    100, 0
            ));
            return;
        }
        if (rnd <= 0.75f) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.RESISTANCE,
                    50, 1
            ));
            return;
        }
        if (rnd <= 1f) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.STRENGTH,
                    25, 1
            ));
            return;
        }
    }

    void ClearEnchants()
    {
        this.isLifesteal = false;
        this.isBerserk = false;
        this.isIcy = false;
        this.isSpicy = false;
    }

    void RollEnchant(World world, PlayerEntity player) {
        ClearEnchants();
        float rnd = world.random.nextFloat();

        if (rnd <= 0.25f) {
            this.isLifesteal = true;
            player.sendMessage(Text.literal("Lifesteal active"));
            return;
        }
        if (rnd <= 0.5f) {
            this.isBerserk = true;
            player.sendMessage(Text.literal("Berserk active"));
            return;
        }
        if (rnd <= 0.75f) {
            this.isIcy = true;
            player.sendMessage(Text.literal("Ice active"));
            return;
        }
        if (rnd <= 1f) {
            this.isSpicy = true;
            player.sendMessage(Text.literal("Fire active"));
            return;
        }
    }
}
