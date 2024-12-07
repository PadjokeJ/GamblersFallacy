package dev.padjokej.gamblersfallacy;

import dev.padjokej.gamblersfallacy.utils.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;

public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        ModModelPredicates.registerModelPredicateProviders();
    }
}
