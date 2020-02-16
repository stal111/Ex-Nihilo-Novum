package com.stal111.ex_nihilo.init;

import com.stal111.ex_nihilo.ExNihilo;
import com.stal111.ex_nihilo.item.*;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ExNihilo.MOD_ID);

    public static final RegistryObject<HammerItem> WOODEN_HAMMER = register("wooden_hammer", new HammerItem(3, -2.8F, ItemTier.WOOD, properties()));
    public static final RegistryObject<HammerItem> STONE_HAMMER = register("stone_hammer", new HammerItem(3, -2.8F, ItemTier.STONE, properties()));
    public static final RegistryObject<HammerItem> IRON_HAMMER = register("iron_hammer", new HammerItem(3, -2.8F, ItemTier.IRON, properties()));
    public static final RegistryObject<HammerItem> GOLDEN_HAMMER = register("golden_hammer", new HammerItem(3, -2.8F, ItemTier.GOLD, properties()));
    public static final RegistryObject<HammerItem> DIAMOND_HAMMER = register("diamond_hammer", new HammerItem(3, -2.8F, ItemTier.DIAMOND, properties()));

    public static final RegistryObject<CrookItem> WOODEN_CROOK = register("wooden_crook", new CrookItem(ItemTier.WOOD, properties()));
    public static final RegistryObject<CrookItem> STONE_CROOK = register("stone_crook", new CrookItem(ItemTier.STONE,properties()));
    public static final RegistryObject<CrookItem> IRON_CROOK = register("iron_crook", new CrookItem(ItemTier.IRON,properties()));
    public static final RegistryObject<CrookItem> GOLDEN_CROOK = register("golden_crook", new CrookItem(ItemTier.GOLD,properties()));
    public static final RegistryObject<CrookItem> DIAMOND_CROOK = register("diamond_crook", new CrookItem(ItemTier.DIAMOND,properties()));

    public static final RegistryObject<SilkwormItem> SILKWORM = register("silkworm", new SilkwormItem(properties()));
    public static final RegistryObject<Item> COOKED_SILKWORM = register("cooked_silkworm", new Item(properties().food(new Food.Builder().hunger(2).saturation(0.7F).build())));
    public static final RegistryObject<SilkwormItem> GIANT_SILKWORM = register("giant_silkworm", new SilkwormItem(properties()));
    public static final RegistryObject<Item> COOKED_GIANT_SILKWORM = register("cooked_giant_silkworm", new Item(properties().food(new Food.Builder().hunger(4).saturation(0.9F).build())));

    public static final RegistryObject<MeshItem> STRING_MESH = register("string_mesh", new MeshItem(properties()));
    public static final RegistryObject<MeshItem> FLINT_MESH = register("flint_mesh", new MeshItem(properties()));
    public static final RegistryObject<MeshItem> IRON_MESH = register("iron_mesh", new MeshItem(properties()));
    public static final RegistryObject<MeshItem> DIAMOND_MESH = register("diamond_mesh", new MeshItem(properties()));

    public static final RegistryObject<Item> GOLD_ORE_PIECE = register("gold_ore_piece", new Item(properties()));
    public static final RegistryObject<Item> GOLD_ORE_CHUNK = register("gold_ore_chunk", new Item(properties()));
    public static final RegistryObject<Item> IRON_ORE_PIECE = register("iron_ore_piece", new Item(properties()));
    public static final RegistryObject<Item> IRON_ORE_CHUNK = register("iron_ore_chunk", new Item(properties()));

    public static final RegistryObject<Item> STONE_PEBBLE = register("stone_pebble", new Item(properties()));
    public static final RegistryObject<Item> GRANITE_PEBBLE = register("granite_pebble", new Item(properties()));
    public static final RegistryObject<Item> DIORITE_PEBBLE = register("diorite_pebble", new Item(properties()));
    public static final RegistryObject<Item> ANDESITE_PEBBLE = register("andesite_pebble", new Item(properties()));

    public static final RegistryObject<GrassSeedsItem> GRASS_SEEDS = register("grass_seeds", new GrassSeedsItem(properties()));

    private static <T extends Item> RegistryObject<T> register(String name, T item) {
        return ITEMS.register(name, () -> item);
    }

    private static Item.Properties properties() {
        return new Item.Properties().group(ExNihilo.ITEM_GROUP);
    }
}
