package com.stal111.ex_nihilo.item;

import com.stal111.ex_nihilo.ExNihilo;
import com.stal111.ex_nihilo.block.InfestedLeavesBlock;
import com.stal111.ex_nihilo.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class SilkwormItem extends Item {

    public SilkwormItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        if (state.getBlock() instanceof LeavesBlock && ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(ExNihilo.MOD_ID, "infested_" + state.getBlock().getRegistryName().getPath()))) {
            if (player != null && !player.abilities.isCreativeMode) {
                context.getItem().shrink(1);
            }
            world.playEvent(player, 2001, pos, Block.getStateId(world.getBlockState(pos)));
            world.setBlockState(pos, Utils.getInfestedCounterpart(state.getBlock()).getDefaultState().with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE)).with(LeavesBlock.PERSISTENT, state.get(LeavesBlock.PERSISTENT)).with(InfestedLeavesBlock.NEARBY_LEAVES, InfestedLeavesBlock.hasNearbyLeaves(world, pos)));
            return ActionResultType.SUCCESS;
        }
        return super.onItemUse(context);
    }
}
