package com.stal111.ex_nihilo.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SieveRecipe {

    public static List<SieveRecipe> recipes = new LinkedList<>();

    public static void initRecipes(JsonObject jsonObject) {
        ItemStack mesh = ShapedRecipe.deserializeItem(jsonObject.get("mesh").getAsJsonObject());
        ItemStack input = ShapedRecipe.deserializeItem(jsonObject.get("input").getAsJsonObject());
        JsonArray outputsArray = jsonObject.get("outputs").getAsJsonArray();

        ItemStack[] outputs = new ItemStack[outputsArray.size()];
        double[] probabilities = new double[outputsArray.size()];

        for (int i = 0; i < outputs.length; i++) {
            JsonObject o = outputsArray.get(i).getAsJsonObject();
            outputs[i] = ShapedRecipe.deserializeItem(o.get("out").getAsJsonObject());
            probabilities[i] = o.has("probability") ? o.get("probability").getAsDouble() : 1.0;
        }

        recipes.add(new SieveRecipe(mesh, input, outputs, probabilities));
    }


    public static SieveRecipe initRecipe(JsonObject jsonObject) {
        ItemStack mesh = ShapedRecipe.deserializeItem(jsonObject.get("mesh").getAsJsonObject());
        ItemStack input = ShapedRecipe.deserializeItem(jsonObject.get("input").getAsJsonObject());
        JsonArray outputsArray = jsonObject.get("outputs").getAsJsonArray();

        ItemStack[] outputs = new ItemStack[outputsArray.size()];
        double[] probabilities = new double[outputsArray.size()];

        for (int i = 0; i < outputs.length; i++) {
            JsonObject o = outputsArray.get(i).getAsJsonObject();
            outputs[i] = ShapedRecipe.deserializeItem(o.get("out").getAsJsonObject());
            probabilities[i] = o.has("probability") ? o.get("probability").getAsDouble() : 1.0;
        }

        return new SieveRecipe(mesh, input, outputs, probabilities);
    }


    public static List<ItemStack> getOutputs(ItemStack mesh, BlockState state) {
        return getOutputs(mesh, new ItemStack(state.getBlock()));
    }

    public static List<ItemStack> getOutputs(ItemStack mesh, ItemStack in) {
        System.out.println(recipes.size());
        for (SieveRecipe recipe : recipes) {
            System.out.println(recipe.input);
            if (mesh.getItem() == recipe.mesh.getItem()) {
                if (in.getItem() == recipe.input.getItem()) {
                    List<ItemStack> list = new ArrayList<>();
                    for (int i = 0; i < recipe.outputs.length; i++) {
                        if (new Random().nextDouble() <= recipe.probabilities[i]) {
                            list.add(recipe.outputs[i]);
                        }
                    }
                    return list;
                }
            }
        }
        return null;
    }

    public final ItemStack mesh;
    public final ItemStack input;
    public final ItemStack[] outputs;
    public final double[] probabilities;

    public SieveRecipe(ItemStack mesh, ItemStack input, ItemStack[] outputs, double[] probabilities) {
        this.mesh = mesh;
        this.input = input;
        this.outputs = outputs;
        this.probabilities = probabilities;
    }
}
