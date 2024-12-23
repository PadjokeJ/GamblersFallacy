package dev.padjokej.gamblersfallacy.stats;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;

public class Stats {

    public static final Identifier GAMBLED = register("times_gambled", StatFormatter.DEFAULT);

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Identifier.of(GamblersFallacy.MOD_ID, id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        net.minecraft.stat.Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }

    public static void registerModStats()
    {
        GamblersFallacy.LOGGER.debug("Registering Mod Stats for Gambler's Fallacy!");
    }
}
