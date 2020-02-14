package com.stal111.ex_nihilo.util;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class Tags {

    public static final Tag<Block> LEAVES = tag("leaves");

    private static Tag<Block> tag(String name) {
        return new BlockTags.Wrapper(new ResourceLocation("minecraft", name));
    }
}
