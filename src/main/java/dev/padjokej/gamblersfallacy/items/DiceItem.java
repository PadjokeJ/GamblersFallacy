package dev.padjokej.gamblersfallacy.items;

import dev.padjokej.gamblersfallacy.component.ModDataComponentTypes;
import net.minecraft.client.session.report.ReporterEnvironment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.world.World;

public class DiceItem extends Item {
    public DiceItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getStackInHand(hand);

        if (world instanceof ServerWorld serverWorld)
            stack.set(ModDataComponentTypes.DICE_ROLL_VALUE, ConstantFloatProvider.create(serverWorld.random.nextFloat()));

        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
