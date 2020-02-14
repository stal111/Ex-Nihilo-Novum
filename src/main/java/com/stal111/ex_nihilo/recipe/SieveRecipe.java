package com.stal111.ex_nihilo.recipe;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipe;

import java.util.LinkedList;
import java.util.List;

public class SieveRecipe {

    public static List<SieveRecipe> recipes = new LinkedList<>();

    public static void initRecipes(JsonObject jsonObject) {
        ItemStack mesh = ShapedRecipe.deserializeItem(jsonObject.get("mesh").getAsJsonObject());
        ItemStack input = ShapedRecipe.deserializeItem(jsonObject.get("input").getAsJsonObject());
        ItemStack output = ShapedRecipe.deserializeItem(jsonObject.get("output").getAsJsonObject());
        recipes.add(new SieveRecipe(mesh, input, output));
    }

    public static ItemStack getOutput(BlockState state) {
        return getOutput(new ItemStack(state.getBlock()));
    }

    public static ItemStack getOutput(ItemStack in) {
        for (SieveRecipe recipe : recipes) {
            if (in.getItem() == recipe.input.getItem()) {
                System.out.println(recipe.input + " -> " + recipe.output);
                return recipe.output;
            }
        }
        return null;
    }

    public final ItemStack mesh;
    public final ItemStack input;
    public final ItemStack output;

    public SieveRecipe(ItemStack mesh, ItemStack input, ItemStack output) {
        this.mesh = mesh;
        this.input = input;
        this.output = output;
    }
}
