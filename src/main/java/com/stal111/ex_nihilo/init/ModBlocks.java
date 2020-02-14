package com.stal111.ex_nihilo.init;

import com.stal111.ex_nihilo.ExNihilo;
import com.stal111.ex_nihilo.block.InfestedLeavesBlock;
import com.stal111.ex_nihilo.block.SieveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ExNihilo.MOD_ID);

    public static final RegistryObject<InfestedLeavesBlock> INFESTED_OAK_LEAVES = register("infested_oak_leaves", new InfestedLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<InfestedLeavesBlock> INFESTED_BIRCH_LEAVES = register("infested_birch_leaves", new InfestedLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<InfestedLeavesBlock> INFESTED_SPRUCE_LEAVES = register("infested_spruce_leaves", new InfestedLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<InfestedLeavesBlock> INFESTED_JUNGLE_LEAVES = register("infested_jungle_leaves", new InfestedLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<InfestedLeavesBlock> INFESTED_ACACIA_LEAVES = register("infested_acacia_leaves", new InfestedLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<InfestedLeavesBlock> INFESTED_DARK_OAK_LEAVES = register("infested_dark_oak_leaves", new InfestedLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()));

    public static final RegistryObject<SieveBlock> OAK_SIEVE = register("oak_sieve", new SieveBlock(Block.Properties.from(Blocks.OAK_PLANKS).notSolid()));
    public static final RegistryObject<SieveBlock> SPRUCE_SIEVE = register("spruce_sieve", new SieveBlock(Block.Properties.from(Blocks.OAK_PLANKS).notSolid()));
    public static final RegistryObject<SieveBlock> BIRCH_SIEVE = register("birch_sieve", new SieveBlock(Block.Properties.from(Blocks.OAK_PLANKS).notSolid()));
    public static final RegistryObject<SieveBlock> JUNGLE_SIEVE = register("jungle_sieve", new SieveBlock(Block.Properties.from(Blocks.OAK_PLANKS).notSolid()));
    public static final RegistryObject<SieveBlock> ACACIA_SIEVE = register("acacia_sieve", new SieveBlock(Block.Properties.from(Blocks.OAK_PLANKS).notSolid()));
    public static final RegistryObject<SieveBlock> DARK_OAK_SIEVE = register("dark_oak_sieve", new SieveBlock(Block.Properties.from(Blocks.OAK_PLANKS).notSolid()));

    public static final RegistryObject<Block> DUST = register("dust", new SandBlock(14406560, Block.Properties.from(Blocks.SAND)));

    private static <T extends Block> RegistryObject<T> register(String name, T block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block, new Item.Properties().group(ExNihilo.ITEM_GROUP)));
        return BLOCKS.register(name, () -> block);
    }
}
