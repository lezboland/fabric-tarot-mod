package com.lezboland.fabrictarot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class CardBlock extends HorizontalFacingBlock {
    public static final Logger LOGGER = LoggerFactory.getLogger("fabrictarot");

    public static final int CARD_COUNT = 3;
    public static final IntProperty CARD_NUMBER = IntProperty.of("cardnumber", 0, CARD_COUNT - 1);


    public CardBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(CARD_NUMBER, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(CARD_NUMBER);
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
        int dirNum = numGen.nextInt(2);

        LOGGER.info("getPlacementState num = " + dirNum);

        Direction dir;
        if (dirNum == 0) {
            //set dir to direction player is facing
            dir = ctx.getPlayerFacing();
        } else {
            //set dir to opposite of direction player is facing
            dir = ctx.getPlayerFacing().getOpposite();
        }
        
        int cardNum = numGen.nextInt(CARD_COUNT);

        LOGGER.info("cardNum = " + cardNum);

        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, dir).with(CARD_NUMBER, cardNum);
    }

}
