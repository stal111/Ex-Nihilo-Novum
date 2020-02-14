package com.stal111.ex_nihilo.block;

import com.stal111.ex_nihilo.init.ModBlocks;
import com.stal111.ex_nihilo.item.MeshItem;
import com.stal111.ex_nihilo.recipe.HammerRecipe;
import com.stal111.ex_nihilo.tileentity.SieveTileEntity;
import com.stal111.ex_nihilo.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SieveBlock extends Block implements ITileEntityProvider {

    public static final BooleanProperty MESH = BooleanProperty.create("mesh");

    protected static final VoxelShape SIEVE_SHAPE = Utils.combineAll(
            makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D),
            makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D),
            makeCuboidShape(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D),
            makeCuboidShape(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D),
            makeCuboidShape(2.0D, 12.0D, 0.0D, 14.0D, 16.0D, 1.0D),
            makeCuboidShape(0.0D, 12.0D, 2.0D, 1.0D, 16.0D, 14.0D),
            makeCuboidShape(2.0D, 12.0D, 15.0D, 14.0D, 16.0D, 16.0D),
            makeCuboidShape(15.0D, 12.0D, 2.0D, 16.0D, 16.0D, 14.0D));

    private static final VoxelShape MESH_SHAPE = makeCuboidShape(1.0D, 13.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    private static final VoxelShape SHAPE = VoxelShapes.or(SIEVE_SHAPE, MESH_SHAPE);

    public SieveBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(MESH, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return state.get(MESH) ? SHAPE : SIEVE_SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack stack = player.getHeldItem(hand);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof SieveTileEntity) {
            SieveTileEntity sieveTileEntity = (SieveTileEntity) tileEntity;
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof MeshItem) {
                    if (sieveTileEntity.setMesh(stack)) {
                        if (!player.abilities.isCreativeMode) {
                            stack.shrink(1);
                        }
                        return ActionResultType.SUCCESS;
                    }
                } else if (sieveTileEntity.getContent().isEmpty()) {
                    if (sieveTileEntity.setContent(stack)) {
                        if (!player.abilities.isCreativeMode) {
                            stack.shrink(1);
                        }
                        return ActionResultType.SUCCESS;
                    }
                }
            } else {
                if (sieveTileEntity.makeProgress(player)) {
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return super.onBlockActivated(state, world, pos, player, hand, result);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new SieveTileEntity();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(MESH);
    }
}
