package com.stal111.ex_nihilo.handler;

import com.stal111.ex_nihilo.recipe.HammerRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class HarvestCheckHandler {

    @SubscribeEvent
    public static void onBlockBroken(PlayerEvent.HarvestCheck event) {
        BlockState state = event.getTargetBlock();
        event.setCanHarvest(false);

    }
}
