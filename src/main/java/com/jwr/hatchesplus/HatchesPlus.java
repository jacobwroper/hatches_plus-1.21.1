package com.jwr.hatchesplus;

import aztech.modern_industrialization.machines.BEP;
import aztech.modern_industrialization.machines.MachineBlock;
import com.jwr.hatchesplus.machine.InfiniteWaterHatchBlockEntity;
import com.jwr.hatchesplus.machine.StockingItemInputHatchBlockEntity;
import com.jwr.hatchesplus.machine.StockingItemOutputHatchBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(HatchesPlus.MOD_ID)
public final class HatchesPlus {
    public static final String MOD_ID = "hatches_plus";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);

    public static final DeferredBlock<MachineBlock> INFINITE_WATER_HATCH = BLOCKS.register(
            "infinite_water_hatch",
            () -> new MachineBlock(
                    (pos, state) -> new InfiniteWaterHatchBlockEntity(
                            new BEP(getInfiniteWaterHatchBlockEntityType(), pos, state)),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(4.0F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    public static final DeferredItem<BlockItem> INFINITE_WATER_HATCH_ITEM = ITEMS.register(
            "infinite_water_hatch",
            () -> new BlockItem(INFINITE_WATER_HATCH.get(), new Item.Properties()));

    public static final DeferredBlock<MachineBlock> STOCKING_ITEM_INPUT_HATCH = BLOCKS.register(
            "stocking_item_input_hatch",
            () -> new MachineBlock(
                    (pos, state) -> new StockingItemInputHatchBlockEntity(
                            new BEP(getStockingItemInputHatchBlockEntityType(), pos, state)),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(4.0F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    public static final DeferredItem<BlockItem> STOCKING_ITEM_INPUT_HATCH_ITEM = ITEMS.register(
            "stocking_item_input_hatch",
            () -> new BlockItem(STOCKING_ITEM_INPUT_HATCH.get(), new Item.Properties()));

    public static final DeferredBlock<MachineBlock> STOCKING_ITEM_OUTPUT_HATCH = BLOCKS.register(
            "stocking_item_output_hatch",
            () -> new MachineBlock(
                    (pos, state) -> new StockingItemOutputHatchBlockEntity(
                            new BEP(getStockingItemOutputHatchBlockEntityType(), pos, state)),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(4.0F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));

    public static final DeferredItem<BlockItem> STOCKING_ITEM_OUTPUT_HATCH_ITEM = ITEMS.register(
            "stocking_item_output_hatch",
            () -> new BlockItem(STOCKING_ITEM_OUTPUT_HATCH.get(), new Item.Properties()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfiniteWaterHatchBlockEntity>>
            INFINITE_WATER_HATCH_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
                    "infinite_water_hatch",
                    () -> BlockEntityType.Builder.of(
                            (pos, state) -> new InfiniteWaterHatchBlockEntity(
                                    new BEP(getInfiniteWaterHatchBlockEntityType(), pos, state)),
                            INFINITE_WATER_HATCH.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StockingItemInputHatchBlockEntity>>
            STOCKING_ITEM_INPUT_HATCH_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
                    "stocking_item_input_hatch",
                    () -> BlockEntityType.Builder.of(
                            (pos, state) -> new StockingItemInputHatchBlockEntity(
                                    new BEP(getStockingItemInputHatchBlockEntityType(), pos, state)),
                            STOCKING_ITEM_INPUT_HATCH.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StockingItemOutputHatchBlockEntity>>
            STOCKING_ITEM_OUTPUT_HATCH_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
                    "stocking_item_output_hatch",
                    () -> BlockEntityType.Builder.of(
                            (pos, state) -> new StockingItemOutputHatchBlockEntity(
                                    new BEP(getStockingItemOutputHatchBlockEntityType(), pos, state)),
                            STOCKING_ITEM_OUTPUT_HATCH.get())
                            .build(null));

    private static BlockEntityType<InfiniteWaterHatchBlockEntity> getInfiniteWaterHatchBlockEntityType() {
        return INFINITE_WATER_HATCH_BLOCK_ENTITY.get();
    }

    private static BlockEntityType<StockingItemInputHatchBlockEntity> getStockingItemInputHatchBlockEntityType() {
        return STOCKING_ITEM_INPUT_HATCH_BLOCK_ENTITY.get();
    }

    private static BlockEntityType<StockingItemOutputHatchBlockEntity> getStockingItemOutputHatchBlockEntityType() {
        return STOCKING_ITEM_OUTPUT_HATCH_BLOCK_ENTITY.get();
    }

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                STOCKING_ITEM_INPUT_HATCH_BLOCK_ENTITY.get(),
                (blockEntity, side) -> blockEntity.getInventory().itemStorage.itemHandler);
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                STOCKING_ITEM_OUTPUT_HATCH_BLOCK_ENTITY.get(),
                (blockEntity, side) -> blockEntity.getInventory().itemStorage.itemHandler);
    }

    public HatchesPlus(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(HatchesPlus::registerCapabilities);
    }
}
