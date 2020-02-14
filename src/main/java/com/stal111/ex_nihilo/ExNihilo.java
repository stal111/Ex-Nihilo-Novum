package com.stal111.ex_nihilo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stal111.ex_nihilo.init.ModBlocks;
import com.stal111.ex_nihilo.init.ModItems;
import com.stal111.ex_nihilo.init.ModTileEntities;
import com.stal111.ex_nihilo.recipe.HammerRecipe;
import com.stal111.ex_nihilo.recipe.SieveRecipe;
import com.stal111.ex_nihilo.render.SieveTileEntityRender;
import com.stal111.ex_nihilo.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod(ExNihilo.MOD_ID)
public class ExNihilo {

    public static final String MOD_ID = "ex_nihilo";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger(ExNihilo.MOD_ID);

    private static final String INNER_RECIPES_FILE = "recipes.json";

    public static final ItemGroup ITEM_GROUP = new ExNihiloItemGroup();

    public ExNihilo() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModTileEntities.TILE_ENTITIES.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::enqueueIMC);
        eventBus.addListener(this::processIMC);
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        for (String string : getResourceFiles("/data/ex_nihilo/recipes/hammer/")) {
            System.out.println(string);
            JsonObject defaultRecipes = null;
            try {
                defaultRecipes = Utils.readJson("/data/ex_nihilo/recipes/hammer/" + string, true).getAsJsonObject();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            HammerRecipe.initRecipes((defaultRecipes.getAsJsonObject()));
        }


        for (String string : getResourceFiles("/data/ex_nihilo/recipes/sieve/")) {
            System.out.println(string);
            JsonObject defaultRecipes = null;
            try {
                defaultRecipes = Utils.readJson("/data/ex_nihilo/recipes/sieve/" + string, true).getAsJsonObject();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            SieveRecipe.initRecipes((defaultRecipes.getAsJsonObject()));
        }
    }

    private List<String> getResourceFiles(String path) {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = ExNihilo.class.getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filenames;
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIEVE.get(), SieveTileEntityRender::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
