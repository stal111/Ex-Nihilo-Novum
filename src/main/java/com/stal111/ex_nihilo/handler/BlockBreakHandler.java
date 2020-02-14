package com.stal111.ex_nihilo.handler;

import com.stal111.ex_nihilo.init.ModItems;
import com.stal111.ex_nihilo.item.HammerItem;
import com.stal111.ex_nihilo.recipe.HammerRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockBreakHandler {

    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        ItemStack stack = event.getPlayer().getHeldItemMainhand();
        BlockState state = event.getWorld().getBlockState(event.getPos());
        BlockPos pos = event.getPos();
        if (stack.getItem() instanceof HammerItem) {
            if (HammerRecipe.getOutput(new ItemStack(state.getBlock())) != null) {
                event.getWorld().addEntity(new ItemEntity(event.getWorld().getWorld(), pos.getX(), pos.getY(), pos.getZ(), HammerRecipe.getOutput(new ItemStack(state.getBlock()))));
                event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                event.setCanceled(true);
            }
        }
    }
}
