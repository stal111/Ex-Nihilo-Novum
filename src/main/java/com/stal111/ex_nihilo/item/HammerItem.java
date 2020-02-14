package com.stal111.ex_nihilo.item;

import com.stal111.ex_nihilo.recipe.HammerRecipe;
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
        if (HammerRecipe.getOutput(new ItemStack(state.getBlock())) != null) {
            return efficiency;
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand p_77659_3_) {
        playerEntity.sendMessage(new StringTextComponent(String.valueOf(HammerRecipe.recipes.size())));
        for (HammerRecipe recipe : HammerRecipe.recipes) {
            playerEntity.sendMessage(new StringTextComponent(recipe.input.getTranslationKey() + recipe.output.getTranslationKey()));
        }

        playerEntity.sendMessage(new StringTextComponent(HammerRecipe.getOutput(new ItemStack(Blocks.COBBLESTONE)).getTranslationKey()));
        return super.onItemRightClick(world, playerEntity, p_77659_3_);
    }

    @Override
    public boolean canHarvestBlock(BlockState state) {
        return this.getTier().getHarvestLevel() >= state.getHarvestLevel() && HammerRecipe.getOutput(new ItemStack(state.getBlock())) != null;
    }
}
