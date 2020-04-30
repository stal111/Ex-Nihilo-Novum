package com.stal111.ex_nihilo.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.stal111.ex_nihilo.tileentity.InfestingLeavesTileEntity;
import com.stal111.ex_nihilo.tileentity.SieveTileEntity;
import com.stal111.ex_nihilo.util.ColorUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.client.extensions.IForgeBakedModel;
import net.minecraftforge.client.model.BakedItemModel;
import net.minecraftforge.client.model.ModelTransformComposition;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class InfestingLeavesTileEntityRender extends TileEntityRenderer<InfestingLeavesTileEntity> {

    public InfestingLeavesTileEntityRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfestingLeavesTileEntity tileEntity, float v, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int combinedLight, int combinedOverlay) {
        ItemStack stack = tileEntity.getBlock();
        IBakedModel bakedModel = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(stack, tileEntity.getWorld(), null);

        IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(renderTypeBuffer, RenderTypeLookup.getRenderType(stack), true, stack.hasEffect());

        renderModelColored(tileEntity, bakedModel, stack, combinedLight, combinedOverlay, matrixStack, ivertexbuilder, tileEntity.progress);
    }

    private void renderModelColored(InfestingLeavesTileEntity tileEntity, IBakedModel modelIn, ItemStack stack, int combinedLightIn, int combinedOverlayIn, MatrixStack matrixStackIn, IVertexBuilder bufferIn, float color) {
        Random random = new Random();
        long i = 42L;

        for(Direction direction : Direction.values()) {
            random.setSeed(42L);
            this.renderQuadsColored(tileEntity, matrixStackIn, bufferIn, modelIn.getQuads((BlockState)null, direction, random), stack, combinedLightIn, combinedOverlayIn, color);
        }

        random.setSeed(42L);
        this.renderQuadsColored(tileEntity, matrixStackIn, bufferIn, modelIn.getQuads((BlockState)null, (Direction)null, random), stack, combinedLightIn, combinedOverlayIn, color);
    }

    public void renderQuadsColored(InfestingLeavesTileEntity tileEntity, MatrixStack matrixStackIn, IVertexBuilder bufferIn, List<BakedQuad> quadsIn, ItemStack itemStackIn, int combinedLightIn, int combinedOverlayIn, float color) {
        boolean flag = !itemStackIn.isEmpty();
        MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();

        for(BakedQuad bakedquad : quadsIn) {
            int i = -1;
            if (flag && bakedquad.hasTintIndex()) {
                i = BiomeColors.func_228361_b_(Objects.requireNonNull(tileEntity.getWorld()), tileEntity.getPos());
            }

            int f = (i >> 16 & 255);
            int f1 = (i >> 8 & 255);
            int f2 = (i & 255);

            Color color1 = ColorUtils.average(Color.WHITE, new Color(f - 25, f1 - 25, f2 - 25), color - 0.05F);


            bufferIn.addVertexData(matrixstack$entry, bakedquad, color1.getRed() / 255.0F, color1.getGreen() / 255.0F, color1.getBlue() / 255.0F, combinedLightIn, combinedOverlayIn, true);
        }

    }
}
