package com.jwr.hatchesplus.machine;

import aztech.modern_industrialization.inventory.ConfigurableFluidStack;
import aztech.modern_industrialization.inventory.MIInventory;
import aztech.modern_industrialization.inventory.SlotPositions;
import aztech.modern_industrialization.machines.BEP;
import aztech.modern_industrialization.machines.components.OrientationComponent;
import aztech.modern_industrialization.machines.gui.MachineGuiParameters;
import aztech.modern_industrialization.machines.multiblocks.HatchBlockEntity;
import aztech.modern_industrialization.machines.multiblocks.HatchType;
import aztech.modern_industrialization.machines.multiblocks.HatchTypes;
import com.jwr.hatchesplus.HatchesPlus;
import java.util.Collections;
import java.util.List;
import net.minecraft.resources.ResourceLocation;

public final class InfiniteWaterHatchBlockEntity extends HatchBlockEntity {
    private static final String MACHINE_ID = "infinite_water_hatch";

    private final InfiniteWaterFluidStack water = new InfiniteWaterFluidStack();
    private final MIInventory inventory = new MIInventory(
            Collections.emptyList(),
            List.of(water),
            SlotPositions.empty(),
            new SlotPositions.Builder().addSlot(80, 40).build());

    public InfiniteWaterHatchBlockEntity(BEP parameters) {
        super(
                parameters,
                new MachineGuiParameters.Builder(
                        ResourceLocation.fromNamespaceAndPath(HatchesPlus.MOD_ID, MACHINE_ID), true).build(),
                OrientationComponent.Params.noFacing(false, false));
    }

    @Override
    public HatchType getHatchType() {
        return HatchTypes.FLUID_INPUT;
    }

    @Override
    public boolean upgradesToSteel() {
        return true;
    }

    @Override
    public MIInventory getInventory() {
        return inventory;
    }

    @Override
    public void appendFluidInputs(List<ConfigurableFluidStack> inputs) {
        inputs.add(water);
    }

    @Override
    protected void clearMachineLock() {
        // The slot is permanently locked to water.
    }
}
