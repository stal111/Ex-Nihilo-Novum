package com.stal111.ex_nihilo.tileentity;

import com.stal111.ex_nihilo.init.ModTileEntities;
import com.stal111.ex_nihilo.item.MeshItem;
import com.stal111.ex_nihilo.recipe.HammerRecipe;
import com.stal111.ex_nihilo.recipe.SieveRecipe;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.StringTextComponent;

public class SieveTileEntity extends TileEntity {

    public ItemStack meshStack = ItemStack.EMPTY;
    private ItemStack content = ItemStack.EMPTY;

    private int progress = 0;

    public SieveTileEntity() {
        super(ModTileEntities.SIEVE.get());
    }

    public boolean makeProgress(PlayerEntity player) {
        if (progress < 10 && !meshStack.isEmpty() && !content.isEmpty()) {
            progress += 1;
            if (progress == 10) {
                if (world != null && !world.isRemote()) {
                    player.sendMessage(new StringTextComponent(String.valueOf(HammerRecipe.getOutput(content))));
                    world.addEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, SieveRecipe.getOutput(content)));
                }
                reset();
            }
            this.markDirty();
            return true;
        }
        return false;
    }

    public void reset() {
        progress = 0;
        content.shrink(1);
        this.markDirty();
    }

    public boolean setMesh(ItemStack mesh) {
        if (progress != 0) {
            return false;
        }
        if (meshStack.isEmpty()) {
            meshStack = new ItemStack(mesh.getItem());
            this.markDirty();
            return true;
        }
        return false;
    }

    public ItemStack getMesh() {
        return meshStack;
    }

    public boolean hasMesh() {
        Item item = this.meshStack.getItem();
        return item instanceof MeshItem;
    }

    public boolean setContent(ItemStack stack) {
        if (SieveRecipe.getOutput(stack) != null) {
            if (progress == 0) {
                if (!meshStack.isEmpty() && content.isEmpty()) {
                    content = new ItemStack(stack.getItem());
                    this.markDirty();
                    return true;
                }
            }
        }
        return false;
    }

    public ItemStack getContent() {
        return content;
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public int getProgress() {
        return this.progress;
    }

    @Override
    public void read(CompoundNBT compoundNBT) {
        super.read(compoundNBT);
        this.progress = compoundNBT.getInt("Progress");
        this.meshStack = ItemStack.read(compoundNBT.getCompound("Mesh"));
        this.content = ItemStack.read(compoundNBT.getCompound("Content"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compoundNBT) {
        super.write(compoundNBT);
        compoundNBT.putInt("Progress", progress);
        compoundNBT.put("Mesh", meshStack.write(new CompoundNBT()));
        compoundNBT.put("Content", content.write(new CompoundNBT()));

        return compoundNBT;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }
}
