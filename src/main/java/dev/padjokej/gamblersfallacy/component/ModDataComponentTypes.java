package dev.padjokej.gamblersfallacy.component;

import dev.padjokej.gamblersfallacy.GamblersFallacy;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.IntProvider;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<IntProvider> WEAPON_STATE =
            register("state", builder -> builder.codec(IntProvider.VALUE_CODEC));


    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(GamblersFallacy.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        GamblersFallacy.LOGGER.info("Registering Data Component Types for " + GamblersFallacy.MOD_ID);
    }
}
