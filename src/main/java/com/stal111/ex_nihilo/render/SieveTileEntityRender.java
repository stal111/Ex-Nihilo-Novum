package com.stal111.ex_nihilo.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.stal111.ex_nihilo.block.SieveBlock;
import com.stal111.ex_nihilo.tileentity.SieveTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class SieveTileEntityRender extends TileEntityRenderer<SieveTileEntity> {

    private float xzScale = 0.875F;
    private float yMin = 0.0625F;
    private float yMax = 0.3750F;

    public SieveTileEntityRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(SieveTileEntity sieveTileEntity, float v, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int combinedLight, int combinedOverlay) {
        renderMesh(sieveTileEntity.getMesh(), matrixStack, renderTypeBuffer, combinedLight, combinedOverlay);
        if (!sieveTileEntity.getContent().isEmpty() && sieveTileEntity.getProgress() < 10) {
            renderContent(sieveTileEntity.getContent(), sieveTileEntity.getProgress() / 10F, matrixStack, renderTypeBuffer, combinedLight, combinedOverlay);
        }
    }

    private void renderMesh(ItemStack stack, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(0.5D, 0.7D, 0.5D);
        matrixStack.scale(2.0F, 1.0F, 2.0F);
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrixStack, renderTypeBuffer);
        matrixStack.pop();
    }

    private void renderContent(ItemStack stack, float progress, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(0.5D, 1.25D - progress * 0.35, 0.5D);
        matrixStack.scale(1.5F, 1.5F - progress * 1.48F, 1.5F);
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrixStack, renderTypeBuffer);
        matrixStack.pop();
    }
}
