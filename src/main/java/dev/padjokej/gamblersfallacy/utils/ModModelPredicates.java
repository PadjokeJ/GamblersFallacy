/**************************************************************************
 * Source from : https://www.youtube.com/watch?v=8MDZmc8ri8s
 * ************************************************************************/

package dev.padjokej.gamblersfallacy.utils;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import dev.padjokej.gamblersfallacy.items.GamblingWeapon;
import dev.padjokej.gamblersfallacy.items.ModWeapons;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(ModWeapons.GAMBLING_WEAPON, Identifier.of(GamblersFallacy.MOD_ID, "state"),
                (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0;
            }
            if (stack.getItem() instanceof GamblingWeapon gamblingWeapon) {
                return gamblingWeapon.getState() / 4f;
            }
            return 0;
        });
    }
}
