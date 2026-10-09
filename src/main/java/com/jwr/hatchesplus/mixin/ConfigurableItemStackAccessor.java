package com.jwr.hatchesplus.mixin;

import aztech.modern_industrialization.inventory.ConfigurableItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ConfigurableItemStack.class)
public interface ConfigurableItemStackAccessor {
    @Accessor("adjustedCapacity")
    void hatchesPlus$setAdjustedCapacity(int capacity);
}
