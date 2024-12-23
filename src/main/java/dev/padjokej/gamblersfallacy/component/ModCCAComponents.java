package dev.padjokej.gamblersfallacy.component;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModCCAComponents implements EntityComponentInitializer {

    public static final ComponentKey<IntComponent> PITY =
            ComponentRegistry.getOrCreate(Identifier.of(GamblersFallacy.MOD_ID, "pity"), IntComponent.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PITY, player -> new RandomIntComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
