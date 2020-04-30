package com.stal111.ex_nihilo.handler;

import com.stal111.ex_nihilo.init.ModItems;
import com.stal111.ex_nihilo.item.HammerItem;
import com.stal111.ex_nihilo.recipe.HammerRecipe;
import com.stal111.ex_nihilo.recipe.HammerRecipeManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockBreakHandler {

    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        IWorld world = event.getWorld();
        ItemStack stack = event.getPlayer().getHeldItemMainhand();
        BlockState state = world.getBlockState(event.getPos());
        BlockPos pos = event.getPos();
        if (stack.getItem() instanceof HammerItem) {
            if (HammerRecipeManager.getOutputs(new ItemStack(state.getBlock())) != null) {
                if (!world.getWorld().isRemote()) {
                    ItemEntity itemEntity = new ItemEntity(world.getWorld(), pos.getX(), pos.getY(), pos.getZ(), HammerRecipeManager.getOutputs(new ItemStack(state.getBlock())));
                    itemEntity.setDefaultPickupDelay();
                    world.getWorld().addEntity(itemEntity);
                    world.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                    event.setCanceled(true);
                }
            }
        }
    }
}
