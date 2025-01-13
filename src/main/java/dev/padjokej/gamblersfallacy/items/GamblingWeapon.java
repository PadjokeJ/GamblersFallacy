package dev.padjokej.gamblersfallacy.items;

import dev.padjokej.gamblersfallacy.component.ModDataComponentTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
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
    public void setState(int state, ItemStack stack, PlayerEntity player)
    {
        this.state = state;
        stack.set(ModDataComponentTypes.WEAPON_STATE, ConstantIntProvider.create(state));
    }

    public GamblingWeapon(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof LivingEntity attacked) {
            if (this.isLifesteal) {
                double damage = attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                attacker.heal(0.1F * (float) damage);
            }
            if (this.isIcy) {
                attacked.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SLOWNESS,
                        80, 2
                ));
            }
            if (this.isBerserk) {
                double damage = (attacker.getMaxHealth() - attacker.getHealth()) * 0.125F;
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
        RollType(world, player, hand);
    }


    void RollType(World world, PlayerEntity player, Hand hand) {
        float rnd = world.random.nextFloat();
        ItemStack stack = player.getStackInHand(hand);

        if (rnd <= 0.25f) { // axe
            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                    .add(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3,
                            EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                    .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 9,
                                    EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                    .build());
            setState(0, stack, player);
            return;
        }
        if (rnd <= 0.5f) { // glaive
            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                            .add(EntityAttributes.GENERIC_ATTACK_SPEED,
                            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2,
                                    EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 6,
                                    EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                            .add(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 1,
                                    EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                    .build());
            setState(1, stack, player);
            return;
        }
        if (rnd <= 0.75f) { // scythe
            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                    .add(EntityAttributes.GENERIC_ATTACK_SPEED,
                            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3,
                                    EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                    .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 8,
                                    EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                    .add(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 0.5,
                                    EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                    .build());
            setState(2, stack, player);
            return;
        } // sword
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.4,
                                EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 7,
                                EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build());
        setState(3, stack, player);

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
            world.playSound((PlayerEntity) null, player.getBlockPos(), SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, SoundCategory.BLOCKS, 1f, 0.8f - (0.5f * world.random.nextFloat()));
            return;
        }
        if (rnd <= 0.5f) {
            this.isBerserk = true;
            world.playSound((PlayerEntity) null, player.getBlockPos(), SoundEvents.ENTITY_WOLF_GROWL, SoundCategory.BLOCKS, 1f, 0.6f - (0.5f * world.random.nextFloat()));
            return;
        }
        if (rnd <= 0.75f) {
            this.isIcy = true;
            world.playSound((PlayerEntity) null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_HURT_FREEZE, SoundCategory.BLOCKS, 1f, 0.8f - (0.5f * world.random.nextFloat()));
            return;
        }
        this.isSpicy = true;
        world.playSound((PlayerEntity) null, player.getBlockPos(), SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.BLOCKS, 1f, 1f + (0.5f * world.random.nextFloat()));
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !(miner.isCreative() && this.getState() == 3);
    }
}
