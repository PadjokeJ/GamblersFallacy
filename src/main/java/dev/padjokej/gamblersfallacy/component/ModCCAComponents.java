package dev.padjokej.gamblersfallacy.component;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import dev.padjokej.gamblersfallacy.block.slot_machine.SlotMachineBlock;
import net.minecraft.entity.Entity;
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

    public static boolean useSlotMachine(Entity provider){
        PITY.get(provider).increment();
        int pity = PITY.get(provider).getValue();

        return pity > SlotMachineBlock.maxPity;
    }

    public static void resetPity(Entity provider){
        PITY.get(provider).resetValue();
    }
}
