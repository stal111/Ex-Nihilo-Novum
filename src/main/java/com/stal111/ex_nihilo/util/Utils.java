package com.stal111.ex_nihilo.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.stal111.ex_nihilo.ExNihilo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.*;

public class Utils {

    public static boolean hasInfestedCounterpart(Block block) {
        return ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(ExNihilo.MOD_ID, "infested_" + block.getRegistryName().getPath()));
    }

    public static Block getInfestedCounterpart(Block block) {
        if (hasInfestedCounterpart(block)) {
            return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ExNihilo.MOD_ID, "infested_" + block.getRegistryName().getPath()));
        }
        return null;
    }

    public static boolean equalExceptAmount(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && ((!stack1.hasTag() && !stack2.hasTag()) || (stack1.getTag().equals(stack2.getTag())));
    }

    public static JsonElement readJson(String resource, boolean... internal) throws IOException {
        Gson gson = new Gson();

        InputStream in = (internal.length > 0 && internal[0]) ? ExNihilo.class.getResourceAsStream(resource) : new FileInputStream(new File(resource));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return gson.fromJson(reader, JsonElement.class);
    }

    public static VoxelShape combineAll(VoxelShape... shapes) {
        VoxelShape result = VoxelShapes.empty();
        for (VoxelShape shape : shapes) {
            result = VoxelShapes.combineAndSimplify(result, shape, IBooleanFunction.OR);
        }
        return result.simplify();
    }
}
