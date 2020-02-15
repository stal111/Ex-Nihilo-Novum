package com.stal111.ex_nihilo.recipe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.stal111.ex_nihilo.ExNihilo;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SieveRecipeManager implements IResourceManagerReloadListener {

    private static final Logger LOGGER = LogManager.getLogger(ExNihilo.MOD_ID + "/SieveRecipeManager");
    public static final int PATH_PREFIX_LENGTH = "sieve_recipes/".length();
    public static final int PATH_SUFFIX_LENGTH = ".json".length();
    private boolean someRecipesErrored;

    private static final Map<ResourceLocation, SieveRecipe> recipes = new HashMap<>();

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
        this.someRecipesErrored = false;

        for (ResourceLocation resourcelocation : resourceManager.getAllResourceLocations("sieve_recipes", (p_199516_0_) -> {
            return p_199516_0_.endsWith(".json") && !p_199516_0_.startsWith("_"); //Forge filter anything beginning with "_" as it's used for metadata.
        })) {
            String s = resourcelocation.getPath();
            ResourceLocation resourcelocation1 = new ResourceLocation(resourcelocation.getNamespace(), s.substring(PATH_PREFIX_LENGTH, s.length() - PATH_SUFFIX_LENGTH));

            try (IResource iresource = resourceManager.getResource(resourcelocation)) {
                JsonObject jsonobject = JSONUtils.fromJson(gson, IOUtils.toString(iresource.getInputStream(), StandardCharsets.UTF_8), JsonObject.class);
                if (jsonobject == null) {
                    LOGGER.error("Couldn't load recipe {} as it's null or empty", (Object) resourcelocation1);
                } else if (jsonobject.has("conditions") && !net.minecraftforge.common.crafting.CraftingHelper.processConditions(JSONUtils.getJsonArray(jsonobject, "conditions"))) {
                    LOGGER.info("Skipping loading recipe {} as it's conditions were not met", resourcelocation1);
                } else {
                    this.addRecipe(resourcelocation1, SieveRecipe.initRecipe(jsonobject));
                }
            } catch (IllegalArgumentException | JsonParseException jsonparseexception) {
                LOGGER.error("Parsing error loading recipe {}", resourcelocation1, jsonparseexception);
                this.someRecipesErrored = true;
            } catch (IOException ioexception) {
                LOGGER.error("Couldn't read custom advancement {} from {}", resourcelocation1, resourcelocation, ioexception);
                this.someRecipesErrored = true;
            }
        }

        LOGGER.info("Loaded {} recipes", (int) this.recipes.size());

    }

    public void addRecipe(ResourceLocation location, SieveRecipe recipe) {
        if (recipes.containsKey(location)) {
            throw new IllegalStateException("Duplicate recipe ignored with ID " + location);
        } else {
            System.out.println("ADDED RECIPE: " + location + " -> " +  recipe.input);
            recipes.put(location, recipe);
        }
    }

    public static Collection<SieveRecipe> getRecipes() {
        return recipes.values();
    }

    public static List<ItemStack> getOutputs(ItemStack mesh, ItemStack in) {
        for (SieveRecipe recipe : getRecipes()) {
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
}
