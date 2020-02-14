package com.stal111.ex_nihilo.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.TieredItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrookItem extends TieredItem {

    public CrookItem(ItemTier itemTier, Properties properties) {
        super(itemTier, properties);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return state.getBlock() instanceof LeavesBlock ? 2.5F : super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0F) {
            int amount = state.getBlock() instanceof LeavesBlock ? 1 : 2;
            stack.damageItem(amount, entity, (livingEntity) -> livingEntity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        }
        return true;
    }
}
