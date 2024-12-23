package dev.padjokej.gamblersfallacy.block.slot_machine;

import com.mojang.serialization.MapCodec;
import dev.padjokej.gamblersfallacy.GamblersFallacy;
import dev.padjokej.gamblersfallacy.block.entity.ModBlockEntities;
import dev.padjokej.gamblersfallacy.items.ModItems;
import dev.padjokej.gamblersfallacy.items.ModWeapons;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;

public class SlotMachineBlock extends BlockWithEntity implements BlockEntityProvider{

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public static final MapCodec<SlotMachineBlock> CODEC = createCodec(SlotMachineBlock::new);


    public MapCodec<SlotMachineBlock> getCodec() {
        return CODEC;
    }

    public SlotMachineBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        blockState = (BlockState) blockState.with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
        return (BlockState) blockState;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SlotMachineBlockEntity(pos, state);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.SLOT_MACHINE_BLOCK_ENTITY_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
    void RollNTimes(int amount, PlayerEntity player, ServerWorld serverWorld, BlockPos pos)
    {
        ItemStack result;
        for(int i = 0; i < amount; i++){
            result = Roll(player, serverWorld, pos);
            if (result != null){
                player.getInventory().offerOrDrop(result);
                serverWorld.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_TRIAL_SPAWNER_EJECT_ITEM, SoundCategory.BLOCKS, 1f, 1f);
                serverWorld.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, pos.getX(), pos.getY() + 0.5, pos.getZ(), 200, 0, 0, 0, 1);
            }
        }
    }
    ItemStack Roll(PlayerEntity player, ServerWorld serverWorld, BlockPos pos){
        float rnd = serverWorld.random.nextFloat();

        if (rnd <= 1f/10000f){
            return new ItemStack(ModWeapons.GAMBLING_WEAPON, 1);

        }
        if (rnd <= 1f/5000f){
            return new ItemStack(Items.ANCIENT_DEBRIS, 1);
        }
        if (rnd <= 1f/2000f){
            return new ItemStack(Items.DIAMOND, 1);
        }
        if (rnd <= 1f/1000f){
            return new ItemStack(Blocks.SCULK_SENSOR, 16);
        }
        if (rnd <= 1f/750f){
            return new ItemStack(Items.GOLD_INGOT, 5);
        }
        if (rnd <= 1f/500f){
            return new ItemStack(Items.IRON_INGOT, 10);
        }
        if (rnd <= 1f/200f){
            return new ItemStack(Items.COPPER_INGOT, 32);
        }
        if (rnd <= 1f/100f) {
            return new ItemStack(Blocks.COBBLESTONE, 32);
        }
        if (rnd <= 1f/50f){
            return new ItemStack(Blocks.SPRUCE_PLANKS, 16);
        }
        if (rnd <= 1f/15f){
            return new ItemStack(ModItems.GAMBLING_CHIP, 1);
        }

        var particlePos = Vec3d.ofCenter(pos);
        serverWorld.spawnParticles(ParticleTypes.ANGRY_VILLAGER, particlePos.getX(), particlePos.getY() + 0.5, particlePos.getZ(), 1, 0.2, 0, 0.2, 0);
        return null;
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        Hand hand = player.getActiveHand();
        boolean success = false;
        int rolls = switch (player.getStackInHand(hand).getItem().toString()) {
            case "minecraft:kelp" -> {
                success = true;
                yield 1;
            }
            case GamblersFallacy.MOD_ID + ":gambling_chip" -> {
                success = true;
                yield 10;
            }
            default -> 0;
        };
        if (!player.getAbilities().creativeMode && success)
        {
            player.getStackInHand(hand).decrement(1);
        }

        if (world instanceof ServerWorld serverWorld)
        {
            if (success) {
                RollNTimes(rolls, player, serverWorld, pos);
                return ActionResult.SUCCESS;
            }
            var particlePos = Vec3d.ofCenter(pos);
            serverWorld.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.BLOCKS, 1f, 1f);
            serverWorld.spawnParticles(ParticleTypes.LARGE_SMOKE, particlePos.getX(), particlePos.getY() + 0.5, particlePos.getZ(), 1, 0, .2, 0, 0);
            return ActionResult.FAIL;
        }
        return ActionResult.SUCCESS;
    }
}
