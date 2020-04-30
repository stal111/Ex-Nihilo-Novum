package com.stal111.ex_nihilo.block;

import com.stal111.ex_nihilo.tileentity.InfestingLeavesTileEntity;
import com.stal111.ex_nihilo.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class InfestingLeavesBlock extends LeavesBlock {

    public static final BooleanProperty NEARBY_LEAVES = BooleanProperty.create("nearby_leaves");
    public static final IntegerProperty INFESTING_STAGE = IntegerProperty.create("infesting_stage", 0, 7);

    public InfestingLeavesBlock(Properties properties) {
        super(properties.tickRandomly());
        this.setDefaultState(stateContainer.getBaseState().with(DISTANCE, 7).with(PERSISTENT, Boolean.FALSE).with(INFESTING_STAGE, 0).with(NEARBY_LEAVES, false));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InfestingLeavesTileEntity();
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return state.get(INFESTING_STAGE) < 7 || state.get(NEARBY_LEAVES) || (state.get(DISTANCE) == 7 && !state.get(PERSISTENT));
    }

    @Override
    public int tickRate(IWorldReader world) {
        return 1;
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (hasNearbyLeaves(world, pos) && !state.get(NEARBY_LEAVES)) {
            world.setBlockState(pos, state.with(NEARBY_LEAVES, true), 2);
        } else if (!hasNearbyLeaves(world, pos) && state.get(NEARBY_LEAVES)) {
            world.setBlockState(pos, state.with(NEARBY_LEAVES, false), 2);
        }
        super.neighborChanged(state, world, pos, blockIn, fromPos, isMoving);
    }

    private void trySpread(IWorld world, BlockPos pos, Random random) {
        BlockState state = world.getBlockState(pos);
        if (!world.isRemote()) {
            for (Direction direction : Direction.values()) {
                BlockPos replacePos = pos.offset(direction);
                BlockState replaceState = world.getBlockState(replacePos);
                if (Utils.hasInfestedCounterpart(replaceState.getBlock()) && state.get(INFESTING_STAGE) >= 3 && random.nextDouble() <= 0.7) {
                    world.setBlockState(replacePos, Utils.getInfestedCounterpart(replaceState.getBlock()).getDefaultState().with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE)).with(LeavesBlock.PERSISTENT, state.get(LeavesBlock.PERSISTENT)).with(NEARBY_LEAVES, hasNearbyLeaves(world, replacePos)), 2);
                }
            }
        }
    }

    public static boolean hasNearbyLeaves(IWorld world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (Utils.hasInfestedCounterpart(world.getBlockState(pos.offset(direction)).getBlock())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NEARBY_LEAVES, INFESTING_STAGE);
        super.fillStateContainer(builder);
    }
}
