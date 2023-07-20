package arkin.tutorial.blockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuperBlockEntity extends BlockEntity {
    private long clicks = 0;
    public SuperBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SUPER_BLOCK_ENTITY.get(), pos, state);
    }

    public void plus() {
        this.clicks++;
        this.setChanged();
    }
    public void minus() {
        this.clicks--;
        this.setChanged();
    }

    public long getClicks() {
        return clicks;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putLong("clicks", clicks);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        clicks = nbt.getLong("clicks");
    }
}
