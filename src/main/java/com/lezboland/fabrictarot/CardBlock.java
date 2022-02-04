package com.lezboland.fabrictarot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class CardBlock extends HorizontalFacingBlock {
    public static final Logger LOGGER = LoggerFactory.getLogger("fabrictarot");


    public CardBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f/16f, 1f);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        LOGGER.info("called getPlacementState");

        Random numGen = new Random();
        //new integer variable called num gets random number btwn 0 and 1
        int num = numGen.nextInt(2);

        LOGGER.info("getPlacementState num = " + num);

        Direction dir;
        if (num == 0) {
            //set dir to direction player is facing
            dir = ctx.getPlayerFacing();
        } else {
            //set dir to opposite of direction player is facing
            dir = ctx.getPlayerFacing().getOpposite();
        }

        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, dir);
    }

}
