package com.stal111.ex_nihilo.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.stal111.ex_nihilo.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipe;

import java.util.LinkedList;
import java.util.List;

public class HammerRecipe {

    public static List<HammerRecipe> recipes = new LinkedList<>();

    public static void initRecipes(JsonObject jsonObject) {
        ItemStack input = ShapedRecipe.deserializeItem(jsonObject.get("input").getAsJsonObject());
        ItemStack output = ShapedRecipe.deserializeItem(jsonObject.get("output").getAsJsonObject());
        recipes.add(new HammerRecipe(input, output));
    }

    public static ItemStack getOutput(BlockState state) {
        return getOutput(new ItemStack(state.getBlock()));
    }

    public static ItemStack getOutput(ItemStack in) {
        System.out.println(recipes.size());
        for (HammerRecipe recipe : recipes) {
            if (in.getItem() == recipe.input.getItem()) {
                return recipe.output;
            }
        }
        return null;
    }

    public final ItemStack input;
    public final ItemStack output;

    public HammerRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }
}
