package dev.padjokej.gamblersfallacy.items;

import dev.padjokej.gamblersfallacy.component.ModDataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.World;

public class GamblingWeapon extends SwordItem {

    public boolean isLifesteal = false;
    public boolean isBerserk = false;
    public boolean isIcy = false;
    public boolean isSpicy = false;

    public int state;

    public int getState() {
        return this.state;
    }
    public int setState(int state, ItemStack stack)
    {
        this.state = state;
        stack.set(ModDataComponentTypes.WEAPON_STATE, ConstantIntProvider.create(state));
        return state;
    }

    public GamblingWeapon(ToolMaterial toolMaterial, Settings settings) {

        super(toolMaterial, settings);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof LivingEntity attacked) {
            if (this.isLifesteal) {
                double damage = attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                attacker.heal(0.4F * (float) damage);
            }
            if (this.isIcy) {
                attacked.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SLOWNESS,
                        100, 1
                ));
            }
            if (this.isBerserk) {
                double damage = (attacker.getMaxHealth() - attacker.getHealth()) * 0.35F;
                attacked.setHealth(attacked.getHealth() - (float) damage);
            }
            if (this.isSpicy) {
                attacked.setFireTicks(50);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 100);
        if (world.isClient)
            return TypedActionResult.pass(user.getStackInHand(hand));
        RollState(world, user, hand);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    void RollState(World world, PlayerEntity player, Hand hand) {
        RollEffect(world, player);
        RollEnchant(world, player);
        RollType(world, player);
    }


    void RollType(World world, PlayerEntity player) {
        float rnd = world.random.nextFloat();
        ItemStack stack = player.getActiveItem();
        if (rnd <= 0.25f) {
            //axe
            player.sendMessage(Text.literal("axe"));

            setState(0, stack);
            return;
        }
        if (rnd <= 0.5f) {
            //glaive
            player.sendMessage(Text.literal("glaive"));
            setState(1, stack);
            return;
        }
        if (rnd <= 0.75f) {
            //scythe
            player.sendMessage(Text.literal("scythe"));
            setState(2, stack);
            return;
        }
        //sword
        player.sendMessage(Text.literal("sword"));
        setState(3, stack);
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
                    200, 0
            ));
            return;
        }
        if (rnd <= 0.75f) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.RESISTANCE,
                    80, 1
            ));
            return;
        }
        player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.STRENGTH,
                40, 1
        ));
    }

    void ClearEnchants() {
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
        this.isSpicy = true;
        player.sendMessage(Text.literal("Fire active"));
    }
}
