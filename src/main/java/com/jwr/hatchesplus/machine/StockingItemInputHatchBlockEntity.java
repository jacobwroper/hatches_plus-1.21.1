package com.jwr.hatchesplus.machine;

import aztech.modern_industrialization.inventory.ConfigurableItemStack;
import aztech.modern_industrialization.inventory.MIInventory;
import aztech.modern_industrialization.inventory.SlotPositions;
import aztech.modern_industrialization.machines.BEP;
import aztech.modern_industrialization.machines.blockentities.hatches.ItemHatch;
import aztech.modern_industrialization.machines.gui.MachineGuiParameters;
import aztech.modern_industrialization.util.Simulation;
import com.jwr.hatchesplus.HatchesPlus;
import com.jwr.hatchesplus.mixin.ConfigurableItemStackAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

/**
 * A nine-channel input hatch intended for deliberately configured,
 * high-throughput recipes. Every channel starts locked to air, so automation
 * cannot insert anything until the player assigns an item to that channel.
 */
public final class StockingItemInputHatchBlockEntity extends ItemHatch {
    private static final String MACHINE_ID = "stocking_item_input_hatch";
    private static final int CHANNEL_COUNT = 9;
    private static final int CHANNEL_CAPACITY = 4096;

    public StockingItemInputHatchBlockEntity(BEP parameters) {
        super(
                parameters,
                new MachineGuiParameters.Builder(
                        ResourceLocation.fromNamespaceAndPath(HatchesPlus.MOD_ID, MACHINE_ID), true)
                        .build(),
                true,
                true,
                createInventory());
    }

    private static MIInventory createInventory() {
        List<ConfigurableItemStack> channels = new ArrayList<>(CHANNEL_COUNT);
        for (int i = 0; i < CHANNEL_COUNT; ++i) {
            ConfigurableItemStack channel = ConfigurableItemStack.standardInputSlot();
            ((ConfigurableItemStackAccessor) channel).hatchesPlus$setAdjustedCapacity(CHANNEL_CAPACITY);
            channel.playerLock(Items.AIR, Simulation.ACT);
            channels.add(channel);
        }

        return new MIInventory(
                channels,
                Collections.emptyList(),
                new SlotPositions.Builder().addSlots(7, 35, CHANNEL_COUNT, 1).build(),
                SlotPositions.empty());
    }
}
