package com.jwr.hatchesplus.mixin;

import aztech.modern_industrialization.inventory.ConfigurableItemStack;
import aztech.modern_industrialization.thirdparty.fabrictransfer.api.item.ItemVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Allows explicitly oversized MI item slots while leaving normal MI slots unchanged. */
@Mixin(ConfigurableItemStack.class)
public abstract class ConfigurableItemStackMixin {
    private static final int VANILLA_MAX_CAPACITY = 64;

    @Shadow
    private int adjustedCapacity;

    @Inject(method = "getCapacity", at = @At("HEAD"), cancellable = true)
    private void hatchesPlus$getCapacity(CallbackInfoReturnable<Long> callback) {
        if (adjustedCapacity > VANILLA_MAX_CAPACITY) {
            callback.setReturnValue((long) adjustedCapacity);
        }
    }

    @Inject(method = "getRemainingCapacityFor", at = @At("HEAD"), cancellable = true)
    private void hatchesPlus$getRemainingCapacityFor(
            ItemVariant ignored,
            CallbackInfoReturnable<Long> callback) {
        if (adjustedCapacity > VANILLA_MAX_CAPACITY) {
            long amount = ((ConfigurableItemStack) (Object) this).getAmount();
            callback.setReturnValue(Math.max(0L, (long) adjustedCapacity - amount));
        }
    }

    @Inject(method = "getTotalCapacityFor", at = @At("HEAD"), cancellable = true)
    private void hatchesPlus$getTotalCapacityFor(
            net.minecraft.world.item.Item ignored,
            CallbackInfoReturnable<Long> callback) {
        if (adjustedCapacity > VANILLA_MAX_CAPACITY) {
            callback.setReturnValue((long) adjustedCapacity);
        }
    }

    @Inject(method = "adjustCapacity", at = @At("HEAD"), cancellable = true)
    private void hatchesPlus$keepOversizedCapacityFixed(
            boolean ignoredIncrease,
            boolean ignoredShift,
            CallbackInfo callback) {
        if (adjustedCapacity > VANILLA_MAX_CAPACITY) {
            callback.cancel();
        }
    }
}
