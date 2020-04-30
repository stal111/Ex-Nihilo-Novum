package com.stal111.ex_nihilo.item;

import com.stal111.ex_nihilo.recipe.HammerRecipe;
import com.stal111.ex_nihilo.recipe.HammerRecipeManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.HashSet;

public class HammerItem extends ToolItem {

    public HammerItem(float attackDamage, float attackSpeed, IItemTier tier, Item.Properties properties) {
        super(attackDamage, attackSpeed, tier, new HashSet<>(), properties);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (HammerRecipeManager.getOutputs(new ItemStack(state.getBlock())) != null) {
            return efficiency;
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean canHarvestBlock(BlockState state) {
        return this.getTier().getHarvestLevel() >= state.getHarvestLevel() && HammerRecipeManager.getOutputs(new ItemStack(state.getBlock())) != null;
    }
}
