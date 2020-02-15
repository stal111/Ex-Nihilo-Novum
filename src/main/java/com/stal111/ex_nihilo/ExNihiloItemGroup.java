package com.stal111.ex_nihilo;

import com.stal111.ex_nihilo.init.ModBlocks;
import com.stal111.ex_nihilo.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExNihiloItemGroup extends ItemGroup {

    public ExNihiloItemGroup() {
        super(ExNihilo.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.OAK_SIEVE.get());
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
