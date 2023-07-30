package arkin.tutorial.blocks;

import arkin.tutorial.blockEntities.ModBlockEntities;
import arkin.tutorial.blockEntities.SuperBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SuperBlock extends Block implements EntityBlock {
    public SuperBlock() {
        super(Properties.of(Material.STONE));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile instanceof SuperBlockEntity) {
                if (hit.getDirection() == Direction.UP) ((SuperBlockEntity) tile).plus();
                else if (hit.getDirection() == Direction.DOWN) ((SuperBlockEntity) tile).minus();
                player.sendSystemMessage(Component.translatable(String.valueOf(((SuperBlockEntity) tile).getClicks())));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.SUPER_BLOCK_ENTITY.get().create(pos, state);
    }
}
