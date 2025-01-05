/**************************************************************************
 * Source from : https://www.youtube.com/watch?v=8MDZmc8ri8s
 * ************************************************************************/

package dev.padjokej.gamblersfallacy.utils;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import dev.padjokej.gamblersfallacy.component.ModDataComponentTypes;
import dev.padjokej.gamblersfallacy.items.GamblingWeapon;
import dev.padjokej.gamblersfallacy.items.ModWeapons;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public class ModModelPredicates {
    public static void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(ModWeapons.GAMBLING_WEAPON, Identifier.of(GamblersFallacy.MOD_ID, "state"),
                (stack, world, entity, seed) -> {
            if (stack.get(ModDataComponentTypes.WEAPON_STATE) != null)
                return stack.get(ModDataComponentTypes.WEAPON_STATE).get(Random.create());
            else
                return 0f;
            }
        );
    }
}
