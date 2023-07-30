package arkin.tutorial.blocks;

import com.ibm.icu.text.ArabicShaping;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class SuperClickBlock extends Block {
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public SuperClickBlock() {
        super(Properties.of(Material.STONE).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(NORTH, false).setValue(SOUTH, false)
                .setValue(EAST, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        ArrayList<VoxelShape> voxels = new ArrayList<>();
        voxels.add(box(6, 6, 6, 10, 10, 10));

        if (state.getValue(NORTH)) voxels.add(box(6, 6, 0, 10, 10, 10));
        if (state.getValue(SOUTH)) voxels.add(box(6, 6, 10, 10, 10, 16));
        if (state.getValue(EAST)) voxels.add(box(6, 6, 6, 16, 10, 10));
        if (state.getValue(WEST)) voxels.add(box(0, 6, 6, 10, 10, 10));
        if (state.getValue(UP)) voxels.add(box(6, 6, 6, 10, 16, 10));
        if (state.getValue(DOWN)) voxels.add(box(6, 0, 6, 10, 6, 10));

        return voxels.stream().reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            int rand = new Random().nextInt(6);
            if (rand == 0) world.setBlock(pos, state.setValue(NORTH, !state.getValue(NORTH)), 1);
            if (rand == 1) world.setBlock(pos, state.setValue(SOUTH, !state.getValue(SOUTH)), 1);
            if (rand == 2) world.setBlock(pos, state.setValue(EAST, !state.getValue(EAST)), 1);
            if (rand == 3) world.setBlock(pos, state.setValue(WEST, !state.getValue(WEST)), 1);
            if (rand == 4) world.setBlock(pos, state.setValue(UP, !state.getValue(UP)), 1);
            if (rand == 5) world.setBlock(pos, state.setValue(DOWN, !state.getValue(DOWN)), 1);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }
}
