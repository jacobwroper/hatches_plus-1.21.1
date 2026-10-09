package com.jwr.hatchesplus.machine;

import aztech.modern_industrialization.inventory.ConfigurableFluidStack;
import aztech.modern_industrialization.thirdparty.fabrictransfer.api.fluid.FluidVariant;
import aztech.modern_industrialization.thirdparty.fabrictransfer.api.transaction.TransactionContext;
import net.minecraft.world.level.material.Fluids;

/** A water-only MI fluid slot whose contents are never consumed. */
public final class InfiniteWaterFluidStack extends ConfigurableFluidStack {
    private static final long DISPLAY_AMOUNT = Integer.MAX_VALUE;
    private static final FluidVariant WATER = FluidVariant.of(Fluids.WATER);

    public InfiniteWaterFluidStack() {
        super(DISPLAY_AMOUNT);
        super.setKey(WATER);
        super.setAmount(DISPLAY_AMOUNT);
    }

    @Override
    public long extractDirect(FluidVariant resource, long maxAmount, TransactionContext transaction) {
        return resource.equals(WATER) ? maxAmount : 0;
    }

    @Override
    public void setAmount(long ignored) {
        super.setKey(WATER);
        super.setAmount(DISPLAY_AMOUNT);
    }

    @Override
    public void setKey(FluidVariant ignored) {
        super.setKey(WATER);
    }
}
