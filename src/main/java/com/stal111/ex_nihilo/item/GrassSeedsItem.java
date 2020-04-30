package com.stal111.ex_nihilo.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrassSeedsItem extends Item {

    public GrassSeedsItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (world.getBlockState(pos).getBlock() == Blocks.DIRT) {
            if (!context.getPlayer().abilities.isCreativeMode) {
                context.getItem().shrink(1);
            }
            world.playEvent(context.getPlayer(), 2001, pos, Block.getStateId(world.getBlockState(pos)));
            world.setBlockState(pos, Blocks.GRASS_BLOCK.getDefaultState(), 2);
            return ActionResultType.SUCCESS;
        }
        return super.onItemUse(context);
    }
}
