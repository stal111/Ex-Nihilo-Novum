package com.stal111.ex_nihilo.init;

import com.stal111.ex_nihilo.ExNihilo;
import com.stal111.ex_nihilo.tileentity.InfestingLeavesTileEntity;
import com.stal111.ex_nihilo.tileentity.SieveTileEntity;
import com.stal111.ex_nihilo.tileentity.WoodenCrucibleTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ExNihilo.MOD_ID);

    public static final RegistryObject<TileEntityType<InfestingLeavesTileEntity>> INFESTING_LEAVES = TILE_ENTITIES.register("infesting_leaves", () -> TileEntityType.Builder.create(InfestingLeavesTileEntity::new, ModBlocks.INFESTING_LEAVES.get()).build(null));
    public static final RegistryObject<TileEntityType<SieveTileEntity>> SIEVE = TILE_ENTITIES.register("sieve", () -> TileEntityType.Builder.create(SieveTileEntity::new, ModBlocks.OAK_SIEVE.get(), ModBlocks.SPRUCE_SIEVE.get(), ModBlocks.BIRCH_SIEVE.get(), ModBlocks.JUNGLE_SIEVE.get(), ModBlocks.ACACIA_SIEVE.get(), ModBlocks.DARK_OAK_SIEVE.get()).build(null));
    public static final RegistryObject<TileEntityType<WoodenCrucibleTileEntity>> WOODEN_CRUCIBLE = TILE_ENTITIES.register("wooden_crucible", () -> TileEntityType.Builder.create(WoodenCrucibleTileEntity::new, ModBlocks.OAK_CRUCIBLE.get(), ModBlocks.SPRUCE_CRUCIBLE.get(), ModBlocks.BIRCH_CRUCIBLE.get(), ModBlocks.JUNGLE_CRUCIBLE.get(), ModBlocks.ACACIA_CRUCIBLE.get(), ModBlocks.DARK_OAK_CRUCIBLE.get()).build(null));

}
