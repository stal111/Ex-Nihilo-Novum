package com.stal111.ex_nihilo.tileentity;

import com.stal111.ex_nihilo.block.InfestingLeavesBlock;
import com.stal111.ex_nihilo.init.ModBlocks;
import com.stal111.ex_nihilo.init.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import java.util.Random;

public class InfestingLeavesTileEntity extends TileEntity implements ITickableTileEntity {

    public float progress = 0.0F;
    private ItemStack stack = ItemStack.EMPTY;

    public InfestingLeavesTileEntity() {
        super(ModTileEntities.INFESTING_LEAVES.get());
    }

    @Override
    public void tick() {
        progress += 0.004F;

        if (progress < 1.0F) {
            markDirty();

            if (progress > 0.4F && getBlockState().get(InfestingLeavesBlock.NEARBY_LEAVES)) {
                for (Direction direction : Direction.values()) {
                    if (new Random().nextDouble() < 0.3 && world != null) {
                        if (BlockTags.LEAVES.contains(world.getBlockState(pos.offset(direction)).getBlock())) {
                            world.setBlockState(pos.offset(direction), ModBlocks.INFESTING_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, getBlockState().get(LeavesBlock.DISTANCE)).with(LeavesBlock.PERSISTENT, getBlockState().get(LeavesBlock.PERSISTENT)), 3);
                        }
                    }
                }
            }

            return;
        }

        if (world != null) {
            world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
        }
    }

    public ItemStack getBlock() {
        return this.stack;
    }

    public InfestingLeavesTileEntity setBlock(Block block) {
        this.stack = new ItemStack(block.asItem());
        return this;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.progress = compound.getInt("Progress");
        this.stack = ItemStack.read(compound.getCompound("Block"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putFloat("Progress", progress);
        compound.put("Block", stack.write(new CompoundNBT()));

        return compound;
    }
}
