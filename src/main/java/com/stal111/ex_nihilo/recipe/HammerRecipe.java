package com.stal111.ex_nihilo.recipe;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipe;

public class HammerRecipe {

    public static HammerRecipe initRecipe(JsonObject jsonObject) {
        ItemStack input = ShapedRecipe.deserializeItem(jsonObject.get("input").getAsJsonObject());
        ItemStack output = ShapedRecipe.deserializeItem(jsonObject.get("output").getAsJsonObject());
        return new HammerRecipe(input, output);
    }

    public final ItemStack input;
    public final ItemStack output;

    public HammerRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }
}
