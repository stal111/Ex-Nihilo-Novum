package com.stal111.ex_nihilo.init;

import com.google.gson.JsonObject;
import com.stal111.ex_nihilo.ExNihilo;
import com.stal111.ex_nihilo.recipe.HammerRecipe;
import com.stal111.ex_nihilo.util.Utils;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = ExNihilo.MOD_ID)
public class ModRecipes {

    private static final String INNER_RECIPES_FILE = "recipes.json";

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register event) {

    }
}
