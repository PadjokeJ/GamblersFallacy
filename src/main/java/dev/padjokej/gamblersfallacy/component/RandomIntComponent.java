package dev.padjokej.gamblersfallacy.component;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;


class RandomIntComponent implements IntComponent {
    private int value = (int) 0;
    @Override public int getValue() { return this.value; }
    @Override public void increment() { this.value++; }
    @Override public void resetValue(){this.value = 0;}
    @Override public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) { this.value = tag.getInt("value"); }
    @Override public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) { tag.putInt("value", this.value); }
}
