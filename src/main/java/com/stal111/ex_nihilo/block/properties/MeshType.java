package com.stal111.ex_nihilo.block.properties;

import com.stal111.ex_nihilo.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public enum MeshType implements IStringSerializable {
    EMPTY("empty", RegistryObject.of(new ResourceLocation("minecraft", "air"), ForgeRegistries.BLOCKS)),
    STRING("string", ModItems.STRING_MESH),
    FLINT("flint", ModItems.FLINT_MESH),
    IRON("iron", ModItems.IRON_MESH),
    DIAMOND("diamond", ModItems.DIAMOND_MESH);

    private final String name;
    private final RegistryObject<?> item;

    MeshType(String name, RegistryObject<?> item) {
        this.name = name;
        this.item = item;
    }

    @Override
    public String getName() {
        return name;
    }

    public ItemStack getStack() {
        return new ItemStack((IItemProvider) item.get());
    }
}
