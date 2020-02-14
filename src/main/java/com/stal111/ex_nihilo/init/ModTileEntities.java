package com.stal111.ex_nihilo.init;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.types.Type;
import com.stal111.ex_nihilo.ExNihilo;
import com.stal111.ex_nihilo.tileentity.SieveTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ExNihilo.MOD_ID);

    public static final RegistryObject<TileEntityType<SieveTileEntity>> SIEVE = TILE_ENTITIES.register("sieve", () -> TileEntityType.Builder.create(SieveTileEntity::new, ModBlocks.OAK_SIEVE.get(), ModBlocks.SPRUCE_SIEVE.get(), ModBlocks.BIRCH_SIEVE.get(), ModBlocks.JUNGLE_SIEVE.get(), ModBlocks.ACACIA_SIEVE.get(), ModBlocks.DARK_OAK_SIEVE.get()).build(null));

    private static <T extends TileEntityType<?>> RegistryObject<T> register(String name, T tileEntityType) {
        return TILE_ENTITIES.register(name, () -> tileEntityType);
    }

}
